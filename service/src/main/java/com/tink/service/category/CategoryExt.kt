package com.tink.service.category

import com.tink.model.category.Category
import com.tink.model.category.CategoryTree
import com.tink.rest.models.Category as CategoryDto

fun List<CategoryDto>.toCategoryTree(): CategoryTree? {
    val expenseCategory =
        firstOrNull { it.type == CategoryDto.TypeEnum.EXPENSES && it.parent == null }
            ?.toCategoryRecursively(this)

    val incomeCategory =
        firstOrNull { it.type == CategoryDto.TypeEnum.INCOME && it.parent == null }
            ?.toCategoryRecursively(this)

    val transferCategory =
        firstOrNull { it.type == CategoryDto.TypeEnum.TRANSFERS && it.parent == null }
            ?.toCategoryRecursively(this)

    if (expenseCategory != null && incomeCategory != null && transferCategory != null) {
        return CategoryTree(expenseCategory, incomeCategory, transferCategory)
    }
    return null
}

private fun CategoryDto.toCategoryRecursively(allCategories: List<CategoryDto>): Category {
    val children = allCategories.filter { this.isParentTo(it) }
        .map { it.toCategoryRecursively(allCategories - this) } // remove this to avoid potential circular dependencies

    return Category(
        code = code,
        id = id,
        name = secondaryName ?: primaryName ?: typeName,
        type = type.toType(),
        parentId = parent,
        children = children,
        sortOrder = sortOrder,
        isDefaultChild = defaultChild
    )
}

private fun CategoryDto.TypeEnum.toType() =
    when (this) {
        CategoryDto.TypeEnum.EXPENSES -> Category.Type.EXPENSE
        CategoryDto.TypeEnum.INCOME -> Category.Type.INCOME
        CategoryDto.TypeEnum.TRANSFERS -> Category.Type.TRANSFER
    }

private fun CategoryDto.isParentTo(other: CategoryDto) = other.parent == this.id