package com.example.grocerystore

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ProductInfoActivity : AppCompatActivity() {

    private var removable: Removable? = null
    private var updatable: Updatable? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        removable = context as Removable?
        updatable = context as Updatable?
    }

    private lateinit var toolbarTB: Toolbar
    private lateinit var imageIV: ImageView
    private lateinit var titleET: EditText
    private lateinit var priceET: EditText
    private lateinit var descriptionET: EditText
    private lateinit var toReturnBTN: Button
    private lateinit var saveBTN: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_product_info)

        toolbarTB = findViewById(R.id.toolbarInfo)
        imageIV = findViewById(R.id.imageInfoIV)
        titleET = findViewById(R.id.titleInfoET)
        priceET = findViewById(R.id.priceInfoET)
        descriptionET = findViewById(R.id.descriptionInfoET)
        toReturnBTN = findViewById(R.id.toReturnBTN)
        saveBTN = findViewById(R.id.saveBTN)

        saveBTN.setOnClickListener {

        }
        toReturnBTN.setOnClickListener { finish() }
    }
}