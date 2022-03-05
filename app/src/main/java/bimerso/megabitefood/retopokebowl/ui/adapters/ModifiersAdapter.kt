package bimerso.megabitefood.retopokebowl.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import bimerso.megabitefood.retopokebowl.R
import bimerso.megabitefood.retopokebowl.models.Modifier
import bimerso.megabitefood.retopokebowl.models.Option
import kotlinx.android.synthetic.main.item_modificador.view.*


class ModifiersAdapter(var dataSet : List<Modifier>, val context: Context, var callback: (sede:Modifier)->Unit) : RecyclerView.Adapter<ModifiersAdapter.ModifierViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModifierViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_modificador, parent, false);
        return ModifierViewHolder(view)
    }
    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: ModifierViewHolder, position: Int) {
       holder.setValues(dataSet[position])
    }

    inner class ModifierViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setValues(item : Modifier){
            itemView.tv_item_title.text= item.item_name
            if(item.min==0)
                itemView.tv_item_minmax.text= "Escoge entre 1 o " +item.max + " opciones"
            else
                itemView.tv_item_minmax.text= "Escoge entre "+ item.min+ " o " +item.max + " opciones"
            if(item.required)
                itemView.tv_item_required.text= "(Obligatorio)"
            else
                itemView.tv_item_required.text= "(Opcional)"
            itemView.btnmodifier.setOnClickListener {
                if(itemView.container_modifier.visibility == View.VISIBLE){
                    itemView.container_modifier.visibility = View.GONE
                    itemView.rvoptions.visibility = View.GONE
                }
                else{
                    itemView.container_modifier.visibility = View.VISIBLE
                    itemView.rvoptions.visibility = View.VISIBLE
                }
                callback(item)
                setAdapterOptions(item.options,itemView,item.type)
            }
        }
    }
    private fun setAdapterOptions(options: List<Option>, itemView: View,type:String) {
        var adapterOptions = OptionAdapter(options, context,type) {
            //Calculate price or view something else
        }
        itemView.rvoptions.adapter = adapterOptions
    }
}