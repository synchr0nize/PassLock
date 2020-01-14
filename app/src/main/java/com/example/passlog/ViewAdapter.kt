package com.example.passlog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class ViewAdapter (val passwordList: ArrayList<Vault>) : RecyclerView.Adapter<ViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.recycler_view, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return passwordList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val passwordVault: Vault = passwordList[position]

        holder?.account?.text = passwordVault.accountName
        holder?.password?.text = passwordVault.password
        holder?.date?.text = passwordVault.date

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val account = itemView.findViewById(R.id.account) as TextView
        val password = itemView.findViewById(R.id.password) as TextView
        val date = itemView.findViewById(R.id.date) as TextView
    }
}