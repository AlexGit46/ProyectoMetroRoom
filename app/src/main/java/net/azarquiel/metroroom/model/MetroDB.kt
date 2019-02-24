package net.azarquiel.metroroom.model

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.Room
import android.content.Context

@Database(entities = [Estacion::class,Linea::class], version = 1)
abstract class MetroDB: RoomDatabase() {
    abstract fun estacionDao(): EstacionDao
    abstract fun lineaDao(): LineaDao
    companion object {
        @Volatile
        private var INSTANCE: MetroDB? = null

        fun getDatabase(context: Context): MetroDB? {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.getApplicationContext(),
                        MetroDB::class.java, "MetroDB.db"
                    ).build()

                }
            }
            return INSTANCE
        }
    }
}
