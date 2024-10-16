package com.denisbrandi.androidrealca.money.presentation.presenter

import com.denisbrandi.androidrealca.money.domain.model.Money
import kotlin.math.floor

object MoneyPresenter {
    fun format(money: Money): String {
        return "${money.currencySymbol}${floor(money.amount * 100) / 100}"
    }
}
