package com.example.joblist.ui.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil 
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.joblist.R
import com.example.joblist.databinding.RowCandidateBinding
import com.example.joblist.entities.Candidate

class CandidateAdapter : ListAdapter<Candidate, RecyclerView.ViewHolder>(DiffCallback()), Filterable {
    private lateinit var binding: RowCandidateBinding
    var listener: Listener? = null
    private var candidates = mutableListOf<Candidate>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = RowCandidateBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).displayData(getItem(position))
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                var list = mutableListOf<Candidate>()
                val filterResults = FilterResults()

                if (constraint == null || constraint.toString().isEmpty()) {
                    list = candidates
                } else {
                    candidates.forEach { candidate ->
                        if (candidate.name
                                .lowercase()
                                .trim()
                                .contains(constraint.toString().lowercase())
                        ) {
                            list.add(candidate)
                        }
                    }
                }

                filterResults.values = list
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, filterResults: FilterResults?) {
                submitList(filterResults?.values as MutableList<Candidate>)
            }
        }
    }

    fun submitData(candidates: MutableList<Candidate>?) {
        this.candidates = candidates!!
        submitList(candidates)
    }

    class ViewHolder(
        private val binding: RowCandidateBinding,
        private val listener: Listener?
    ) : RecyclerView.ViewHolder(binding.root) {
        fun displayData(candidate: Candidate) {
            Glide
                .with(itemView.context)
                .load(candidate.avatar)
                .placeholder(R.drawable.ic_launcher_foreground)
                .centerCrop()
                .into(binding.ivAvatar)
            binding.tvName.text = candidate.name
            binding.tvCategory.text = candidate.category
            binding.tvAddress.text = candidate.address
            itemView.setOnClickListener {
                listener?.onClick(adapterPosition)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Candidate>() {
        override fun areItemsTheSame(oldItem: Candidate, newItem: Candidate): Boolean =
            oldItem === newItem

        override fun areContentsTheSame(oldItem: Candidate, newItem: Candidate): Boolean =
            oldItem.id == newItem.id
    }

    interface Listener {
        fun onClick(position: Int)
    }
}
