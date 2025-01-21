package com.example.grocerystore

import android.annotation.SuppressLint
import android.content.ClipDescription
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class StoreActivity : AppCompatActivity(), Removable, Updatable {

    var grocery: Grocery? = null

    private val GALLERY_REQUEST = 302
    var photoUri: Uri? = null
    var listAdapter: ListAdapter? = null
    var grocerys: MutableList<Grocery> = mutableListOf()

    var item: Int? = null
    var check = true

    private lateinit var toolbarTB: Toolbar
    private lateinit var imageIV: ImageView
    private lateinit var titleET: EditText
    private lateinit var priceET: EditText
    private lateinit var description: EditText
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
        description = findViewById(R.id.descriptionET)
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
            val description = description.text.toString()
            grocery = Grocery(title, price, description, image)
            grocerys.add(grocery!!)
            listAdapter = ListAdapter(this@StoreActivity, grocerys)
            grocerysLV.adapter = listAdapter
            listAdapter!!.notifyDataSetChanged()
            titleET.text.clear()
            priceET.text.clear()
            imageIV.setImageResource(R.drawable.baseline_shopping_basket_24)
            photoUri = null

        }

        grocerysLV.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                grocery = listAdapter!!.getItem(position)
                item = position
                val dialog = GroceryAlertDialog()
                val args = Bundle()
                args.putSerializable("grocery", grocery)
                dialog.arguments = args
                dialog.show(supportFragmentManager, "grocery")//custom
            }

    }

    private val launchSomeActivity = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
            result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            val item = data!!.getIntExtra("item", 0)
            grocerys[item].title = data.getStringExtra("title").toString()
            grocerys[item].price = data.getStringExtra("price").toString()
            grocerys[item].description = data.getStringExtra("description").toString()
            grocerys[item].image = data.getStringExtra("image").toString()

        } else {

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

    override fun remove(grocery: Grocery) {
        listAdapter?.remove(grocery)
    }

    override fun update(grocery: Grocery) {
        var intentInfo = Intent(this, GroceryInfoActivity::class.java)
        intentInfo.putExtra("grocery", grocery)
        intentInfo.putExtra("grocerys", this.grocerys as ArrayList<Grocery>)
        intentInfo.putExtra("position", item)
        intentInfo.putExtra("check", check)
        //startActivity(intentInfo)
        launchSomeActivity.launch(intentInfo)
    }

}