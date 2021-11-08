package com.example.todolist.addList

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import com.example.todolist.Database.ToDoData
import com.example.todolist.R
import com.example.todolist.theList.KEY_ID
import java.util.*

class AddListFragment : Fragment() {

private val addListFrVM by lazy { ViewModelProvider(this).get(AddListViewModel::class.java) }

    private lateinit var note:ToDoData

    private lateinit var textEdit:EditText
    private lateinit var descreptionEdit:EditText
    private lateinit var isDone:CheckBox
    private lateinit var dateBTn:Button
    private lateinit var adeddBTn:Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =inflater.inflate(R.layout.add_list_fragment, container, false)

        textEdit=view.findViewById(R.id.editeNote)
        descreptionEdit=view.findViewById(R.id.editDecs)
        isDone=view.findViewById(R.id.isDone)
        dateBTn=view.findViewById(R.id.dateBTN)
        adeddBTn=view.findViewById(R.id.addToList)

        dateBTn.apply {
            text=note.date.toString()//whaaaay
        }


        return view
    }

    override fun onStart() {
        super.onStart()

    val textWatcher=object :TextWatcher{
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            note.textedit= p0.toString()

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun afterTextChanged(p0: Editable?) {

        }

    }
        textEdit.addTextChangedListener(textWatcher)
        descreptionEdit.addTextChangedListener(textWatcher)
        isDone.setOnCheckedChangeListener { _, b ->note.isDone=b  }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        note= ToDoData()

        val noteId=arguments?.getSerializable(KEY_ID)as UUID

        addListFrVM
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }


}