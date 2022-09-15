package com.app.saybings.view.homeActivity

import android.content.Intent
import com.app.saybings.base.view.BaseActivity
import com.app.saybings.databinding.ActivityHomeBinding
import com.app.saybings.view.addMovementActivity.AddMovementActivity

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>() {

    override fun onStart() {
        super.onStart()
        viewModel.updateBalance()
    }

    override fun initListeners() {
        binding.btnAdd.setOnClickListener {
            startActivity(Intent(this, AddMovementActivity::class.java))
        }
    }

    override fun initObservers() {
        viewModel.balance.observe(this){
            binding.txtSavings.text = it.toString()
        }
    }

    override fun getDataBinding(): ActivityHomeBinding =
        ActivityHomeBinding.inflate(layoutInflater)

    override fun injectViewModel(): HomeViewModel = compositionRoot.homeViewModel
}