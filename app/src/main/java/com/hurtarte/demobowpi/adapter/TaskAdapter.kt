package com.hurtarte.demobowpi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hurtarte.demobowpi.R
import com.hurtarte.demobowpi.fragments.data.Task

class TaskAdapter(private var taskList:ArrayList<Task>, context:Context): RecyclerView.Adapter<TaskAdapter.MyViewHolder>() {




    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val mName = itemView.findViewById(R.id.txt_name) as TextView
        val mDescription = itemView.findViewById(R.id.txt_description) as TextView
        val mDate = itemView.findViewById(R.id.txt_date) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tasks, parent,false)

        return MyViewHolder(view)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if (taskList != null) {
            holder.mName.text=taskList.get(position).name
            holder.mDescription.text=taskList.get(position).description
            holder.mDate.text=taskList.get(position).date
        }

    }

    override fun getItemCount(): Int {

     return taskList.size
    }

}