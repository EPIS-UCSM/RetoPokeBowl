package bimerso.megabitefood.retopokebowl.ui.features.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import bimerso.megabitefood.retopokebowl.R
import bimerso.megabitefood.retopokebowl.base.BaseActivity
import bimerso.megabitefood.retopokebowl.databinding.ActivityDetailFoodBinding
import bimerso.megabitefood.retopokebowl.models.Data
import bimerso.megabitefood.retopokebowl.models.Option
import bimerso.megabitefood.retopokebowl.ui.adapters.ModifiersAdapter
import bimerso.megabitefood.retopokebowl.ui.adapters.OptionAdapter
import bimerso.megabitefood.retopokebowl.ui.features.detail.repository.DetailFoodRepository
import bimerso.megabitefood.retopokebowl.ui.features.detail.viewmodel.DetailFoodViewModel
import bimerso.megabitefood.retopokebowl.ui.features.detail.viewmodel.DetailFoodViewModelProviderFactory
import com.bumptech.glide.Glide

class DetailFoodActivity : BaseActivity() {
    var adapter:ModifiersAdapter?=null
    private lateinit var binding:ActivityDetailFoodBinding
    lateinit var viewModel:DetailFoodViewModel
    private var precioFinal:Double=0.0
    private lateinit var entity:Data
    private var ID="8"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository = DetailFoodRepository()
        val viewModelProviderFactory = DetailFoodViewModelProviderFactory(repository)
        viewModel = ViewModelProvider(this,viewModelProviderFactory).get(DetailFoodViewModel::class.java)
        binding = ActivityDetailFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getDetailFood(ID)

        binding.arrowback.setOnClickListener{
            onBackPressed()
        }
        binding.btnhacerpedido.setOnClickListener{
            showErrorMessage("Avances","Me faltó terminar la logica de mínimos y maximos y hallar los precios agregados.")
        }
    }
    private fun getDetailFood(id: String) {
        viewModel.getDetailFood(id).observe(this,{
            when (it){
                is DetailFoodViewModel.ViewState.Loading->{
                    showProgressDialog()
                }
                is DetailFoodViewModel.ViewState.GetDetailFood->{
                    hideProgressDialog()
                    entity=it.entidad
                    setView()
                }
                is DetailFoodViewModel.ViewState.Failure->{
                    hideProgressDialog()
                    showErrorMessage("Error",it.error)
                }
            }
        })
    }
    private fun setView() {
        Glide.with(this).load(entity.item_image).fitCenter().error(R.color.colorPrimary).into(binding.imagefood)
        binding.tvname.text = entity.name.toUpperCase()
        binding.tvtitle.text = entity.title
        binding.tvsummary.text = entity.summary
        if(entity.offer!=null){
            var descuento:Double = (100 - entity.offer.discount_percent).toDouble()
            var precioFinal:Double = entity.price*descuento
            precioFinal /= 100
            binding.tvPriceDiscount.text = "-"+entity.offer.discount_percent+"%"
            binding.tvPriceReal.text = "S/"+String.format("%.2f",entity.price)
            binding.tvPrice.text = "S/"+String.format("%.2f",precioFinal)
        }
        else{
            binding.tvPrice.text = "S/"+String.format("%.2f",entity.price)
            binding.tvPriceDiscount.visibility = View.GONE
            binding.tvPriceReal.visibility = View.GONE


        }
        setAdapterModifiers()
    }
    private fun setAdapterModifiers() {
        adapter = ModifiersAdapter(entity.modifiers, this) {
            //Open options
        }
        binding.rvmodificadores.adapter = adapter
        binding.rvmodificadores.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL))
        binding.btnhacerpedido.text = "Agregar al carrito s/"+String.format("%.2f",entity.price)
    }
    override fun onBackPressed() {
        super.onBackPressed()
    }
}