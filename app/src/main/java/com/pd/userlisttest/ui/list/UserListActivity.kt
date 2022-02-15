package com.pd.userlisttest.ui.list

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.pd.userlisttest.R
import com.pd.userlisttest.databinding.ActivityUserListBinding
import com.pd.userlisttest.ui.add.AddUserActivity
import com.pd.userlisttest.utils.click
import com.pd.userlisttest.utils.setManager

class UserListActivity : AppCompatActivity() {

    lateinit var binding: ActivityUserListBinding
    private val viewModel: UserListViewModel by viewModels()
    private lateinit var adapter: UserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_list)

        initializeView()
        initListeners()
    }

    private fun initializeView() {
        setToolBar()
        setupRecyclerView()
        setupObservers()
    }

    private fun setToolBar() {
        // Update title text in Toolbar
        supportActionBar?.title = getString(R.string.user_list)
    }

    private fun setupRecyclerView() {
        adapter = UserListAdapter()
        binding.rvUser.setManager()
        binding.rvUser.adapter = adapter
    }

    private fun initListeners() {
        binding.fabAdd.click {
            startActivity(Intent(this, AddUserActivity::class.java))
        }
    }

    private fun setupObservers() {
        viewModel.allTodo.observe(this, Observer {

            //Get users list from DB
            if (!it.isNullOrEmpty()) {
                adapter.setItems(ArrayList(it))
                binding.tvNoData.visibility = View.GONE
            } else {
                adapter.setItems(arrayListOf())
                binding.tvNoData.visibility = View.VISIBLE
            }
        })
    }
}