package com.tink.service.insight

internal const val insightTestList = "[\n" +
        "  {\n" +
        "    \"id\": \"7d020f77e057442d8edb64e6c75d2e10\",\n" +
        "    \"userId\": \"0f0ced014ec041b5bc1bdc1f96d9d607\",\n" +
        "    \"type\": \"BUDGET_SUGGEST_CREATE_FIRST\",\n" +
        "    \"title\": \"Set up your first budget to help you keep track of expenses.\",\n" +
        "    \"description\": \"Creating budgets can help you stay on top of your spending – give it a go.\",\n" +
        "    \"data\": {\n" +
        "      \"type\": \"BUDGET_SUGGEST_CREATE_FIRST\"\n" +
        "    },\n" +
        "    \"createdTime\": 1596107611568,\n" +
        "    \"insightActions\": [\n" +
        "      {\n" +
        "        \"label\": \"Create Budget\",\n" +
        "        \"data\": {\n" +
        "          \"budgetSuggestion\": {\n" +
        "            \"filter\": null,\n" +
        "            \"periodicityType\": \"BUDGET_PERIODICITY_TYPE_RECURRING\",\n" +
        "            \"oneOffPeriodicityData\": null,\n" +
        "            \"recurringPeriodicityData\": {\n" +
        "              \"periodUnit\": \"MONTH\"\n" +
        "            },\n" +
        "            \"amount\": null\n" +
        "          },\n" +
        "          \"type\": \"CREATE_BUDGET\"\n" +
        "        }\n" +
        "      },\n" +
        "      {\n" +
        "        \"label\": \"Archive\",\n" +
        "        \"data\": {\n" +
        "          \"type\": \"DISMISS\"\n" +
        "        }\n" +
        "      }\n" +
        "    ]\n" +
        "  },\n" +
        "  {\n" +
        "    \"id\": \"9207d650bff24743aac17a4dff06ad68\",\n" +
        "    \"userId\": \"0f0ced014ec041b5bc1bdc1f96d9d607\",\n" +
        "    \"type\": \"WEEKLY_SUMMARY_EXPENSE_TRANSACTIONS\",\n" +
        "    \"title\": \"Here’s a summary of your expense transactions last week\",\n" +
        "    \"description\": \"€38.95 in total expenses. \\n15 transactions last week. Most common was Spending 1(7 times). \\n€9.55 was your largest expense, to Taco Bell.\",\n" +
        "    \"data\": {\n" +
        "      \"week\": {\n" +
        "        \"year\": 2020,\n" +
        "        \"week\": 30\n" +
        "      },\n" +
        "      \"transactionSummary\": {\n" +
        "        \"totalExpenses\": {\n" +
        "          \"currencyCode\": \"EUR\",\n" +
        "          \"amount\": 38.95\n" +
        "        },\n" +
        "        \"commonTransactionsOverview\": {\n" +
        "          \"totalNumberOfTransactions\": 15,\n" +
        "          \"mostCommonTransactionDescription\": \"Spending 1\",\n" +
        "          \"mostCommonTransactionCount\": 7\n" +
        "        },\n" +
        "        \"largestExpense\": {\n" +
        "          \"id\": \"a45e18fd4b064d949c65ad9da5d1682c\",\n" +
        "          \"date\": 1595757600000,\n" +
        "          \"amount\": {\n" +
        "            \"currencyCode\": \"EUR\",\n" +
        "            \"amount\": 9.55\n" +
        "          },\n" +
        "          \"description\": \"Taco Bell\"\n" +
        "        }\n" +
        "      },\n" +
        "      \"type\": \"WEEKLY_SUMMARY_EXPENSE_TRANSACTIONS\"\n" +
        "    },\n" +
        "    \"createdTime\": 1596107611525,\n" +
        "    \"insightActions\": [\n" +
        "      {\n" +
        "        \"label\": \"See details\",\n" +
        "        \"data\": {\n" +
        "          \"transactionIds\": [\n" +
        "            {\n" +
        "              \"id\": \"a45e18fd4b064d949c65ad9da5d1682c\",\n" +
        "              \"type\": \"TRANSACTION\"\n" +
        "            },\n" +
        "            {\n" +
        "              \"id\": \"aac02d1587a94003b0ff6a77e6eba642\",\n" +
        "              \"type\": \"TRANSACTION\"\n" +
        "            },\n" +
        "            {\n" +
        "              \"id\": \"f439a845803340bd85f1a68d59c79ebe\",\n" +
        "              \"type\": \"TRANSACTION\"\n" +
        "            },\n" +
        "            {\n" +
        "              \"id\": \"ed2e0416ed5c4c28b814c4c3ffaf38a5\",\n" +
        "              \"type\": \"TRANSACTION\"\n" +
        "            },\n" +
        "            {\n" +
        "              \"id\": \"fa5b262eaf184a5b89645e6a600380a4\",\n" +
        "              \"type\": \"TRANSACTION\"\n" +
        "            },\n" +
        "            {\n" +
        "              \"id\": \"3e0582a7dbcc407c8270da0ec3e702da\",\n" +
        "              \"type\": \"TRANSACTION\"\n" +
        "            },\n" +
        "            {\n" +
        "              \"id\": \"66b4d7062ef34330814d78734a8c3538\",\n" +
        "              \"type\": \"TRANSACTION\"\n" +
        "            },\n" +
        "            {\n" +
        "              \"id\": \"850d3d7468954b378e6e6099e791a66e\",\n" +
        "              \"type\": \"TRANSACTION\"\n" +
        "            },\n" +
        "            {\n" +
        "              \"id\": \"d5cac8f4b0704e738032c889c8df3660\",\n" +
        "              \"type\": \"TRANSACTION\"\n" +
        "            },\n" +
        "            {\n" +
        "              \"id\": \"137f20d5eec84de88add6c4d907a68dc\",\n" +
        "              \"type\": \"TRANSACTION\"\n" +
        "            },\n" +
        "            {\n" +
        "              \"id\": \"e70c9c0c3df04521a7cec08bda0a7c49\",\n" +
        "              \"type\": \"TRANSACTION\"\n" +
        "            },\n" +
        "            {\n" +
        "              \"id\": \"0a3aca5d51824bbbba21998c7b4f1b16\",\n" +
        "              \"type\": \"TRANSACTION\"\n" +
        "            },\n" +
        "            {\n" +
        "              \"id\": \"714e2c202be34b928c0ab7389ff89d88\",\n" +
        "              \"type\": \"TRANSACTION\"\n" +
        "            },\n" +
        "            {\n" +
        "              \"id\": \"451908735ffb439599794db8dadae96b\",\n" +
        "              \"type\": \"TRANSACTION\"\n" +
        "            },\n" +
        "            {\n" +
        "              \"id\": \"f8be89ea04404538bd7e4ae280ee0d64\",\n" +
        "              \"type\": \"TRANSACTION\"\n" +
        "            }\n" +
        "          ],\n" +
        "          \"type\": \"VIEW_TRANSACTIONS\"\n" +
        "        }\n" +
        "      },\n" +
        "      {\n" +
        "        \"label\": \"Archive\",\n" +
        "        \"data\": {\n" +
        "          \"type\": \"DISMISS\"\n" +
        "        }\n" +
        "      }\n" +
        "    ]\n" +
        "  },\n" +
        "  {\n" +
        "    \"id\": \"b091f40783db4a37ae02846c22ecc560\",\n" +
        "    \"userId\": \"0f0ced014ec041b5bc1bdc1f96d9d607\",\n" +
        "    \"type\": \"WEEKLY_UNCATEGORIZED_TRANSACTIONS\",\n" +
        "    \"title\": \"Help us categorise some of last week's transactions\",\n" +
        "    \"description\": \"One recent transaction is missing a category.\",\n" +
        "    \"data\": {\n" +
        "      \"transactionIds\": [\n" +
        "        \"a45e18fd4b064d949c65ad9da5d1682c\"\n" +
        "      ],\n" +
        "      \"week\": {\n" +
        "        \"year\": 2020,\n" +
        "        \"week\": 30\n" +
        "      },\n" +
        "      \"type\": \"WEEKLY_UNCATEGORIZED_TRANSACTIONS\"\n" +
        "    },\n" +
        "    \"createdTime\": 1596107611517,\n" +
        "    \"insightActions\": [\n" +
        "      {\n" +
        "        \"label\": \"Categorise\",\n" +
        "        \"data\": {\n" +
        "          \"transactionIds\": [\n" +
        "            \"a45e18fd4b064d949c65ad9da5d1682c\"\n" +
        "          ],\n" +
        "          \"type\": \"CATEGORIZE_TRANSACTIONS\"\n" +
        "        }\n" +
        "      },\n" +
        "      {\n" +
        "        \"label\": \"No, thanks\",\n" +
        "        \"data\": {\n" +
        "          \"type\": \"DISMISS\"\n" +
        "        }\n" +
        "      }\n" +
        "    ]\n" +
        "  },\n" +
        "  {\n" +
        "    \"id\": \"c09bab4e967a411d8fc967842ac29355\",\n" +
        "    \"userId\": \"0f0ced014ec041b5bc1bdc1f96d9d607\",\n" +
        "    \"type\": \"WEEKLY_SUMMARY_EXPENSES_BY_DAY\",\n" +
        "    \"title\": \"Here’s your spending per day last week\",\n" +
        "    \"description\": \"Expenses last week.\",\n" +
        "    \"data\": {\n" +
        "      \"week\": {\n" +
        "        \"year\": 2020,\n" +
        "        \"week\": 30\n" +
        "      },\n" +
        "      \"expenseStatisticsByDay\": [\n" +
        "        {\n" +
        "          \"date\": [\n" +
        "            2020,\n" +
        "            7,\n" +
        "            20\n" +
        "          ],\n" +
        "          \"expenseStatistics\": {\n" +
        "            \"totalAmount\": {\n" +
        "              \"currencyCode\": \"EUR\",\n" +
        "              \"amount\": 4.2\n" +
        "            },\n" +
        "            \"averageAmount\": {\n" +
        "              \"currencyCode\": \"EUR\",\n" +
        "              \"amount\": 3.7\n" +
        "            }\n" +
        "          }\n" +
        "        },\n" +
        "        {\n" +
        "          \"date\": [\n" +
        "            2020,\n" +
        "            7,\n" +
        "            21\n" +
        "          ],\n" +
        "          \"expenseStatistics\": {\n" +
        "            \"totalAmount\": {\n" +
        "              \"currencyCode\": \"EUR\",\n" +
        "              \"amount\": 4.2\n" +
        "            },\n" +
        "            \"averageAmount\": {\n" +
        "              \"currencyCode\": \"EUR\",\n" +
        "              \"amount\": 3.7\n" +
        "            }\n" +
        "          }\n" +
        "        },\n" +
        "        {\n" +
        "          \"date\": [\n" +
        "            2020,\n" +
        "            7,\n" +
        "            22\n" +
        "          ],\n" +
        "          \"expenseStatistics\": {\n" +
        "            \"totalAmount\": {\n" +
        "              \"currencyCode\": \"EUR\",\n" +
        "              \"amount\": 4.2\n" +
        "            },\n" +
        "            \"averageAmount\": {\n" +
        "              \"currencyCode\": \"EUR\",\n" +
        "              \"amount\": 13.0\n" +
        "            }\n" +
        "          }\n" +
        "        },\n" +
        "        {\n" +
        "          \"date\": [\n" +
        "            2020,\n" +
        "            7,\n" +
        "            23\n" +
        "          ],\n" +
        "          \"expenseStatistics\": {\n" +
        "            \"totalAmount\": {\n" +
        "              \"currencyCode\": \"EUR\",\n" +
        "              \"amount\": 4.2\n" +
        "            },\n" +
        "            \"averageAmount\": {\n" +
        "              \"currencyCode\": \"EUR\",\n" +
        "              \"amount\": 4.9\n" +
        "            }\n" +
        "          }\n" +
        "        },\n" +
        "        {\n" +
        "          \"date\": [\n" +
        "            2020,\n" +
        "            7,\n" +
        "            24\n" +
        "          ],\n" +
        "          \"expenseStatistics\": {\n" +
        "            \"totalAmount\": {\n" +
        "              \"currencyCode\": \"EUR\",\n" +
        "              \"amount\": 4.2\n" +
        "            },\n" +
        "            \"averageAmount\": {\n" +
        "              \"currencyCode\": \"EUR\",\n" +
        "              \"amount\": 5.7\n" +
        "            }\n" +
        "          }\n" +
        "        },\n" +
        "        {\n" +
        "          \"date\": [\n" +
        "            2020,\n" +
        "            7,\n" +
        "            25\n" +
        "          ],\n" +
        "          \"expenseStatistics\": {\n" +
        "            \"totalAmount\": {\n" +
        "              \"currencyCode\": \"EUR\",\n" +
        "              \"amount\": 4.2\n" +
        "            },\n" +
        "            \"averageAmount\": {\n" +
        "              \"currencyCode\": \"EUR\",\n" +
        "              \"amount\": 5.4\n" +
        "            }\n" +
        "          }\n" +
        "        },\n" +
        "        {\n" +
        "          \"date\": [\n" +
        "            2020,\n" +
        "            7,\n" +
        "            26\n" +
        "          ],\n" +
        "          \"expenseStatistics\": {\n" +
        "            \"totalAmount\": {\n" +
        "              \"currencyCode\": \"EUR\",\n" +
        "              \"amount\": 13.75\n" +
        "            },\n" +
        "            \"averageAmount\": {\n" +
        "              \"currencyCode\": \"EUR\",\n" +
        "              \"amount\": 13.75\n" +
        "            }\n" +
        "          }\n" +
        "        }\n" +
        "      ],\n" +
        "      \"type\": \"WEEKLY_SUMMARY_EXPENSES_BY_DAY\"\n" +
        "    },\n" +
        "    \"createdTime\": 1596107611551,\n" +
        "    \"insightActions\": [\n" +
        "      {\n" +
        "        \"label\": \"OK, good to know\",\n" +
        "        \"data\": {\n" +
        "          \"type\": \"ACKNOWLEDGE\"\n" +
        "        }\n" +
        "      }\n" +
        "    ]\n" +
        "  },\n" +
        "  {\n" +
        "    \"id\": \"c84d9b6e1d1c4ac6b96c47fc4221a9d8\",\n" +
        "    \"userId\": \"0f0ced014ec041b5bc1bdc1f96d9d607\",\n" +
        "    \"type\": \"NEW_INCOME_TRANSACTION\",\n" +
        "    \"title\": \"You got paid!\",\n" +
        "    \"description\": \"You have received a new income Salary on Jan random\",\n" +
        "    \"data\": {\n" +
        "      \"transactionId\": \"539e60b2ff6842a9a6848b7843cc2546\",\n" +
        "      \"accountId\": \"5e7e7aafb6b8469d84881fece2603234\",\n" +
        "      \"type\": \"NEW_INCOME_TRANSACTION\"\n" +
        "    },\n" +
        "    \"createdTime\": 1596107611523,\n" +
        "    \"insightActions\": [\n" +
        "      {\n" +
        "        \"label\": \"See details\",\n" +
        "        \"data\": {\n" +
        "          \"transactionId\": \"539e60b2ff6842a9a6848b7843cc2546\",\n" +
        "          \"type\": \"VIEW_TRANSACTION\"\n" +
        "        }\n" +
        "      },\n" +
        "      {\n" +
        "        \"label\": \"Archive\",\n" +
        "        \"data\": {\n" +
        "          \"type\": \"DISMISS\"\n" +
        "        }\n" +
        "      }\n" +
        "    ]\n" +
        "  },\n" +
        "  {\n" +
        "    \"id\": \"eaf924fb70ea4fbcad0405f33885427d\",\n" +
        "    \"userId\": \"0f0ced014ec041b5bc1bdc1f96d9d607\",\n" +
        "    \"type\": \"BUDGET_SUGGEST_CREATE_TOP_CATEGORY\",\n" +
        "    \"title\": \"Set up a budget for your top expense: Restaurants.\",\n" +
        "    \"description\": \"You spent €156 on Restaurants last month. How about setting up a budget of €140.4 to help save more money?\",\n" +
        "    \"data\": {\n" +
        "      \"categorySpending\": {\n" +
        "        \"categoryCode\": \"expenses:food.restaurants\",\n" +
        "        \"spentAmount\": {\n" +
        "          \"currencyCode\": \"EUR\",\n" +
        "          \"amount\": 156.0\n" +
        "        }\n" +
        "      },\n" +
        "      \"suggestedBudgetAmount\": {\n" +
        "        \"currencyCode\": \"EUR\",\n" +
        "        \"amount\": 140.4\n" +
        "      },\n" +
        "      \"type\": \"BUDGET_SUGGEST_CREATE_TOP_CATEGORY\"\n" +
        "    },\n" +
        "    \"createdTime\": 1596107611526,\n" +
        "    \"insightActions\": [\n" +
        "      {\n" +
        "        \"label\": \"Create Budget\",\n" +
        "        \"data\": {\n" +
        "          \"budgetSuggestion\": {\n" +
        "            \"filter\": {\n" +
        "              \"accounts\": null,\n" +
        "              \"categories\": [\n" +
        "                \"expenses:food.restaurants\"\n" +
        "              ]\n" +
        "            },\n" +
        "            \"periodicityType\": \"BUDGET_PERIODICITY_TYPE_RECURRING\",\n" +
        "            \"oneOffPeriodicityData\": null,\n" +
        "            \"recurringPeriodicityData\": {\n" +
        "              \"periodUnit\": \"MONTH\"\n" +
        "            },\n" +
        "            \"amount\": {\n" +
        "              \"currencyCode\": \"EUR\",\n" +
        "              \"amount\": 140.4\n" +
        "            }\n" +
        "          },\n" +
        "          \"type\": \"CREATE_BUDGET\"\n" +
        "        }\n" +
        "      },\n" +
        "      {\n" +
        "        \"label\": \"Archive\",\n" +
        "        \"data\": {\n" +
        "          \"type\": \"DISMISS\"\n" +
        "        }\n" +
        "      }\n" +
        "    ]\n" +
        "  },\n" +
        "  {\n" +
        "    \"id\": \"f7f874eb3624489f96ef76125c2265b1\",\n" +
        "    \"userId\": \"0f0ced014ec041b5bc1bdc1f96d9d607\",\n" +
        "    \"type\": \"WEEKLY_SUMMARY_EXPENSES_BY_CATEGORY\",\n" +
        "    \"title\": \"Here’s your spending per category last week\",\n" +
        "    \"description\": \"You spent the most on these categories last week.\",\n" +
        "    \"data\": {\n" +
        "      \"week\": {\n" +
        "        \"year\": 2020,\n" +
        "        \"week\": 30\n" +
        "      },\n" +
        "      \"expensesByCategory\": [\n" +
        "        {\n" +
        "          \"categoryCode\": \"expenses:food.coffee\",\n" +
        "          \"spentAmount\": {\n" +
        "            \"currencyCode\": \"EUR\",\n" +
        "            \"amount\": 7.0\n" +
        "          }\n" +
        "        },\n" +
        "        {\n" +
        "          \"categoryCode\": \"expenses:misc.uncategorized\",\n" +
        "          \"spentAmount\": {\n" +
        "            \"currencyCode\": \"EUR\",\n" +
        "            \"amount\": 9.55\n" +
        "          }\n" +
        "        },\n" +
        "        {\n" +
        "          \"categoryCode\": \"expenses:food.restaurants\",\n" +
        "          \"spentAmount\": {\n" +
        "            \"currencyCode\": \"EUR\",\n" +
        "            \"amount\": 22.4\n" +
        "          }\n" +
        "        }\n" +
        "      ],\n" +
        "      \"type\": \"WEEKLY_SUMMARY_EXPENSES_BY_CATEGORY\"\n" +
        "    },\n" +
        "    \"createdTime\": 1596107611517,\n" +
        "    \"insightActions\": [\n" +
        "      {\n" +
        "        \"label\": \"See details\",\n" +
        "        \"data\": {\n" +
        "          \"transactionIdsByCategory\": {\n" +
        "            \"expenses:misc.uncategorized\": {\n" +
        "              \"transactionIds\": [\n" +
        "                \"a45e18fd4b064d949c65ad9da5d1682c\"\n" +
        "              ]\n" +
        "            },\n" +
        "            \"expenses:food.coffee\": {\n" +
        "              \"transactionIds\": [\n" +
        "                \"f439a845803340bd85f1a68d59c79ebe\",\n" +
        "                \"ed2e0416ed5c4c28b814c4c3ffaf38a5\",\n" +
        "                \"66b4d7062ef34330814d78734a8c3538\",\n" +
        "                \"d5cac8f4b0704e738032c889c8df3660\",\n" +
        "                \"e70c9c0c3df04521a7cec08bda0a7c49\",\n" +
        "                \"0a3aca5d51824bbbba21998c7b4f1b16\",\n" +
        "                \"f8be89ea04404538bd7e4ae280ee0d64\"\n" +
        "              ]\n" +
        "            },\n" +
        "            \"expenses:food.restaurants\": {\n" +
        "              \"transactionIds\": [\n" +
        "                \"aac02d1587a94003b0ff6a77e6eba642\",\n" +
        "                \"fa5b262eaf184a5b89645e6a600380a4\",\n" +
        "                \"3e0582a7dbcc407c8270da0ec3e702da\",\n" +
        "                \"850d3d7468954b378e6e6099e791a66e\",\n" +
        "                \"137f20d5eec84de88add6c4d907a68dc\",\n" +
        "                \"714e2c202be34b928c0ab7389ff89d88\",\n" +
        "                \"451908735ffb439599794db8dadae96b\"\n" +
        "              ]\n" +
        "            }\n" +
        "          },\n" +
        "          \"type\": \"VIEW_TRANSACTIONS_BY_CATEGORY\"\n" +
        "        }\n" +
        "      },\n" +
        "      {\n" +
        "        \"label\": \"Archive\",\n" +
        "        \"data\": {\n" +
        "          \"type\": \"DISMISS\"\n" +
        "        }\n" +
        "      }\n" +
        "    ]\n" +
        "  }\n" +
        "]"

internal const val insightWithBudgetCloseNegativeData = "{\n" +
        "  \"createdTime\": 1549976786000,\n" +
        "  \"data\": {\n" +
        "    \"type\": \"BUDGET_CLOSE_NEGATIVE\",\n" +
        "    \"data\": {\n" +
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
        "    }\n" +
        "  },\n" +
        "  \"description\": \"The balance on your bank account x is low. \\nDo you want to transfer money to this account?\",\n" +
        "  \"id\": \"e2b746ed27c542ce846a8d693474df21\",\n" +
        "  \"insightActions\": [\n" +
        "    {\n" +
        "      \"data\": {\n" +
        "        \"type\": \"CREATE_TRANSFER\"\n" +
        "      },\n" +
        "      \"label\": \"Make transfer\"\n" +
        "    }\n" +
        "  ],\n" +
        "  \"title\": \"Your balance on bank account x is low\",\n" +
        "  \"type\": \"ACCOUNT_BALANCE_LOW\",\n" +
        "  \"userId\": \"d9f134ee2eb44846a4e02990ecc8d32e\"\n" +
        "}\n"
