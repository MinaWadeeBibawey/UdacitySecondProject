package android.udacity.com.udacitysecondproject.ui.main

import android.udacity.com.udacitysecondproject.databinding.ItemAdapterBinding
import android.udacity.com.udacitysecondproject.models.Asteroid
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class AsteroidAdapter(
    private val onClickListener: OnClickListener,
) :
    ListAdapter<Asteroid, AsteroidAdapter.AsteroidListViewHolder>(DiffCallback) {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AsteroidListViewHolder {
        return AsteroidListViewHolder(ItemAdapterBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: AsteroidListViewHolder, position: Int) {
        val asteroidProperty = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(asteroidProperty)
        }
        holder.bind(asteroidProperty)
    }

    class AsteroidListViewHolder(private var binding: ItemAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(asteroid: Asteroid) {
            binding.asteroidModel = asteroid
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Asteroid>() {
        override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class OnClickListener(val clickListener: (marsProperty: Asteroid) -> Unit) {
        fun onClick(marsProperty: Asteroid) = clickListener(marsProperty)
    }
}