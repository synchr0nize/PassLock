package com.example.passlog

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DateFormat
import java.util.*
import kotlin.collections.ArrayList

class AccountsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accounts)

        val timeDate = Calendar.getInstance().time

        val timeDateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM).format(timeDate)

        val dateTextView : TextView = findViewById(R.id.textDate2) as TextView
        dateTextView.setText(timeDateFormat)

        val amount = getSharedPreferences("passLogAmount", Context.MODE_PRIVATE)
        var p = amount.getInt("amount", 0)

        val recyclerView = findViewById(R.id.vault) as RecyclerView

        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        var vault = ArrayList<Vault>()

        val testVault = Vault("Amazon", "55", "Tomorrow")

        val json = Gson().toJson(testVault)

        if (p > 0) {
            val sharedPreferencesVault = getSharedPreferences("passwordsVault", Context.MODE_PRIVATE)

            for (i in 1..p){
                var vaultPassword = sharedPreferencesVault.getString("savedPasswords" + p, json)

                var objectPassword = Gson().fromJson<Vault>(vaultPassword, Vault::class.java!!)

                vault.add(Vault(objectPassword.accountName, objectPassword.password, objectPassword.date))
            }
        }

        val adapter = ViewAdapter(vault)

        recyclerView.adapter = adapter

    }

    fun mainActivity(view: View){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
