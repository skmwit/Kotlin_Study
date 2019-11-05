package com.hyemin.todolist_login_register.Room

import androidx.room.*

@Dao
interface MemoDao {
    @Query("SELECT * FROM memo")
    fun getAll(): MutableList<Memo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(memo: Memo)

    @Query("DELETE from memo")
    fun deleteAll()

    @Query("DELETE FROM memo WHERE id = (:id)")
    fun deleteMemo(id: Int)

    @Query("UPDATE memo SET title=:title, content=:content, year=:year, month=:month, day=:day, hour=:hour, minute=:minute WHERE id = (:id)")
    fun updateMemo(id: Int, title: String, content:String, year: Int, month: Int, day: Int, hour: Int, minute: Int)
}
