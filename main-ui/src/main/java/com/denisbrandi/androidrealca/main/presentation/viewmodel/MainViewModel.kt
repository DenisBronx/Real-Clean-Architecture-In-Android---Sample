package com.denisbrandi.androidrealca.main.presentation.viewmodel

import com.denisbrandi.androidrealca.viewmodel.StateViewModel

internal interface MainViewModel : StateViewModel<MainState>

internal data class MainState(val wishlistBadge: Int = 0, val cartBadge: Int = 0)
