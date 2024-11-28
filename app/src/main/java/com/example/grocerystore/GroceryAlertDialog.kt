package com.example.grocerystore

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class GroceryAlertDialog: DialogFragment() {

    private var removable: Removable? = null
    private var updatable: Updatable? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        removable = context as Removable?
        updatable = context as Updatable?
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val grocery = requireArguments().getSerializable("grocery")
        val builder = AlertDialog.Builder(
            requireActivity()
        )

        return builder
            .setTitle("Внимание")
            .setMessage("Предполагаемые действия")
            .setPositiveButton("Удалить") { dialog, which ->
                removable?.remove(grocery as Grocery)
            }
            .setNeutralButton("Редактировать") { dialog, which ->
                updatable?.update(grocery as Grocery)
            }
            .setNegativeButton("редактировать") { dialog, which ->
                var infoIntent = Intent(this.context, ProductInfoActivity::class.java)
                startActivity(infoIntent)

            }
            .create()
    }
}