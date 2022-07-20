package com.example.afinal

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import java.util.*

class MainAdapter(private val context: Context,
                  private val daftarBarang: ArrayList<Order?>?) : RecyclerView.Adapter<MainViewHolder>() {
    private val listener: FirebaseDataListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_item_barang, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.namaBarang.text = "Nama   : " + (daftarBarang?.get(position)?.nama)
        holder.merkBarang.text = "Merk     : " + (daftarBarang?.get(position)?.merk)
        holder.hargaBarang.text = "Harga   : " + (daftarBarang?.get(position)?.harga)
        holder.view.setOnClickListener { listener.onDataClick(daftarBarang?.get(position), position) }
    }

    override fun getItemCount(): Int {

        return daftarBarang?.size!!
    }

    //interface data listener
    interface FirebaseDataListener {
        fun onDataClick(barang: Order?, position: Int)
    }

    init {
        listener = context as FirebaseDataListener
    }}
