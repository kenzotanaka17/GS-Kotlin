package com.example.gs

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gs.adapter.PraiaAdapter
import com.example.gs.model.Praia

class MainActivity : AppCompatActivity() {

    private lateinit var praiaAdapter: PraiaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerPraia)
        praiaAdapter = PraiaAdapter { praia ->
            praiaAdapter.removerPraia(praia)
        }
        recyclerView.adapter = praiaAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val etNomePraia = findViewById<EditText>(R.id.etNPraia)
        val etCidadePraia = findViewById<EditText>(R.id.etCidPraia)
        val etEstadoPraia = findViewById<EditText>(R.id.etEstPraia)

        btnAdd.setOnClickListener {
            val nome = etNomePraia.text.toString().trim()
            val cidade = etCidadePraia.text.toString().trim()
            val estado = etEstadoPraia.text.toString().trim()

            if (nome.isEmpty() || cidade.isEmpty() || estado.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val novaPraia = Praia(nome, cidade, estado)
            praiaAdapter.adicionarPraia(novaPraia)
            etNomePraia.text.clear()
            etCidadePraia.text.clear()
            etEstadoPraia.text.clear()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_alunos -> {
                startActivity(Intent(this, AlunosActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
