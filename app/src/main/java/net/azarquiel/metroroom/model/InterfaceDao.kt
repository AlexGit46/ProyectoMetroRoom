package net.azarquiel.metroroom.model

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update

@Dao
interface EstacionDao {

    @Query("SELECT * from estacion WHERE linea = :linea ORDER BY id ASC")
    fun getEstaciones(linea:Int): LiveData<List<Estacion>>

    @Query("SELECT linea from estacion WHERE nombre=:estacion and linea!=:linea")
    fun getCorrespondencias(estacion:String , linea:Int): List<Int>

    @Insert
    fun insert(estacion: Estacion)

    @Query("DELETE FROM estacion WHERE id=:id")
    fun delete(id:Int)

    @Update
    fun update(estacion: Estacion)
}

@Dao
interface LineaDao {

    @Query("select l.id id,l.nombre nombre,l.color color, x.inifin inifin from " +
            "(select a.linea linea, e1.nombre||' - '||e2.nombre inifin from " +
            "estacion e1, estacion e2, " +
            "(select linea, max(id) maxi, min(id) mini from " +
            "estacion group by linea) a where e1.id = maxi and e2.id=mini) x," +
            " linea l where x.linea=l.id order by id")
    fun getAllLineas(): LiveData<List<LineaView>>

}
