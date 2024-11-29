package com.example.grocerystore

import java.io.Serializable

class Grocery(
    var title: String,
    var price: String,
    var description: String,
    var image: String?
) : Serializable