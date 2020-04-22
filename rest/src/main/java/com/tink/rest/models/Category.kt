/**
 * NOTE: This class is auto generated by the Swagger Gradle Codegen for the following API: Tink API
 *
 * More info on this tool is available on https://github.com/Yelp/swagger-gradle-codegen
 */

package com.tink.rest.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Categories are used for categorization of transactions. They are structured as a category tree, and are available as a flat list of categories with parent/child relationships using their id and parent fields. Category information is used for pre-computed statistics, making aggregated spending and income data available for all the different nodes in the category tree. However, a transaction itself, can only be assigned to a leaf category. Both the `INCOME` and `EXPENSES` categories represent users' regular income and spending, while the `TRANSFER` categories are special in the sense that they represent transfers between accounts (potentially across banks), such as regular bank transfers, credit-card payments, mortgage amortizations and other transactions that should not add to the users' actual spending.
 * @property code Machine readable category code.
 * @property defaultChild Indicates if this is the default child to be used when categorizing to a primary level category.
 * @property id The internal identifier of the category, referenced by e.g. a transaction.
 * @property parent The parent internal identifier of this category, or null.
 * @property primaryName The primary name of this category.
 * @property searchTerms Used by the search engine to find transactions with this category.
 * @property secondaryName The secondary name of this category.
 * @property sortOrder Sort order for nicer display for the user.
 * @property type Type of the category.
 * @property typeName Type name of the category.
 */
@JsonClass(generateAdapter = true)
data class Category(
    @Json(name = "code") @field:Json(name = "code") var code: String,
    @Json(name = "defaultChild") @field:Json(name = "defaultChild") var defaultChild: Boolean,
    @Json(name = "id") @field:Json(name = "id") var id: String,
    @Json(name = "sortOrder") @field:Json(name = "sortOrder") var sortOrder: Int,
    @Json(name = "type") @field:Json(name = "type") var type: Category.TypeEnum,
    @Json(name = "typeName") @field:Json(name = "typeName") var typeName: String,
    @Json(name = "parent") @field:Json(name = "parent") var parent: String? = null,
    @Json(name = "primaryName") @field:Json(name = "primaryName") var primaryName: String? = null,
    @Json(name = "searchTerms") @field:Json(name = "searchTerms") var searchTerms: String? = null,
    @Json(name = "secondaryName") @field:Json(name = "secondaryName") var secondaryName: String? = null
) {
    /**
     * Type of the category.
     * Values: INCOME, EXPENSES, TRANSFERS
     */
    @JsonClass(generateAdapter = false)
    enum class TypeEnum(val value: String) {
        @Json(name = "INCOME") INCOME("INCOME"),
        @Json(name = "EXPENSES") EXPENSES("EXPENSES"),
        @Json(name = "TRANSFERS") TRANSFERS("TRANSFERS")
    }
}
