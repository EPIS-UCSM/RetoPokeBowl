package bimerso.megabitefood.retopokebowl.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bimerso.megabitefood.retopokebowl.R
import bimerso.megabitefood.retopokebowl.models.Modifier
import bimerso.megabitefood.retopokebowl.models.Option
import kotlinx.android.synthetic.main.item_options.view.*


class OptionAdapter(var dataSet : List<Option>, private val context: Context,var type:String, var callback: (sede:Option)->Unit) : RecyclerView.Adapter<OptionAdapter.OptionViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_options, parent, false);
        return OptionViewHolder(view)
    }
    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: OptionViewHolder, position: Int) {
       holder.setValues(dataSet[position])
    }
    inner class OptionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setValues(item : Option){
            when (type) {
                "radio" -> {
                    itemView.container_option_radio.visibility=View.VISIBLE
                    itemView.container_option_checkbox.visibility=View.GONE
                    itemView.container_option_incremental.visibility=View.GONE

                    itemView.tv_item_radio_title.text = item.title

                }
                "incremental" -> {
                    itemView.container_option_radio.visibility=View.GONE
                    itemView.container_option_checkbox.visibility=View.GONE
                    itemView.container_option_incremental.visibility=View.VISIBLE
                    itemView.tv_item_incremental_title.text = item.title
                    itemView.tv_item_incremental_price.text = "+ s/"+item.price

                }
                "checkbox" -> {
                    itemView.container_option_radio.visibility=View.GONE
                    itemView.container_option_checkbox.visibility=View.VISIBLE
                    itemView.container_option_incremental.visibility=View.GONE
                    itemView.tv_item_checkbox_title.text = item.title

                }
            }

            itemView.setOnClickListener {
                callback(item)
            }
        }
    }
}