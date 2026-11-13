package com.example.pertemuan9.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Siswa::class], version = 1, exportSchema = false)
abstract class DatabaseSiswa : RoomDatabase() {

    // Mendeklarasikan fungsi abstrak yang mengembalikan SiswaDao
    abstract fun siswaDao(): SiswaDao

    companion object {
        @Volatile
        private var Instance: DatabaseSiswa? = null

        fun getDatabase(context: Context): DatabaseSiswa {
            // Jika Instance tidak null, kembalikan instance tersebut,
            // jika null, buat instance database baru.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseSiswa::class.java,
                    "siswa_database" // Nama file database yang akan disimpan di memori HP
                )
                    .build().also { Instance = it }
            }
        }
    }
}