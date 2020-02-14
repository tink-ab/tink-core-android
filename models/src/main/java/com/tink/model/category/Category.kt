package com.tink.model.category

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Category(
    val code: String,
    val id: String,
    val name: String,
    val children: List<Category>,
    val sortOrder: Int,
    val isDefaultChild: Boolean
) : Parcelable

@Parcelize
data class CategoryTree(
    val expenses: Category,
    val income: Category,
    val transfers: Category
) : Parcelable
