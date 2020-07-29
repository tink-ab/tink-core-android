package com.tink.service.category

import com.tink.model.category.CategoryTree
import com.tink.rest.apis.CategoryApi
import java.util.Locale
import javax.inject.Inject

interface CategoryService {
    suspend fun getCategoryTree(): CategoryTree
}

class CategoryServiceImpl @Inject constructor(
    private val api: CategoryApi
) : CategoryService {

    override suspend fun getCategoryTree(): CategoryTree =
        api.list(Locale.getDefault().language).toCategoryTree()
            ?: throw IllegalStateException("Couldn't create CategoryTree")
}
