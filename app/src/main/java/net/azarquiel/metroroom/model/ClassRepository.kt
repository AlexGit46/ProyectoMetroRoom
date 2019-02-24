package net.azarquiel.metroroom.model

import android.app.Application
import android.arch.lifecycle.LiveData
import org.jetbrains.anko.doAsync

class EstacionRepository(application: Application) {

    val estacionDao = MetroDB.getDatabase(application)!!.estacionDao()

    fun getEstaciones(linea:Int):LiveData<List<Estacion>> = estacionDao.getEstaciones(linea)
    fun getCorrespondencias(estacion:String , linea:Int): List<Int> = estacionDao.getCorrespondencias(estacion, linea)
}

class LineaRepository(application: Application)  {

    val lineaDao = MetroDB.getDatabase(application)!!.lineaDao()
    val alllineas: LiveData<List<LineaView>> = lineaDao.getAllLineas()

}