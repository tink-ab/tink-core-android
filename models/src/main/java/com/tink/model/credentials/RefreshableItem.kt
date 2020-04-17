package com.tink.model.credentials

enum class RefreshableItem(val item: String) {
    CHECKING_ACCOUNTS("CHECKING_ACCOUNTS"),
    CHECKING_TRANSACTIONS("CHECKING_TRANSACTIONS"),
    SAVING_ACCOUNTS("SAVING_ACCOUNTS"),
    SAVING_TRANSACTIONS("SAVING_TRANSACTIONS"),
    CREDITCARD_ACCOUNTS("CREDITCARD_ACCOUNTS"),
    CREDITCARD_TRANSACTIONS("CREDITCARD_TRANSACTIONS"),
    LOAN_ACCOUNTS("LOAN_ACCOUNTS"),
    LOAN_TRANSACTIONS("LOAN_TRANSACTIONS"),
    INVESTMENT_ACCOUNTS("INVESTMENT_ACCOUNTS"),
    INVESTMENT_TRANSACTIONS("INVESTMENT_TRANSACTIONS"),
    EINVOICES("EINVOICES"),
    TRANSFER_DESTINATIONS("TRANSFER_DESTINATIONS"),
    IDENTITY_DATA("IDENTITY_DATA");

    companion object {

        private val transactions = setOf(
            CHECKING_TRANSACTIONS,
            SAVING_TRANSACTIONS,
            CREDITCARD_TRANSACTIONS,
            LOAN_TRANSACTIONS,
            INVESTMENT_TRANSACTIONS
        )

        private val accounts = setOf(
            CHECKING_ACCOUNTS,
            SAVING_ACCOUNTS,
            CREDITCARD_ACCOUNTS,
            LOAN_ACCOUNTS,
            INVESTMENT_ACCOUNTS
        )

        private val all = setOf(values())

        fun transactions() = transactions
        fun accounts() = accounts
        fun all() = all
    }
}

operator fun RefreshableItem.plus(other: RefreshableItem) = setOf(this, other)
