package com.app.saybings.base.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import com.app.saybings.SayBingsApplication

abstract class BaseActivity<V: ViewDataBinding, VM : BaseViewModel> : AppCompatActivity() {

    protected lateinit var binding: V
    protected lateinit var viewModel: VM
    val compositionRoot get() =  (application as SayBingsApplication).appCompositionRoot

    abstract fun getDataBinding(): V
    abstract fun injectViewModel(): VM

    abstract fun initListeners()
    abstract fun initObservers()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = getDataBinding()
        setContentView(binding.root)

        viewModel = injectViewModel()

        super.onCreate(savedInstanceState)
        initListeners()
        initObservers()
    }
}