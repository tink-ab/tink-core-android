package com.tink.service.insight

import com.tink.model.insights.InsightData
import com.tink.model.misc.Amount
import com.tink.model.misc.ExactNumber
import com.tink.rest.models.ActionableInsight
import com.tink.rest.models.InsightData as InsightDataDto
import com.tink.rest.tools.GeneratedCodeConverters
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class InsightDataConvertersTest {

    private val insightJson = "{\n" +
            "    \"createdTime\": 1549976786000,\n" +
            "    \"data\": {\n" +
            "      \"type\": \"ACCOUNT_BALANCE_LOW\",\n" +
            "      \"accountId\": \"c6f26025fbb949a08348e2f73f0ae12c\",\n" +
            "      \"balance\": {\n" +
            "        \"currencyCode\": \"EUR\",\n" +
            "        \"amount\": 2.42\n" +
            "        }\n" +
            "    },\n" +
            "    \"description\": \"The balance on your bank account x is low. \\nDo you want to transfer money to this account?\",\n" +
            "    \"id\": \"e2b746ed27c542ce846a8d693474df21\",\n" +
            "    \"insightActions\": [\n" +
            "      {\n" +
            "        \"data\": {\n" +
            "          \"type\": \"CREATE_TRANSFER\",\n" +
            "          \"sourceAccount\": \"iban://SE9832691627751644451227\",\n" +
            "          \"destinationAccount\": \"iban://NL41INGB1822913977\",\n" +
            "          \"amount\": {\n" +
            "               \"currencyCode\": \"EUR\",\n" +
            "               \"amount\": 30.00\n" +
            "           },\n" +
            "           \"sourceAccountNumber\": \"1234567890\",\n" +
            "           \"destinationAccountNumber\": \"1234098765\"" +
            "           },\n" +
            "          \"label\": \"Make transfer\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"title\": \"Your balance on bank account x is low\",\n" +
            "    \"type\": \"ACCOUNT_BALANCE_LOW\",\n" +
            "    \"userId\": \"d9f134ee2eb44846a4e02990ecc8d32e\"\n" +
            "  }"

    @Test
    internal fun `account balance low data conversion`() {

        val insightDto = GeneratedCodeConverters.moshi
            .adapter(ActionableInsight::class.java)
            .fromJson(insightJson)

        val data = insightDto!!.data!!.toCoreModel() as InsightData.AccountBalanceLowData

        assertThat(data.balance).isEqualTo(Amount(ExactNumber(2.42), "EUR"))
        assertThat(data.accountId).isEqualTo("c6f26025fbb949a08348e2f73f0ae12c")
    }

    private val insightWithBudgetCloseNegativeData = "{\n" +
            "  \"createdTime\": 1549976786000,\n" +
            "  \"data\": {\n" +
            "    \"type\": \"BUDGET_CLOSE_NEGATIVE\",\n" +
            "      \"budgetId\": \"cbbac116e43c4b21b7013091ec03d590\",\n" +
            "      \"budgetPeriod\": {\n" +
            "        \"start\": 1567296000000,\n" +
            "        \"end\": 1569887999999,\n" +
            "        \"spentAmount\": {\n" +
            "          \"currencyCode\": \"EUR\",\n" +
            "          \"amount\": 114.31\n" +
            "        },\n" +
            "        \"budgetAmount\": {\n" +
            "          \"currencyCode\": \"EUR\",\n" +
            "          \"amount\": 120.00\n" +
            "        }\n" +
            "      },\n" +
            "      \"currentTime\": 1569593745000,\n" +
            "      \"periodUnit\": \"MONTH\"\n" +
            "  },\n" +
            "  \"description\": \"The balance on your bank account x is low. \\nDo you want to transfer money to this account?\",\n" +
            "  \"id\": \"e2b746ed27c542ce846a8d693474df21\",\n" +
            "  \"insightActions\": [\n" +
            "  ],\n" +
            "  \"title\": \"Your balance on bank account x is low\",\n" +
            "  \"type\": \"ACCOUNT_BALANCE_LOW\",\n" +
            "  \"userId\": \"d9f134ee2eb44846a4e02990ecc8d32e\"\n" +
            "}\n"

    @Test
    internal fun `budget close negative data conversion`() {
        val insightDto = GeneratedCodeConverters.moshi
            .adapter(ActionableInsight::class.java)
            .fromJson(insightWithBudgetCloseNegativeData)

        val data = (insightDto!!.data as InsightDataDto.BudgetCloseNegativeData)

        assertThat(data.budgetId).isEqualTo("cbbac116e43c4b21b7013091ec03d590")
        assertThat(data.budgetPeriod.budgetAmount.currencyCode).isEqualTo("EUR")
        assertThat(data.budgetPeriod.budgetAmount.amount).isEqualTo(120.0)
        assertThat(data.budgetPeriod.spentAmount.currencyCode).isEqualTo("EUR")
        assertThat(data.budgetPeriod.spentAmount.amount).isEqualTo(114.31)
        assertThat(data.budgetPeriod.start).isEqualTo(1567296000000)
        assertThat(data.budgetPeriod.end).isEqualTo(1569887999999)
        assertThat(data.currentTime).isEqualTo(1569593745000)
        assertThat(data.periodUnit).isEqualTo("MONTH")
    }

    private val monthlySummaryExpensesByCategoryDataJson = "{\n" +
            "  \"type\": \"MONTHLY_SUMMARY_EXPENSES_BY_CATEGORY\",\n" +
            "    \"month\": {\n" +
            "      \"month\": 1,\n" +
            "      \"year\": 2020 \n" +
            "    },\n" +
            "    \"expensesByCategory\": [\n" +
            "      {\n" +
            "        \"categoryCode\": \"expenses:food.coffee\",\n" +
            "        \"spentAmount\": {\n" +
            "          \"currencyCode\": \"EUR\",\n" +
            "          \"amount\": 28.0\n" +
            "        }\n" +
            "      },\n" +
            "      {\n" +
            "        \"categoryCode\": \"expenses:food.groceries\",\n" +
            "        \"spentAmount\": {\n" +
            "          \"currencyCode\": \"EUR\",\n" +
            "          \"amount\": 115.75\n" +
            "        }\n" +
            "      }\n" +
            "    ]\n" +
            "}"

    @Test
    fun `monthly summary expenses by category conversion`() {

        val dataDto = GeneratedCodeConverters.moshi
            .adapter(InsightDataDto::class.java)
            .fromJson(monthlySummaryExpensesByCategoryDataJson)

        assert(dataDto is InsightDataDto.MonthlySummaryExpensesByCategoryData)
    }

    private val insightDataWithUnknownType = "{\n" +
            "  \"type\": \"RANDOM_TYPE_SPQR\",\n" +
            "    \"menu\": {\n" +
            "      \"id\": \"file\",\n" +
            "      \"value\": \"File\",\n" +
            "      \"popup\": {\n" +
            "        \"menuitem\": [\n" +
            "          {\"value\": \"New\", \"onclick\": \"CreateNewDoc()\"},\n" +
            "          {\"value\": \"Open\", \"onclick\": \"OpenDoc()\"},\n" +
            "          {\"value\": \"Close\", \"onclick\": \"CloseDoc()\"}\n" +
            "        ]\n" +
            "      }\n" +
            "    }\n" +
            "}\n"

    @Test
    fun `unknown type can contain any type of data`() {

        val dataDto = GeneratedCodeConverters.moshi
            .adapter(InsightDataDto::class.java)
            .fromJson(insightDataWithUnknownType)

        assert(dataDto is InsightDataDto.Unknown)
    }

    private val budgetSuggestCreateTopCategoryInsightJson =
        """
         {
          "id" : "a0f465d81f414eefada4ad0dcab3a1fe",
          "userId" : "89b7858c052c4fd88024e4581dab6506",
          "type" : "BUDGET_SUGGEST_CREATE_TOP_CATEGORY",
          "title" : "Set up a budget for your top expense: Restaurants.",
          "description" : "You spent SEK 20,030 on Restaurants last month. How about setting up a budget of SEK 18,027 to help save more money?",
          "data" : {
            "categorySpending" : {
              "categoryCode" : "expenses:food.restaurants",
              "spentAmount" : {
                "currencyCode" : "SEK",
                "amount" : 20030.0
              }
            },
            "suggestedBudgetAmount" : {
              "currencyCode" : "SEK",
              "amount" : 18027.0
            },
            "type" : "BUDGET_SUGGEST_CREATE_TOP_CATEGORY"
          },
          "createdTime" : 1602230327079,
          "insightActions" : [ {
            "label" : "Create Budget",
            "data" : {
              "budgetSuggestion" : {
                "filter" : {
                  "accounts" : null,
                  "categories" : [ "expenses:food.restaurants" ]
                },
                "periodicityType" : "BUDGET_PERIODICITY_TYPE_RECURRING",
                "oneOffPeriodicityData" : null,
                "recurringPeriodicityData" : {
                  "periodUnit" : "MONTH"
                },
                "amount" : {
                  "currencyCode" : "SEK",
                  "amount" : 18027.0
                }
              },
              "type" : "CREATE_BUDGET"
            }
          }, {
            "label" : "Archive",
            "data" : {
              "type" : "DISMISS"
            }
          } ]
        }
        """.trimIndent()

    @Test
    fun `budget suggest create top category conversion`() {
        val insightDto = GeneratedCodeConverters.moshi
            .adapter(ActionableInsight::class.java)
            .fromJson(budgetSuggestCreateTopCategoryInsightJson)

        val data = (insightDto!!.data as InsightDataDto.BudgetSuggestCreateTopCategoryData)
        val categorySpending = data.categorySpending.spentAmount
        val suggestedBudgetAmount = data.suggestedBudgetAmount
        assertThat(categorySpending.amount).isEqualTo(20030.0)
        assertThat(categorySpending.currencyCode).isEqualTo("SEK")
        assertThat(suggestedBudgetAmount.amount).isEqualTo(18027.0)
        assertThat(suggestedBudgetAmount.currencyCode).isEqualTo("SEK")
    }

    private val budgetSuggestCreateTopPrimaryCategoryInsightJson =
        """
        {
          "id" : "a0f465d81f414eefada4ad0dcab3a1fe",
          "userId" : "89b7858c052c4fd88024e4581dab6506",
          "type" : "BUDGET_SUGGEST_CREATE_TOP_PRIMARY_CATEGORY",
          "title" : "Set up a budget for your top expense: Restaurants.",
          "description" : "You spent SEK 20,030 on Restaurants last month. How about setting up a budget of SEK 18,027 to help save more money?",
          "data" : {
            "categorySpending" : {
              "categoryCode" : "expenses:food.restaurants",
              "spentAmount" : {
                "currencyCode" : "SEK",
                "amount" : 20030.0
              }
            },
            "suggestedBudgetAmount" : {
              "currencyCode" : "SEK",
              "amount" : 18027.0
            },
            "type" : "BUDGET_SUGGEST_CREATE_TOP_PRIMARY_CATEGORY"
          },
          "createdTime" : 1602230327079,
          "insightActions" : [ {
            "label" : "Create Budget",
            "data" : {
              "budgetSuggestion" : {
                "filter" : {
                  "accounts" : null,
                  "categories" : [ "expenses:food.restaurants" ]
                },
                "periodicityType" : "BUDGET_PERIODICITY_TYPE_RECURRING",
                "oneOffPeriodicityData" : null,
                "recurringPeriodicityData" : {
                  "periodUnit" : "MONTH"
                },
                "amount" : {
                  "currencyCode" : "SEK",
                  "amount" : 18027.0
                }
              },
              "type" : "CREATE_BUDGET"
            }
          }, {
            "label" : "Archive",
            "data" : {
              "type" : "DISMISS"
            }
          } ]
        }
        """.trimIndent()

    @Test
    fun `budget suggest create top primary category conversion`() {
        val insightDto = GeneratedCodeConverters.moshi
            .adapter(ActionableInsight::class.java)
            .fromJson(budgetSuggestCreateTopPrimaryCategoryInsightJson)

        val data = (insightDto!!.data as InsightDataDto.BudgetSuggestCreateTopPrimaryCategoryData)
        val categorySpending = data.categorySpending.spentAmount
        val suggestedBudgetAmount = data.suggestedBudgetAmount
        assertThat(categorySpending.amount).isEqualTo(20030.0)
        assertThat(categorySpending.currencyCode).isEqualTo("SEK")
        assertThat(suggestedBudgetAmount.amount).isEqualTo(18027.0)
        assertThat(suggestedBudgetAmount.currencyCode).isEqualTo("SEK")
    }

    private val budgetSuggestCreateFirstInsightJson =
        """
            {
              "id" : "c78aceecbc67459b9494ff934e0f6abc",
              "userId" : "89b7858c052c4fd88024e4581dab6506",
              "type" : "BUDGET_SUGGEST_CREATE_FIRST",
              "title" : "Set up your first budget to help you keep track of expenses.",
              "description" : "Creating budgets can help you stay on top of your spending – give it a go.",
              "data" : {
                "type" : "BUDGET_SUGGEST_CREATE_FIRST"
              },
              "createdTime" : 1602230327090,
              "insightActions" : [ {
                "label" : "Create Budget",
                "data" : {
                  "budgetSuggestion" : {
                    "filter" : null,
                    "periodicityType" : "BUDGET_PERIODICITY_TYPE_RECURRING",
                    "oneOffPeriodicityData" : null,
                    "recurringPeriodicityData" : {
                      "periodUnit" : "MONTH"
                    },
                    "amount" : null
                  },
                  "type" : "CREATE_BUDGET"
                }
              }, {
                "label" : "Archive",
                "data" : {
                  "type" : "DISMISS"
                }
              } ]
            }
        """.trimIndent()

    @Test
    fun `budget suggest create first conversion`() {
        val insightDto = GeneratedCodeConverters.moshi
            .adapter(ActionableInsight::class.java)
            .fromJson(budgetSuggestCreateFirstInsightJson)
        assertThat(insightDto!!.data is InsightDataDto.BudgetSuggestCreateFirstData)
    }
}
