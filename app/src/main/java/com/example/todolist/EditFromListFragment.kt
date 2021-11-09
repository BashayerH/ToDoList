package com.example.todolist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.todolist.Database.ToDoData
import com.example.todolist.addList.KEY_FOR_DATE
import com.example.todolist.theList.KEY_ID
import com.example.todolist.theList.theListFragment
import java.util.*

class EditFromListFragment : Fragment(),DataPickerFragment.DataPickerCallBack {

    private val editFragmentVm by lazy { ViewModelProvider(this).get(EditFromListViewModel::class.java) }

    private lateinit var note: ToDoData

    private lateinit var editTitle: EditText
    private lateinit var editDescr: EditText
    private lateinit var editBTn: Button
    private lateinit var deleteBtn: Button
    private lateinit var editDate: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.edit_from_list_fragment, container, false)

        editTitle = view.findViewById(R.id.edit_title)
        editDescr = view.findViewById(R.id.edit_desc_title)
        editBTn = view.findViewById(R.id.editBTn)
        deleteBtn = view.findViewById(R.id.deleteBtn)
        editDate = view.findViewById(R.id.startDate)

        editDate.apply {
            text=note.date.time.toString()//whaaaay
      }


        return view
    }

    override fun onStart() {
        super.onStart()

        deleteBtn.setOnClickListener {

            editFragmentVm.deleteToDo(note)
            val fragment = theListFragment()
            activity?.let {
                it.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragmentContainerView, fragment)
                    .addToBackStack(null)
                    .commit()

            }
        }

        editDate.setOnClickListener {
            val args = Bundle()
            args.putSerializable(KEY_FOR_DATE, note.date)
            val datePicker = DataPickerFragment()
            datePicker.arguments = args
            datePicker.setTargetFragment(this, 0)
            datePicker.show(this.parentFragmentManager, "date picker")
        }

        val textWatcher=object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {


            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                note.textedit= p0.toString()
                note.description=p1.toString()// ايش الفرق ؟؟؟؟
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        }

        editTitle.addTextChangedListener(textWatcher)
        editDescr.addTextChangedListener(textWatcher)
       //isDone.setOnCheckedChangeListener { _, isChecked ->note.isDone=isChecked  }


   }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editFragmentVm.noteLiveData.observe(
            viewLifecycleOwner,{
                it?.let {
                    note=it
                    editTitle.setText(it.textedit)
                    editDescr.setText(it.description)
                    editDate.text=it.date.toString()
                  //  isDone.isChecked= it.isDone

                }
            }
        )


    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        note = ToDoData()

        val noteId = arguments?.getSerializable(KEY_ID) as UUID?

        if (noteId != null) {
            editFragmentVm.loadToDoList(noteId)
        }
    }

    override fun onStop() {
        super.onStop()
        editFragmentVm.saveUpdate(note)
    }

    override fun onDateSelected(date: Date) {//وااااي
        note.date=date
        editDate.text=date.toString()
    }



}
