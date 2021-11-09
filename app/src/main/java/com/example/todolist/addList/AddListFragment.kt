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
import com.example.todolist.DataPickerFragment
import com.example.todolist.Database.ToDoData
import com.example.todolist.R
import com.example.todolist.theList.KEY_ID
import com.example.todolist.theList.theListFragment
import java.util.*

const val KEY_FOR_DATE="date key"
class AddListFragment : Fragment(),DataPickerFragment.DataPickerCallBack {

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
        descreptionEdit=view.findViewById(R.id.descrItem)
        isDone=view.findViewById(R.id.isDone)
        dateBTn=view.findViewById(R.id.dateBTN)
        adeddBTn=view.findViewById(R.id.addToList)



//        dateBTn.apply {
//            text=note.date.toString()//whaaaay
//        }


        return view
    }


    override fun onStart() {
        super.onStart()

        dateBTn.setOnClickListener {
            val args =Bundle()
            args.putSerializable(KEY_FOR_DATE,note.date)
            val datePicker = DataPickerFragment()
            datePicker.arguments = args
            datePicker.setTargetFragment(this, 0)
            datePicker.show(this.parentFragmentManager, "date picker")
        }

        adeddBTn.setOnClickListener {

            addListFrVM.addtodo(note)
            val fragment= theListFragment()
            activity?.let {
                it.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragmentContainerView, fragment)
                    .addToBackStack(null)
                    .commit()
            }
        }



    val textWatcher=object :TextWatcher{
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {


        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            note.textedit= p0.toString()
        }

        override fun afterTextChanged(p0: Editable?) {

        }

    }
        textEdit.addTextChangedListener(textWatcher)
       descreptionEdit.addTextChangedListener(textWatcher)
        isDone.setOnCheckedChangeListener { _, isChecked ->note.isDone=isChecked  }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        note= ToDoData()

       // val noteId= arguments?.getSerializable(KEY_ID) as UUID?
        //هنا سوينا هاندل للايروراذا كان نول مايكرش
//        if (noteId != null) {
//            addListFrVM.loadToDoList(noteId)
//        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addListFrVM.noteLiveData.observe(
            viewLifecycleOwner,{
                it?.let {
                    note=it
                    textEdit.setText(it.textedit)
                    descreptionEdit.setText(it.textedit)
                    dateBTn.text=it.date.toString()
                    isDone.isChecked= it.isDone

                }
            }
        )


    }

    override fun onDateSelected(date: Date) {
        note.date=date
        dateBTn.text=date.toString()
    }

    override fun onStop() {
        super.onStop()
        addListFrVM.saveUpdate(note)
    }


}