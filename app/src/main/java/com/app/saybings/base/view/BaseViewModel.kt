package com.app.saybings.base.view

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

abstract class BaseViewModel {

    protected val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
}