package com.example.grocerystore

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import java.io.IOException

class StoreActivity : AppCompatActivity() {

    private val GALLERY_REQUEST = 302
    var photoUri: Uri? = null
    var grocerys: MutableList<Grocery> = mutableListOf()

    private lateinit var toolbarTB: Toolbar
    private lateinit var imageIV: ImageView
    private lateinit var titleET: EditText
    private lateinit var priceET: EditText
    private lateinit var saveBTN: Button
    private lateinit var grocerysLV: ListView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_store)

        toolbarTB = findViewById(R.id.toolbarStore)
        imageIV = findViewById(R.id.editImageIV)
        titleET = findViewById(R.id.titleET)
        priceET = findViewById(R.id.priceET)
        saveBTN = findViewById(R.id.addBTN)
        grocerysLV = findViewById(R.id.grocerysLV)

        setSupportActionBar(toolbarTB)

        imageIV.setOnClickListener {
            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            startActivityForResult(photoPickerIntent, GALLERY_REQUEST)
        }

        saveBTN.setOnClickListener {
            val title = titleET.text.toString()
            val price = priceET.text.toString()
            val image = photoUri.toString()
            val grocery = Grocery(title, price, image)
            grocerys.add(grocery)

            val listAdapter = ListAdapter(this@StoreActivity, grocerys)
            grocerysLV.adapter = listAdapter
            listAdapter.notifyDataSetChanged()
            //bitmap = null
            titleET.text.clear()
            priceET.text.clear()
            imageIV.setImageResource(R.drawable.baseline_shopping_basket_24)
            photoUri = null

        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.store_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {R.id.exit -> finishAffinity() }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK) {
            photoUri = data?.data
            imageIV.setImageURI(photoUri)
        }
    }

}