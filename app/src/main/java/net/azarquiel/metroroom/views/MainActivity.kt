package net.azarquiel.metroroom.views

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import net.azarquiel.metroroom.R
import net.azarquiel.metroroom.adapter.CustomAdapterLineas
import net.azarquiel.metroroom.model.LineaView
import net.azarquiel.metroroom.util.Util
import net.azarquiel.metroroom.viewmodel.LineaViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var lineaViewModel: LineaViewModel

    private lateinit var lineas: LiveData<List<LineaView>>
    private lateinit var adapter: CustomAdapterLineas

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Util.inyecta(this)
        lineaViewModel = ViewModelProviders.of(this).get(LineaViewModel::class.java)
        showData()
        lineaViewModel.allLineas.observe(this, Observer { lineas ->
            lineas?.let {
                adapter.setLineas(it) }
        })
    }

    private fun showData() {
        adapter = CustomAdapterLineas(this, R.layout.rowlinea)
        rvLineas.layoutManager = LinearLayoutManager(this)
        rvLineas.adapter = adapter
    }

    fun onClickLinea(view: View){
        val lineaView = view.tag as LineaView
        val intent = Intent(this, EstacionesActivity::class.java)
        intent.putExtra("linea",lineaView)
        startActivity(intent)
    }
}
