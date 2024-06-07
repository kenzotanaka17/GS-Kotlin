package com.example.gs.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gs.R
import com.example.gs.model.Praia

class PraiaAdapter(private val onDeleteClick: (Praia) -> Unit) : RecyclerView.Adapter<PraiaAdapter.PraiaViewHolder>() {

    private val praias = mutableListOf<Praia>()

    fun adicionarPraia(novaPraia: Praia) {
        praias.add(novaPraia)
        notifyDataSetChanged()
    }

    fun removerPraia(praia: Praia) {
        praias.remove(praia)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PraiaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_praia, parent, false)
        return PraiaViewHolder(view)
    }

    override fun onBindViewHolder(holder: PraiaViewHolder, position: Int) {
        val praia = praias[position]
        holder.bind(praia)
    }

    override fun getItemCount(): Int {
        return praias.size
    }

    inner class PraiaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvPraiaInfo: TextView = itemView.findViewById(R.id.tvPraiaInfo)
        private val btnExcluir: Button = itemView.findViewById(R.id.btnExcluir)

        fun bind(praia: Praia) {
            tvPraiaInfo.text = "${praia.nome}, ${praia.cidade}, ${praia.estado}"
            btnExcluir.setOnClickListener { onDeleteClick(praia) }
        }
    }
}
