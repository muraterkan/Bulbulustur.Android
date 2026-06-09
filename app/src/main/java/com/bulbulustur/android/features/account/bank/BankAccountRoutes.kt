package com.bulbulustur.android.features.account.bank

object BankAccountRoutes {
    const val List = "account/bank-accounts"
    const val Create = "account/bank-accounts/create"
    const val Edit = "account/bank-accounts/edit/{bankAccountId}"

    fun edit(
        bankAccountId: Int
    ): String {
        return "account/bank-accounts/edit/$bankAccountId"
    }
}