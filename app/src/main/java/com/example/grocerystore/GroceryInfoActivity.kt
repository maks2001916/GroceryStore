package com.example.grocerystore

import android.content.Intent
import android.net.Uri
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

        var grocery: Grocery = intent.extras?.getSerializable("grocery") as Grocery
        val grocerys = intent.getSerializableExtra("grocerys")
        val item = intent.extras?.getInt("item")
        var check = intent.extras?.getBoolean("check")

        val title = grocery.title
        val price = grocery.price
        val description = grocery.description
        val image: Uri? = Uri.parse(grocery.image)
        titleET.setText(title)
        priceET.setText(price)
        descriptionET.setText(description)
        imageIV.setImageURI(image)


        saveBTN.setOnClickListener {
            var newGrocery = Grocery(
                titleET.text.toString(),
                priceET.text.toString(),
                descriptionET.text.toString(),
                grocery.image
            )
            val list: MutableList<Grocery> = grocerys as MutableList<Grocery>

            if (item != null) {
                swap(item, grocery, grocerys)
            }
            check = false

            var resultIntent = Intent(this, StoreActivity::class.java)

            resultIntent.putExtra("list", list as ArrayList<Grocery>)
            //resultIntent.putExtra("price", priceET.text.toString())
            //resultIntent.putExtra("description", descriptionET.text.toString())
            //resultIntent.putExtra("image", imageIV.tag?.toString())
            resultIntent.putExtra("newCheck", check)
            startActivity(intent)
            //finish()
            //setResult(RESULT_OK, resultIntent)

        }
        toReturnBTN.setOnClickListener { finish() }
    }

    private fun swap(item: Int, grocery: Grocery, grocerys: MutableList<Grocery>) {
        grocerys.add(item+1, grocery)
        grocerys.removeAt(item)
    }
}