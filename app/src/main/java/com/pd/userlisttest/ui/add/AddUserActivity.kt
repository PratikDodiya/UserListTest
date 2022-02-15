package com.pd.userlisttest.ui.add

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.pd.userlisttest.R
import com.pd.userlisttest.data.entities.User
import com.pd.userlisttest.databinding.ActivityAddUserBinding
import com.pd.userlisttest.ui.list.UserListViewModel
import com.pd.userlisttest.utils.click
import com.pd.userlisttest.utils.hideKeyboard
import com.pd.userlisttest.utils.toast

class AddUserActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddUserBinding
    private val viewModel: UserListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_user)

        initializeView()
        initListeners()
    }

    private fun initializeView() {
        setToolBar()
        setupObservers()
    }

    private fun initListeners() {
        binding.btnAddUser.click {

            if (binding.etName.text.toString().isBlank()) {
                toast(getString(R.string.msg_enter_name))
            } else if (binding.etNumber.text.toString().isBlank()) {
                toast(getString(R.string.msg_enter_number))
            } else {
                hideKeyboard()
                val user = User(0, binding.etName.text.toString(), binding.etNumber.text.toString())

                //Check users already exists or not in DB
                viewModel.checkExistsUser(user)
            }
        }
    }

    private fun setToolBar() {
        // Update title text in Toolbar
        supportActionBar?.title = getString(R.string.add_user)

        // Show Back Arrow to Toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish() // close this activity and return to previous activity
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setupObservers() {
        viewModel.isUserExists.observe(this) {
            if (it.not()) {
                viewModel.addUser(User(0, binding.etName.text.toString(), binding.etNumber.text.toString()))
                toast(getString(R.string.msg_user_added_success))
                finish()
            } else {
                toast(getString(R.string.msg_user_available))
            }
        }
    }
}