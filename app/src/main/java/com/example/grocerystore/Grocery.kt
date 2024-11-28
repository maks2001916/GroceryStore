package com.example.grocerystore

import java.io.Serializable

class Grocery(
    val title: String,
    val price: String,
    val image: String?
) : Serializable