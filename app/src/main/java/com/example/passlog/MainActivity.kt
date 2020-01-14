package com.example.passlog

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DateFormat
import java.util.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val timeDate = Calendar.getInstance().time

        val timeDateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM).format(timeDate)

        val clock : TextView = findViewById(R.id.dateTime)
        clock.setText(timeDateFormat)
    }

    fun accountsActivity(view: View){
        val intent = Intent(this, AccountsActivity::class.java)
        startActivity(intent)
    }

    fun randomise(view: View){
        val digits1 = Random.nextInt(0,99)
        val digits2 = Random.nextInt(0,99)
        val digits3 = Random.nextInt(0,99)
        val digits4 = Random.nextInt(0,99)

        val box1 = findViewById<EditText>(R.id.digits1)
        box1.setText(digits1.toString())

        val box2 = findViewById<EditText>(R.id.digits2)
        box2.setText(digits2.toString())

        val box3 = findViewById<EditText>(R.id.digits3)
        box3.setText(digits3.toString())

        val box4 = findViewById<EditText>(R.id.digits4)
        box4.setText(digits4.toString())

    }

    fun savePass(view: View){
        val amount = getSharedPreferences("passLogAmount", Context.MODE_PRIVATE)
        var p = amount.getInt("amount", 0)

        if(p == 0){
            val editAmount = amount.edit()

            editAmount.putInt("amount", 1)
            editAmount.apply()

            p = 1
        }

        val timeDate = Calendar.getInstance().time

        val timeDateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM).format(timeDate)

        val accountName = accountName.text.toString().trim()
        val password = digits1.text.toString().trim() + "-" + digits2.text.toString().trim() + "-" + digits3.text.toString().trim() + "-" + digits4.text.toString().trim()
        if (accountName == "" || password == "---") {
            Toast.makeText(this, "Valid input required", Toast.LENGTH_LONG).show()
        } else {
            val date = timeDateFormat.toString()

            val info = Vault(accountName, password, date)
            val json = Gson().toJson(info)

            val sharedPreferencesVault = getSharedPreferences("passwordsVault", Context.MODE_PRIVATE)
            val vaultEdit = sharedPreferencesVault.edit()

            vaultEdit.putString("savedPasswords" + p, json)
            vaultEdit.apply()

            Toast.makeText(this, "Password Saved", Toast.LENGTH_LONG).show()

            val editAmount = amount.edit()

            editAmount.putInt("amount", p + 1)
            editAmount.apply()
        }
    }
}
