package com.hyemin.todolist_login_register.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Memo::class], version=1)
abstract class MemoDatabase: RoomDatabase() {
    abstract val memoDao :MemoDao

    companion object{
        private var INSTANCE : MemoDatabase? = null

        fun getInstance(context: Context): MemoDatabase?{
            if(INSTANCE == null){
                synchronized(MemoDatabase::class){
                    INSTANCE= Room.databaseBuilder(context.applicationContext, MemoDatabase::class.java, "memo.db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}