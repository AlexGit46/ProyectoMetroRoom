package net.azarquiel.metroroom.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "linea")
data class Linea(@PrimaryKey
                 var id: Int,
                 var nombre:String,
                 var color:String)

@Entity(tableName = "estacion", foreignKeys = [ForeignKey(entity = Linea::class, parentColumns = arrayOf("id"), childColumns = arrayOf("linea"))])
data class Estacion(@PrimaryKey
                    var id: Int,
                    var nombre:String,
                    var linea:Int)

data class LineaView (var id: Int,
                      var nombre:String,
                      var color:String,
                      var inifin:String): Serializable

data class EstacionView (var id: Int,
                      var nombre:String,
                      var correspondencias:List<Int>): Serializable
