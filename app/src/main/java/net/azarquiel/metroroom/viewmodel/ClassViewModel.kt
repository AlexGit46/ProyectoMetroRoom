package net.azarquiel.metroroom.viewmodel


import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import net.azarquiel.metroroom.model.*

class EstacionViewModel (application: Application) : AndroidViewModel(application) {

    private var repository: EstacionRepository =
        EstacionRepository(application)

    fun getEstaciones(linea:Int):LiveData<List<Estacion>> = repository.getEstaciones(linea)
    fun getCorrespondencias(estacion:String , linea:Int): List<Int> = repository.getCorrespondencias(estacion, linea)

//    fun insert(estacion: Estacion) {
//        repository.insert(estacion)
//    }
//
//    fun delete(id: Int) {
//        repository.delete(id)
//    }
//
//    fun update(estacion: Estacion) {
//        repository.update(estacion)
//    }

}

class LineaViewModel (application: Application) : AndroidViewModel(application) {

    private var repository: LineaRepository =
        LineaRepository(application)

    var allLineas: LiveData<List<LineaView>> = repository.alllineas

//    fun insert(linea: Linea) {
//        repository.insert(linea)
//    }
//
//    fun delete(id: Int) {
//        repository.delete(id)
//    }
//
//    fun update(linea: Linea) {
//        repository.update(linea)
//    }

}
