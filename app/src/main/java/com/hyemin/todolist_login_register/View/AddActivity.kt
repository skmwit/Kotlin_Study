package com.hyemin.todolist_login_register.View

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hyemin.todolist_login_register.Model.MemoModel
import com.hyemin.todolist_login_register.R
import com.hyemin.todolist_login_register.Room.Memo
import kotlinx.android.synthetic.main.activity_add.*
import java.util.*

class AddActivity: AppCompatActivity() {

    private var memoModel: MemoModel? = null
    val cal = Calendar.getInstance()
    val c = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        memoModel = MemoModel(applicationContext)
        //memoDb = MemoDatabase.getInstance(this)

        val addRunnable = Runnable{
            val newMemo = Memo()
            newMemo.title = add_title.text.toString()
            newMemo.content = add_content.text.toString()
            newMemo.year = cal.get(Calendar.YEAR)
            newMemo.month = cal.get(Calendar.MONTH)+1
            newMemo.day = cal.get(Calendar.DAY_OF_MONTH)
            newMemo.hour = c.get(Calendar.HOUR_OF_DAY)
            newMemo.minute = c.get(Calendar.MINUTE)
            memoModel!!.insert(newMemo)
        }

        add_Date.setOnClickListener{

            val date_picker = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{DatePicker, cyear, cmonth, cday ->
                cal.set(Calendar.YEAR, cyear)
                cal.set(Calendar.MONTH, cmonth)
                cal.set(Calendar.DAY_OF_MONTH, cday)
            add_Date.setText(""+cyear +"년"+(cmonth+1)+"월"+cday+"일")},cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH))
            date_picker.show()
        }

        add_Time.setOnClickListener{
            val time_picker = TimePickerDialog.OnTimeSetListener{timePicker, hour, minute ->
                c.set(Calendar.HOUR_OF_DAY, hour)
                c.set(Calendar.MINUTE, minute)

                add_Time.setText(""+ hour +" : "+ minute)
            }
            TimePickerDialog(this, time_picker, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true).show()
        }

        add_button.setOnClickListener{
            val addThread = Thread(addRunnable)
            addThread.start()

            val i = Intent(this, MainActivity::class.java)
            startActivity(i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP))
            finish()

        }
    }
}