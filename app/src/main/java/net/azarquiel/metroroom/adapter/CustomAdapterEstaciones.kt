package net.azarquiel.metroroom.adapter

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import kotlinx.android.synthetic.main.rowestaciones.view.*
import net.azarquiel.metroroom.model.EstacionView
import android.widget.LinearLayout
import net.azarquiel.metroroom.R
import net.azarquiel.metroroom.model.LineaView

class CustomAdapterEstaciones(val context: Context, val layout: Int, val color: String) : RecyclerView.Adapter<CustomAdapterEstaciones.ViewHolder>() {

    private var dataList: List<EstacionView> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewlayout = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(viewlayout, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item,color)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    internal fun setEstaciones(estaciones: List<EstacionView>) {
        this.dataList = estaciones
        notifyDataSetChanged()
    }

    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: EstacionView, color: String){
            itemView.tvEstacion.text = dataItem.nombre
            itemView.linearIconos.removeAllViews()
            for (correspondencia in dataItem.correspondencias){
                val iv = ImageView(context)
                val witdhiv = context.resources.getDimension(R.dimen.witdhiv)
                val layoutParams = LinearLayout.LayoutParams(witdhiv.toInt(),witdhiv.toInt())
                iv.layoutParams = layoutParams
                val id = context.resources.getIdentifier("icono_linea_" + correspondencia, "drawable", context.packageName)
                iv.setImageResource(id)
                itemView.linearIconos.addView(iv)
            }
            var newcolor = color.replace("#FF", "#66").replace("#A0", "#44")
            (itemView as CardView).getChildAt(0).setBackgroundColor(Color.parseColor(newcolor))
        }

    }
}