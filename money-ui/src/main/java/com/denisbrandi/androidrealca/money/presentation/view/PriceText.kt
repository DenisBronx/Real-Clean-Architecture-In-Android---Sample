package com.denisbrandi.androidrealca.money.presentation.view

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.denisbrandi.androidrealca.money.domain.model.Money
import com.denisbrandi.androidrealca.money.presentation.presenter.MoneyPresenter

@Composable
fun PriceText(
    money: Money
) {
    Text(
        text = MoneyPresenter.format(money),
        style = MaterialTheme.typography.bodyMedium
    )
}
