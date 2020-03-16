package com.tink.service.category

import com.tink.model.category.CategoryTree
import com.tink.service.observer.ChangeObserver

interface CategoryService {
    fun subscribe(listener: ChangeObserver<CategoryTree>)
    fun unsubscribe(listener: ChangeObserver<CategoryTree>)
    fun refreshCategories()
}
