package com.denisbrandi.androidrealca.main.presentation.viewmodel

import com.denisbrandi.androidrealca.viewmodel.StateViewModel

internal interface MainViewModel : StateViewModel<MainScreenState>

internal data class MainScreenState(val wishlistBadge: Int = 0, val cartBadge: Int = 0)
