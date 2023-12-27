package com.iphint.testmagang.ui.list

import UserAdapter
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.iphint.testmagang.databinding.ActivityListUserBinding
import com.iphint.testmagang.paging.UserLoadStateAdapter
import com.iphint.testmagang.ui.MainActivity
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ListUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListUserBinding
    private val viewModel: ListUserViewModel by viewModels()

    private val adapter = UserAdapter { dataItem ->
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("EXTRA_NAME", "${dataItem.first_name} ${dataItem.last_name}")
        }
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupSwipeToRefresh()
        observeUsers()
    }

    private fun setupRecyclerView() {
        binding.recylerView.layoutManager = LinearLayoutManager(this)
        binding.recylerView.adapter = adapter.withLoadStateHeaderAndFooter(
            header = UserLoadStateAdapter { adapter.retry() },
            footer = UserLoadStateAdapter { adapter.retry() }
        )
    }

    private fun setupSwipeToRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            adapter.refresh()
        }
        adapter.addLoadStateListener { loadState ->
            binding.swipeRefreshLayout.isRefreshing = loadState.refresh is LoadState.Loading
        }
    }

    private fun observeUsers() {
        lifecycleScope.launch {
            viewModel.users.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }
}