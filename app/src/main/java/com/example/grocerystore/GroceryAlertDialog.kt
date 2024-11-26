package com.example.grocerystore

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class GroceryAlertDialog: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val grocery = requireArguments().getSerializable("grocery")
        val builder = AlertDialog.Builder(
            requireActivity()
        )

        return builder
            .setTitle("Внимание")
            .setMessage("Предполагаемые действия")
            .setPositiveButton("Редактировать") { dialog, which ->
                val infoIntent = Intent(this, ProductInfoActivity::class.java)
            }
            .create()
    }
}