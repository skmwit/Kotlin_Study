package com.hyemin.todolist_login_register.View

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hyemin.todolist_login_register.Model.MemoModel
import com.hyemin.todolist_login_register.R
import com.hyemin.todolist_login_register.Room.Memo
import kotlinx.android.synthetic.main.activity_edit.*
import java.util.*

class EditActivity: AppCompatActivity() {

    private var memoModel: MemoModel? = null
    val cal = Calendar.getInstance()
    val c = Calendar.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        memoModel = MemoModel(applicationContext)

        val item_id = intent.extras?.getInt("id")

        edit_title.setText(intent.getStringExtra("item_title"))
        edit_content.setText(intent.getStringExtra("item_content"))
        edit_Date.setText(intent.getStringExtra("item_dueYear")+"년 "+intent.getStringExtra("item_dueMonth")+"월 "+intent.getStringExtra("item_dueDay")+"일")
        edit_Time.setText(intent.getStringExtra("item_dueHour")+" : "+intent.getStringExtra("item_dueMinute"))

        val editRunnable = Runnable {

            val editMemo = Memo()
            editMemo.title = edit_title.text.toString()
            editMemo.content = edit_content.text.toString()
            editMemo.year = cal.get(Calendar.YEAR)
            editMemo.month = cal.get(Calendar.MONTH)+1
            editMemo.day = cal.get(Calendar.DAY_OF_MONTH)
            editMemo.hour = c.get(Calendar.HOUR_OF_DAY)
            editMemo.minute = c.get(Calendar.MINUTE)

            if (item_id != null) {
                memoModel!!.updateMemo(item_id.toInt(), editMemo.title.toString(), editMemo.content.toString(), editMemo.year!!.toInt(), editMemo.month!!.toInt(), editMemo.day!!.toInt(), editMemo.hour!!.toInt(), editMemo.minute!!.toInt())
            }

        }

        edit_Date.setOnClickListener{

            val date_picker = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{ DatePicker, cyear, cmonth, cday ->
                cal.set(Calendar.YEAR, cyear)
                cal.set(Calendar.MONTH, cmonth)
                cal.set(Calendar.DAY_OF_MONTH, cday)
                edit_Date.setText(""+cyear +"년"+(cmonth+1)+"월"+cday+"일")},cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH))
            date_picker.show()
        }

        edit_Time.setOnClickListener{
            val time_picker = TimePickerDialog.OnTimeSetListener{ timePicker, hour, minute ->
                c.set(Calendar.HOUR_OF_DAY, hour)
                c.set(Calendar.MINUTE, minute)

                edit_Time.setText(""+ hour +" : "+ minute)
            }
            TimePickerDialog(this, time_picker, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true).show()
        }


        edit_button.setOnClickListener{
            val editThread = Thread(editRunnable)
            editThread.start()

            val i = Intent(this, MainActivity::class.java)
            startActivity(i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP))
            finish()

        }
    }

}