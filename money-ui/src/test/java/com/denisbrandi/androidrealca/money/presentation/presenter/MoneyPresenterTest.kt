package com.denisbrandi.androidrealca.money.presentation.presenter

import com.denisbrandi.androidrealca.money.domain.model.Money
import org.junit.Assert.assertEquals
import org.junit.Test

class MoneyPresenterTest {

    private val sut = MoneyPresenter

    @Test
    fun `EXPECT price formatted`() {
        val result = sut.format(Money(99.999999, "$"))

        assertEquals("$99.99", result)
    }
}
