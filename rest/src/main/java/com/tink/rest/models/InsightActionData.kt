/**
 * NOTE: This class is auto generated by the Swagger Gradle Codegen for the following API: Tink API
 *
 * More info on this tool is available on https://github.com/Yelp/swagger-gradle-codegen
 */

package com.tink.rest.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.tink.rest.models.insightdata.AmountWithCurrencyCode
import com.tink.rest.models.insights.actions.BudgetSuggestion
import com.tink.rest.models.insights.actions.TransactionIdWithType
import com.tink.rest.models.insights.actions.TransactionIds

/**
 * @property type The action type
 */
@JsonClass(generateAdapter = false)
sealed class InsightActionData(
    @Json(name = "type") @field:Json(name = "type") var type: InsightActionData.TypeEnum? = null
) {
    /**
     * The action type
     * Values: ACKNOWLEDGE, DISMISS, VIEW_BUDGET, CREATE_BUDGET, CREATE_TRANSFER, VIEW_TRANSACTION, CATEGORIZE_EXPENSE, VIEW_TRANSACTIONS, CATEGORIZE_TRANSACTIONS, VIEW_TRANSACTIONS_BY_CATEGORY, VIEW_EXPECTED_AND_ACTUAL_AMOUNT
     */
    @JsonClass(generateAdapter = false)
    enum class TypeEnum(val value: String) {
        @Json(name = "UNKNOWN")
        UNKNOWN("UNKNOWN"),
        @Json(name = "ACKNOWLEDGE")
        ACKNOWLEDGE("ACKNOWLEDGE"),
        @Json(name = "DISMISS")
        DISMISS("DISMISS"),
        @Json(name = "VIEW_BUDGET")
        VIEW_BUDGET("VIEW_BUDGET"),
        @Json(name = "CREATE_BUDGET")
        CREATE_BUDGET("CREATE_BUDGET"),
        @Json(name = "CREATE_TRANSFER")
        CREATE_TRANSFER("CREATE_TRANSFER"),
        @Json(name = "VIEW_TRANSACTION")
        VIEW_TRANSACTION("VIEW_TRANSACTION"),
        @Json(name = "CATEGORIZE_EXPENSE")
        CATEGORIZE_EXPENSE("CATEGORIZE_EXPENSE"),
        @Json(name = "VIEW_TRANSACTIONS")
        VIEW_TRANSACTIONS("VIEW_TRANSACTIONS"),
        @Json(name = "CATEGORIZE_TRANSACTIONS")
        CATEGORIZE_TRANSACTIONS("CATEGORIZE_TRANSACTIONS"),
        @Json(name = "VIEW_TRANSACTIONS_BY_CATEGORY")
        VIEW_TRANSACTIONS_BY_CATEGORY("VIEW_TRANSACTIONS_BY_CATEGORY"),
        @Json(name = "REFRESH_CREDENTIAL")
        REFRESH_CREDENTIAL("REFRESH_CREDENTIAL")
    }

    @JsonClass(generateAdapter = true)
    data class CreateTransferActionData(
        @Json(name = "amount")
        val amount: AmountWithCurrencyCode? = null,
        @Json(name = "destinationAccount")
        val destinationAccount: String? = null,
        @Json(name = "destinationAccountNumber")
        val destinationAccountNumber: String? = null,
        @Json(name = "sourceAccount")
        val sourceAccount: String? = null,
        @Json(name = "sourceAccountNumber")
        val sourceAccountNumber: String? = null
    ) : InsightActionData(TypeEnum.CREATE_TRANSFER)

    @JsonClass(generateAdapter = true)
    data class ViewBudgetActionData(
        @Json(name = "budgetId")
        val budgetId: String,
        @Json(name = "budgetPeriodStartTime")
        val budgetPeriodStartTime: Long
    ) : InsightActionData(TypeEnum.VIEW_BUDGET)

    @JsonClass(generateAdapter = true)
    data class CreateBudgetActionData(
        @Json(name = "budgetSuggestion")
        val budgetSuggestion: BudgetSuggestion
    ) : InsightActionData(TypeEnum.CREATE_BUDGET)

    @JsonClass(generateAdapter = true)
    data class CategorizeTransactionsActionData(
        @Json(name = "transactionIds")
        val transactionIds: List<String>
    ) : InsightActionData(TypeEnum.CATEGORIZE_TRANSACTIONS)

    @JsonClass(generateAdapter = true)
    data class ViewTransactionsByCategoryActionData(
        @Json(name = "transactionIdsByCategory")
        val transactionIdsByCategory: Map<String, TransactionIds>
    ) : InsightActionData(TypeEnum.VIEW_TRANSACTIONS_BY_CATEGORY)

    @JsonClass(generateAdapter = true)
    data class ViewTransactionActionData(
        @Json(name = "transactionId")
        val transactionId: String
    ) : InsightActionData(TypeEnum.VIEW_TRANSACTION)

    @JsonClass(generateAdapter = true)
    data class CategorizeExpenseActionData(
        @Json(name = "transactionId")
        val transactionId: String
    ) : InsightActionData(TypeEnum.CATEGORIZE_EXPENSE)

    @JsonClass(generateAdapter = true)
    data class ViewTransactionsActionData(
        @Json(name = "transactionIds")
        val transactionIds: List<TransactionIdWithType>
    ) : InsightActionData(TypeEnum.VIEW_TRANSACTIONS)

    @JsonClass(generateAdapter = true)
    data class RefreshCredentialActionData(
        @Json(name = "credentialId")
        val credentialId: String
    ) : InsightActionData(TypeEnum.REFRESH_CREDENTIAL)

    class Acknowledge : InsightActionData(TypeEnum.ACKNOWLEDGE)
    class Dismiss : InsightActionData(TypeEnum.DISMISS)
    object Unknown : InsightActionData(TypeEnum.UNKNOWN)
}
