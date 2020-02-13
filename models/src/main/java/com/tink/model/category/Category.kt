package com.tink.model.category

data class Category(
    val code: String,
    val id: String,
    val name: String,
    val children: List<Category>,
    val sortOrder: Int,
    val isDefaultChild: Boolean
)

data class CategoryTree(
    val expenses: Category,
    val income: Category,
    val transfers: Category
)