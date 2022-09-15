package com.app.saybings.base.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import com.app.saybings.SayBingsApplication

abstract class BaseActivity<T: ViewDataBinding> : AppCompatActivity() {

    protected lateinit var binding: T
    val compositionRoot get() =  (application as SayBingsApplication).appCompositionRoot

    abstract fun getDataBinding(): T

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = getDataBinding()
        setContentView(binding.root)
        super.onCreate(savedInstanceState)
    }
}