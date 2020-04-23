package com.tink.service.category

import com.tink.model.category.CategoryTree
import com.tink.service.observer.ChangeObserver
import javax.inject.Inject

interface CategoryService {
    fun subscribe(listener: ChangeObserver<CategoryTree>)
    fun unsubscribe(listener: ChangeObserver<CategoryTree>)
    fun refreshCategories()
}

class CategoryServiceImpl @Inject constructor() : CategoryService {
    override fun subscribe(listener: ChangeObserver<CategoryTree>) {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun unsubscribe(listener: ChangeObserver<CategoryTree>) {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun refreshCategories() {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }
}
