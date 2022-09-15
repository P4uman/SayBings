package com.app.saybings

import android.app.Application
import com.app.saybings.common.composition.AppCompositionRoot

private const val DATABASE_NAME = "SayBingsDB"

class SayBingsApplication : Application() {

    lateinit var appCompositionRoot: AppCompositionRoot

    override fun onCreate() {
        appCompositionRoot = AppCompositionRoot(this, DATABASE_NAME)
        super.onCreate()
    }
}