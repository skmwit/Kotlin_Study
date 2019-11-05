package com.hyemin.todolist_login_register.View

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hyemin.todolist_login_register.Model.App
import com.hyemin.todolist_login_register.Model.MemoAdapter
import com.hyemin.todolist_login_register.Model.MemoModel
import com.hyemin.todolist_login_register.R
import com.hyemin.todolist_login_register.Room.Memo
import com.hyemin.todolist_login_register.Room.MemoDatabase
import kotlinx.android.synthetic.main.activity_memolist.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var memoModel: MemoModel? = null
    var memoList = mutableListOf<Memo>()
    //var memoList!!.add(Memo())
    private var memoDb: MemoDatabase? = null
    lateinit var Madapter: MemoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memolist)

        memoModel = MemoModel(applicationContext)
        memoDb = MemoDatabase.getInstance(this)

        val r = Runnable {
            try{
                memoList.clear()
                memoList.addAll(memoModel!!.getAll())

                Madapter = MemoAdapter(applicationContext, memoList, memoModel!!)
                RecyclerView.adapter = Madapter

                RecyclerView.layoutManager = LinearLayoutManager(this)
                RecyclerView.setHasFixedSize(true)

            }catch(e: Exception){
                Log.d("tag", "Error = $e")
            }
        }

        val thread = Thread(r)
        thread.start()

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, position: Int) {
                Madapter.removeItem(viewHolder, viewHolder.adapterPosition)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false            }
        }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(RecyclerView)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.logout -> {
                val intent = Intent(applicationContext, LoginActivity::class.java)
                applicationContext.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
                finish()
                App.prefs.checkBox = false
                App.prefs.email = ""
                App.prefs.passwd = ""
            }
            R.id.addBtn->{
                val i = Intent(this, AddActivity::class.java)
                applicationContext.startActivity(i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
                finish()
            }
        }
    }
}
