package com.example.fridgefindsmobile

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ShoppingList : AppCompatActivity() {
    private val mData = mutableListOf<Item>()
    private lateinit var adapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_shopping_list)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MyAdapter(mData)
        recyclerView.adapter = adapter

        val userItemEditText = findViewById<EditText>(R.id.userInput)
        val addButton = findViewById<Button>(R.id.addButton)
        addButton.setOnClickListener {
            val userItemText = userItemEditText.text.toString()
            if (userItemText.isNotEmpty()) {
                val item = Item(userItemText)
                adapter.addItem(item)
                userItemEditText.text.clear()
            }
        }

        val delete = findViewById<Button>(R.id.deleteItems)
        delete.setOnClickListener {adapter.deleteItems()}

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val buttonMain: ImageButton = findViewById(R.id.home_main)
        buttonMain.setOnClickListener {
            val intent = Intent(this, PantallaInicio::class.java)
            startActivity(intent)
        }
    }
}