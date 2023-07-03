package com.example.jobdesk.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.jobdesk.R
import com.example.jobdesk.model.Jobdesk

class jobdeskListAdapter(
    private  val onItemClickListener: (Jobdesk) -> Unit
): ListAdapter<Jobdesk, jobdeskListAdapter.JobdeskViewHolder>(WORDS_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobdeskViewHolder {
        return JobdeskViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: JobdeskViewHolder, position: Int) {
        val jobdesk = getItem(position)
        holder.bind(jobdesk)
        holder.itemView.setOnClickListener {
            onItemClickListener(jobdesk)
        }
    }
    class JobdeskViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private val addressTextView: TextView = itemView.findViewById(R.id.addressEditText)
        private val psosicionTextureView: TextView = itemView.findViewById(R.id.psosicionTextView)
        fun bind(jobdesk: Jobdesk?) {
            nameTextView.text = jobdesk?.name
            addressTextView.text = jobdesk?.address
            psosicionTextureView.text = jobdesk?.posicion
        }

        companion object {
            fun create(parent: ViewGroup): jobdeskListAdapter.JobdeskViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_jobdesk, parent, false)
                return  JobdeskViewHolder(view)

            }
        }

    }

  companion object {
      private val WORDS_COMPARATOR = object : DiffUtil.ItemCallback<Jobdesk>(){
          override fun areItemsTheSame(oldItem: Jobdesk, newItem: Jobdesk): Boolean {
              return oldItem === newItem
          }

          override fun areContentsTheSame(oldItem: Jobdesk, newItem: Jobdesk): Boolean {
              return oldItem.id == newItem.id

          }
      }
  }
}