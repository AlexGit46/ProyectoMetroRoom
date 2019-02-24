package net.azarquiel.metroroom.adapter

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.rowlinea.view.*
import net.azarquiel.metroroom.model.LineaView

class CustomAdapterLineas(val context: Context, val layout: Int) : RecyclerView.Adapter<CustomAdapterLineas.ViewHolder>() {

    private var dataList: List<LineaView> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewlayout = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(viewlayout, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    internal fun setLineas(lineas: List<LineaView>) {
        this.dataList = lineas
        notifyDataSetChanged()
    }

    class ViewHolder(viewlayout: View,val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: LineaView){
            itemView.tvLinea.text = dataItem.nombre
            itemView.tvIniFin.text = dataItem.inifin
            val id = context.resources.getIdentifier("icono_linea_" + dataItem.id, "drawable", context.packageName)
            itemView.ivLinea.setImageResource(id)
            (itemView as CardView).getChildAt(0).setBackgroundColor(Color.parseColor(dataItem.color.replace("#FFFFFFFF","#FFF0F0F0")))
            itemView.tag = dataItem
        }
    }
}
