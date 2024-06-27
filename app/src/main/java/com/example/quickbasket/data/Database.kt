package com.example.quickbasket.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.quickbasket.data.daos.UsersDao
import com.example.quickbasket.data.daos.ProductsDao
import com.example.quickbasket.data.daos.FavouritesDao
import com.example.quickbasket.data.models.Users
import com.example.quickbasket.data.models.Products
import com.example.quickbasket.data.models.Favourites

@Database(entities = [Users::class, Products::class, Favourites::class], version = 2, exportSchema = false)
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDao(): UsersDao
    abstract fun productDao(): ProductsDao
    abstract fun favouriteDao(): FavouritesDao

    companion object{
        @Volatile
        private var Instance: UserDatabase? = null

        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE Products ADD COLUMN imageResId INTEGER NOT NULL DEFAULT 0")
            }
        }

        fun getDatabase(context: Context): UserDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, UserDatabase::class.java, "UserAPPDatabase")
                    .addMigrations(MIGRATION_1_2)
                    .build().also { Instance = it }
            }
        }
    }
}