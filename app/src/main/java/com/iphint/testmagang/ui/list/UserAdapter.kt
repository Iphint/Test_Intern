import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iphint.testmagang.response.DataItem
import com.iphint.testmagang.databinding.ItemUserBinding

class UserAdapter(private val onItemClicked: (DataItem) -> Unit) :
    PagingDataAdapter<DataItem, UserAdapter.UserViewHolder>(DataItemComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemUserBinding.inflate(layoutInflater, parent, false)
        return UserViewHolder(binding, onItemClicked)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bind(it) }
    }

    class UserViewHolder(private val binding: ItemUserBinding, private val onItemClicked: (DataItem) -> Unit) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(dataItem: DataItem) {
            binding.tvUserName.text = "${dataItem.first_name} ${dataItem.last_name}"
            binding.tvUserEmail.text = dataItem.email
            Glide.with(binding.ivUserAvatar.context).load(dataItem.avatar).into(binding.ivUserAvatar)

            itemView.setOnClickListener { onItemClicked(dataItem) }
        }
    }

    companion object DataItemComparator : DiffUtil.ItemCallback<DataItem>() {
        override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem == newItem
        }
    }
}
