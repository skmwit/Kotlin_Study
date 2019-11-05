package com.hyemin.todolist_login_register.Model

import android.content.Context
import com.hyemin.todolist_login_register.Room.Memo
import com.hyemin.todolist_login_register.Room.MemoDao
import com.hyemin.todolist_login_register.Room.MemoDatabase
import java.lang.Exception

class MemoModel(val context: Context) {

    private var database: MemoDatabase? = MemoDatabase.getInstance(context)
    private var memoDao: MemoDao

    init{
        memoDao = database!!.memoDao
    }

    fun deleteMemo(id: Int) {
        Thread{memoDao.deleteMemo(id)}.start()
    }

    fun insert(memo: Memo){
        val memoList = mutableListOf<Memo>()
        val insertItemThread = Thread{memoList.add(memo)}
        insertItemThread.start()

        try{
            insertItemThread.join()
        }
        catch (e: Exception){
            e.printStackTrace()
        }
        memoDao.insert(memo)
    }

    fun getAll():MutableList<Memo> {
       return memoDao.getAll()
    }

    fun updateMemo(id: Int, title: String, content: String, year: Int, month: Int, day: Int, hour: Int, minute: Int){
        Thread{memoDao.updateMemo(id, title, content, year, month, day, hour, minute)}.start()
    }
}