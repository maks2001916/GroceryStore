package com.example.grocerystore

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.net.toUri

class GroceryInfoActivity : AppCompatActivity() {

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
        setGroceryFeatures()


        saveBTN.setOnClickListener {
            intent = Intent()
            intent.putExtra("title", titleET.text.toString())
            intent.putExtra("price", priceET.text.toString())
            intent.putExtra("description", descriptionET.text.toString())
            intent.putExtra("image", imageIV.toString())
            intent.putExtra("check", false)

        }
        toReturnBTN.setOnClickListener { finish() }
    }

    fun setGroceryFeatures() {
        intent = Intent()
        titleET.setText(intent.getStringExtra("title"))
        priceET.setText(intent.getStringExtra("price"))
        descriptionET.setText(intent.getStringExtra("description"))
        imageIV.setImageURI(intent.getStringExtra("image").toString().toUri())
    }
}