package com.hyemin.todolist_login_register.Model

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.hyemin.todolist_login_register.R
import com.hyemin.todolist_login_register.Room.Memo
import com.hyemin.todolist_login_register.View.AddActivity
import com.hyemin.todolist_login_register.View.EditActivity
import com.hyemin.todolist_login_register.View.LoginActivity

class MemoAdapter(val context: Context, val memo:MutableList<Memo>, val memoModel: MemoModel) :
RecyclerView.Adapter<MemoAdapter.Holder>(){
    //private var memoModel:MemoModel? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view=LayoutInflater.from(context).inflate(R.layout.item_memo, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return memo.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder?.bind(memo[position])
        holder.itemView.setOnClickListener{
            val intent = Intent(context, EditActivity::class.java)
            intent.putExtra("id", memo.get(holder.adapterPosition).id)

            intent.putExtra("item_title",memo.get(holder.adapterPosition).title)
            intent.putExtra("item_content",memo.get(holder.adapterPosition).content)
            intent.putExtra("item_dueYear",memo.get(holder.adapterPosition).year.toString())
            intent.putExtra("item_dueMonth",memo.get(holder.adapterPosition).month.toString())
            intent.putExtra("item_dueDay",memo.get(holder.adapterPosition).day.toString())
            intent.putExtra("item_dueHour",memo.get(holder.adapterPosition).hour.toString())
            intent.putExtra("item_dueMinute",memo.get(holder.adapterPosition).minute.toString())

            context.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
        }
    }

    fun removeItem(viewHolder: RecyclerView.ViewHolder, position: Int){

        memoModel.deleteMemo(memo.get(viewHolder.adapterPosition).id)
        notifyDataSetChanged()

        Log.d("Memo", position.toString())
        val removeItemThread=Thread{memo.removeAt(position)}
        removeItemThread.start()
        removeItemThread.join()
        notifyItemRemoved(position)

    }

    inner class Holder(itemView: View?):RecyclerView.ViewHolder(itemView!!){

        val title = itemView?.findViewById<TextView>(R.id.itemName)
        val due = itemView?.findViewById<TextView>(R.id.itemDue)
        val card = itemView?.findViewById<CardView>(R.id.card_view)

        fun bind(memo:Memo){
            title?.text = memo.title
            due?.text = "마감 : "+ memo.year.toString() +"년 " + memo.month.toString() + "월 " + memo.day.toString() + "일 " + memo.hour.toString()+ "시 "+memo.minute.toString() + "분"

        }
    }
}