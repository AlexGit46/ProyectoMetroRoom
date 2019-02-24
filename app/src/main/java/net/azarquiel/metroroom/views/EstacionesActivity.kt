package net.azarquiel.metroroom.views

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_estaciones.*
import net.azarquiel.metroroom.R
import net.azarquiel.metroroom.model.Estacion
import net.azarquiel.metroroom.model.EstacionView
import net.azarquiel.metroroom.model.LineaView
import net.azarquiel.metroroom.viewmodel.EstacionViewModel
import net.azarquiel.metroroom.adapter.CustomAdapterEstaciones
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class EstacionesActivity : AppCompatActivity() {

    private lateinit var lineaView: LineaView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estaciones)

        lineaView = intent.getSerializableExtra("linea") as LineaView

        title = "${lineaView.nombre} (${lineaView.inifin})"

        val estacionViewModel = ViewModelProviders.of(this).get(EstacionViewModel::class.java)

        estacionViewModel.getEstaciones(lineaView.id).observe(this, Observer { estaciones ->
            estaciones?.let {
                getCorrespondencias(it) }
        })
    }

    private fun getCorrespondencias(estaciones: List<Estacion>) {
        val estacionViewModel = ViewModelProviders.of(this).get(EstacionViewModel::class.java)
        val estacionesView = ArrayList<EstacionView>()
        doAsync {
            for (estacion in estaciones){
                estacionesView.add(EstacionView(estacion.id, estacion.nombre, estacionViewModel.getCorrespondencias(estacion.nombre, lineaView.id)))
            }
            uiThread {
                    showData(estacionesView)
            }
        }

    }

    private fun showData(estacionesView: ArrayList<EstacionView>) {
        val adapter = CustomAdapterEstaciones(this, R.layout.rowestaciones, lineaView.color)
        rvEstaciones.layoutManager = LinearLayoutManager(this)
        rvEstaciones.adapter = adapter
        adapter.setEstaciones(estacionesView)
    }
}
