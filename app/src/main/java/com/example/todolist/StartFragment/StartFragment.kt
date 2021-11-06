package com.example.todolist.StartFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.addList.AddListFragment
import com.example.todolist.theList.theListFragment


class StartFragment : Fragment() {

val startFragmentViewModel by lazy { ViewModelProvider(this).get(StartFragmentViewModel::class.java) }
    private lateinit var addLBTN:Button
    private lateinit var listBTN:Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view=inflater.inflate(R.layout.fragment_start,container,false)

        addLBTN= view.findViewById(R.id.AddListBTN)
        listBTN=view.findViewById(R.id.LIstBTN)

    return view

    }

    override fun onStart() {
        super.onStart()
        val fragment=AddListFragment()
        val fragmentList=theListFragment()
        addLBTN.setOnClickListener {
            activity?.let {
                it.supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainerView,fragment)
                .addToBackStack(null)
                .commit()  }

        }

        listBTN.setOnClickListener {

            activity?.let {
                it.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragmentContainerView,fragmentList)
                    .addToBackStack(null)
                    .commit()  }
        }
    }



    }
