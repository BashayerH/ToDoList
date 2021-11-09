package com.example.todolist.theList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.Database.ToDoData
import com.example.todolist.EditFromListFragment
import com.example.todolist.R
import com.example.todolist.addList.AddListFragment
import com.example.todolist.addList.AddListViewModel
import java.util.*

const val KEY_ID="note_id"

class theListFragment : Fragment() {

   private lateinit var theListRC:RecyclerView

private val theListViewModel by lazy { ViewModelProvider(this).get(TheListViewModel::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view= inflater.inflate(R.layout.fragment_the_list,container,false)

        theListRC=view.findViewById(R.id.listRcView)
        val linearLM=LinearLayoutManager(context)
        theListRC.layoutManager=linearLM



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        theListViewModel.noteLiveData.observe(
            viewLifecycleOwner,Observer{
                updatAdapter(it)
            }
        )
    }

    private inner class ViewHolderList(view: View):RecyclerView.ViewHolder(view),View.OnClickListener{
        private var titleList:TextView=itemView.findViewById(R.id.title_item)
        private var isDoneImg:ImageView=itemView.findViewById(R.id.is_Done_item)
        private var descriptioItem:TextView=itemView.findViewById(R.id.descrep_item)
       private lateinit var note:ToDoData
//هنا عندي مشكلة بطريقة عرض البيانات
       fun bind(note:ToDoData){
           this.note=note//whaay
           titleList.text=note.textedit
          // descriptioItem.text=note.description
           descriptioItem.text=note.date.toString()
           isDoneImg.visibility=if (note.isDone){
               View.VISIBLE
           }else{
               View.GONE
           }
       }
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            if (p0==itemView){
                val arg=Bundle()
                arg.putSerializable(KEY_ID,note.id)
               val fragment= EditFromListFragment()
                fragment.arguments=arg
                activity?.let {
                    it.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragmentContainerView,fragment)
                        .addToBackStack(null)
                        .commit()
                }
            }
        }

    }


    private inner class AdapterList(var note:List<ToDoData>):RecyclerView.Adapter<ViewHolderList>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolderList {
            val view =layoutInflater.inflate(R.layout.the_list_item,parent,false)

            return ViewHolderList(view)
        }

        override fun onBindViewHolder(holder: ViewHolderList, position: Int) {
            val toDo=note[position]
            holder.bind(toDo)
        }

        override fun getItemCount(): Int= note.size

    }
    private fun updatAdapter(note: List<ToDoData>){
        val adapterList=AdapterList(note)

        theListRC.adapter=adapterList
    }


}