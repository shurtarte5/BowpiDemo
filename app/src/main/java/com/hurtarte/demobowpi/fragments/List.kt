package com.hurtarte.demobowpi.fragments

import android.app.AlertDialog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout

import androidx.core.view.setPadding
import androidx.recyclerview.widget.LinearLayoutManager

import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database

import com.google.firebase.ktx.Firebase


import com.hurtarte.demobowpi.R
import com.hurtarte.demobowpi.adapter.TaskAdapter

import com.hurtarte.demobowpi.fragments.data.Task

import kotlinx.android.synthetic.main.fragment_list.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class List() : Fragment() {

    private lateinit var database: DatabaseReference
    private lateinit var adapter: TaskAdapter


    var lTareas: ArrayList<Task> = arrayListOf()


    lateinit var mRecyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        var rootview = inflater.inflate(R.layout.fragment_list, container, false)

        database = Firebase.database.reference

        mRecyclerView = rootview.findViewById(R.id.recyclerview_tasks)



        rootview.fab.setOnClickListener {
            createTask()

        }


        getData()







        return rootview
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    private fun getData() {


        var getData = object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                //var sb = StringBuilder()


                val layoutManager = LinearLayoutManager(activity?.applicationContext)
                layoutManager.orientation = LinearLayoutManager.VERTICAL
                mRecyclerView.layoutManager = layoutManager

                for (i in snapshot.child("task").children) {


                    var mKey: String = i.key as String
                    var mName: String = i.child("name").getValue() as String
                    var mDescription: String = i.child("description").getValue() as String
                    var mdate: String = i.child("date").getValue() as String
                    var mDone = i.child("done").getValue() as Boolean
                    var mTask = Task(mKey, mName, mDescription, mdate, mDone)
                    lTareas.add(mTask)


                    // populateList(mTask)


                    adapter = activity?.applicationContext?.let { TaskAdapter(lTareas, it) }!!
                    mRecyclerView.adapter = adapter

                    adapter.notifyDataSetChanged()


                }


            }


            override fun onCancelled(error: DatabaseError) {

            }

        }



        database.addValueEventListener(getData)


    }


    private fun createTask() {
        val dialogTitle = getString(R.string.task_to_add)
        val positiveButtonTitle = getString(R.string.create)
//---------------------------------------------------------
        val builder = AlertDialog.Builder(activity)


        var layout: LinearLayout = LinearLayout(activity)
        layout.orientation = LinearLayout.VERTICAL
        val name: EditText = EditText(activity)
        name.hint = getString(R.string.task_name)

        val description1: EditText = EditText(activity)
        description1.hint = getString(R.string.task_description)

        layout.addView(name)
        layout.setPadding(8)
        layout.addView(description1)

        builder.setTitle(dialogTitle)
        builder.setView(layout)
        //-------------------------------

        builder.setPositiveButton(positiveButtonTitle) { dialog, _ ->
            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            val currentDate = sdf.format(Date())
            val dateTask = currentDate.toString()
            val newTask = database.child("task").push()
            // val newTask = database
            val mId = newTask.key
            val mName = name.text.toString()
            val mDescription = description1.text.toString()


            val taskData = Task(mId.toString(), mName, mDescription, dateTask)

            newTask.setValue(taskData)






            dialog.dismiss()
        }
        builder.show()

    }





}