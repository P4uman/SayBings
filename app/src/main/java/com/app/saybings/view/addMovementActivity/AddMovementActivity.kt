package com.app.saybings.view.addMovementActivity

import com.app.saybings.base.view.BaseActivity
import com.app.saybings.databinding.ActivityAddMovementBinding

class AddMovementActivity : BaseActivity<ActivityAddMovementBinding, AddMovementViewModel>() {

    override fun initListeners() {
        binding.btnAdd.setOnClickListener {
            viewModel.addMovement(
                binding.edtxtMovement.text.toString().toFloat(),
                binding.chbxSaving.isChecked
            )
        }
    }

    override fun initObservers() {
        // Do nothing
    }

    override fun getDataBinding(): ActivityAddMovementBinding  = ActivityAddMovementBinding.inflate(
        layoutInflater)

    override fun injectViewModel(): AddMovementViewModel = compositionRoot.addMovementViewModel


}