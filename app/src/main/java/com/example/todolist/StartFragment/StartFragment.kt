package com.example.todolist.StartFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.Database.ToDoData
import com.example.todolist.R
import com.example.todolist.addList.AddListFragment
import com.example.todolist.theList.KEY_ID
import com.example.todolist.theList.theListFragment


class StartFragment : Fragment() {


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

        addLBTN.setOnClickListener {

            val fragment= AddListFragment()

            activity?.let {
                it.supportFragmentManager
                .beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_right,R.anim.slid_out_left,R.anim.slide_in_right,R.anim.slied_in_left)
                .replace(R.id.fragmentContainerView,fragment)
                .addToBackStack(null)
                .commit()  }

        }

        listBTN.setOnClickListener {

            val fragmentList = theListFragment()

            activity?.let {
                it.supportFragmentManager
                    .beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_right,R.anim.slid_out_left,R.anim.slide_in_right,R.anim.slied_in_left)
                    .replace(R.id.fragmentContainerView,fragmentList)
                    .addToBackStack(null)
                    .commit()  }
        }
    }


    }
