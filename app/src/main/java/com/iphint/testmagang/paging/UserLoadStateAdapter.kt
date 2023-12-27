package com.iphint.testmagang.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.iphint.testmagang.databinding.ItemLoadingBinding

class UserLoadStateAdapter(private val retry: () -> Unit) : LoadStateAdapter<UserLoadStateViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): UserLoadStateViewHolder {
        val binding = ItemLoadingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserLoadStateViewHolder(binding, retry)
    }

    override fun onBindViewHolder(holder: UserLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }
}

class UserLoadStateViewHolder(private val binding: ItemLoadingBinding, retry: () -> Unit) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.retryButton.setOnClickListener { retry() }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Loading) {
            binding.progressBar.visibility = View.VISIBLE
            binding.errorMsg.visibility = View.GONE
            binding.retryButton.visibility = View.GONE
        } else {
            binding.progressBar.visibility = View.GONE
            if (loadState is LoadState.Error) {
                binding.errorMsg.visibility = View.VISIBLE
                binding.retryButton.visibility = View.VISIBLE
            } else {
                binding.errorMsg.visibility = View.GONE
                binding.retryButton.visibility = View.GONE
            }
        }
    }
}
