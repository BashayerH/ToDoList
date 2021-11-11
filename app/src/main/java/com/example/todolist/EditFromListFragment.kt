package com.example.todolist

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.todolist.Database.ToDoData
import com.example.todolist.addList.KEY_FOR_DATE
import com.example.todolist.theList.KEY_ID
import com.example.todolist.theList.dateFormat
import com.example.todolist.theList.theListFragment
import java.util.*

class EditFromListFragment : Fragment(),DataPickerFragment.DataPickerCallBack {

    private val editFragmentVm by lazy { ViewModelProvider(this).get(EditFromListViewModel::class.java) }

    private lateinit var note: ToDoData

    private lateinit var editTitle: EditText
    private lateinit var editDescr: EditText
    private lateinit var editBTn: TextView
    private lateinit var editImg:ImageView
    private lateinit var deleteBtn: TextView
    private lateinit var deletImg:ImageView
    private lateinit var editDate: TextView
    private lateinit var changDateImg:ImageView
    private lateinit var share:TextView
    private lateinit var shareImg:ImageView
    private lateinit var creatDate:TextView
    private lateinit var douDateEnd:TextView
    private lateinit var isDone: CheckBox

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.edit_from_list_fragment, container, false)

        initBTN(view)

        douDateEnd.apply {
            text= note.duoDate.toString()//whaaaay
      }
        creatDate.apply {
            text=android.text.format.DateFormat.format(dateFormat,note.date)
        }


        return view
    }

     private fun shareBTN():String{

        val solvedString =if (note.isDone){
            "I complete the task"
        }else{
            "not complete ,yet."
        }

        val dateString = DateFormat.format(dateFormat,note.duoDate)

//        val suspectString = if (crime.suspected.isBlank()){
//            "there is no any suspect"
//        }else{
//            "the suspect is ${crime.suspected}"
//       }
        return "$solvedString and the date of the crime is $dateString  "
    }



    private fun initBTN(view: View) {
        editTitle = view.findViewById(R.id.edit_title)
        editDescr = view.findViewById(R.id.edit_desc_title)
        editBTn = view.findViewById(R.id.editBTn)
        editImg = view.findViewById(R.id.edit_img)
        deleteBtn = view.findViewById(R.id.deleteBtn)
        deletImg = view.findViewById(R.id.delet_img)
        editDate = view.findViewById(R.id.changeDate)
        changDateImg = view.findViewById(R.id.change_date_img)
        share = view.findViewById(R.id.shareText)
        shareImg = view.findViewById(R.id.share_img)
        creatDate = view.findViewById(R.id.the_creat_date)
        douDateEnd = view.findViewById(R.id.the_due_date)
        isDone = view.findViewById(R.id.isDone)
    }

    override fun onStart() {
        super.onStart()


      shareImg.setOnClickListener {
            Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT,shareBTN() )
                putExtra(
                    Intent.EXTRA_SUBJECT,
                    " Report")
            }.also {
                val chooserIntent =
                    Intent.createChooser(it," send_report")
                startActivity(chooserIntent)
            }

        }

        (deleteBtn );(deletImg).setOnClickListener {
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

        (editDate);(changDateImg).setOnClickListener {
            val args = Bundle()
            args.putSerializable(KEY_FOR_DATE, note.duoDate)
            val datePicker = DataPickerFragment()
            datePicker.arguments = args
            datePicker.setTargetFragment(this, 0)
            datePicker.show(this.parentFragmentManager, "date picker")
        }

        (editBTn);(editImg).setOnClickListener{
            editFragmentVm.saveUpdate(note)
            val fragment= theListFragment()
            activity?.let {
                it.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragmentContainerView, fragment)
                    .addToBackStack(null)
                    .commit()
            }

        }

        val textWatcher=object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                note.textedit= p0.toString()

            }
            override fun afterTextChanged(p0: Editable?) {
            } }

        val descrWatcher=object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                note.description=p0.toString()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        }

        editTitle.addTextChangedListener(textWatcher)
        editDescr.addTextChangedListener(descrWatcher)
       isDone.setOnCheckedChangeListener { _, isChecked ->note.isDone=isChecked  }


   }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editFragmentVm.noteLiveData.observe(
            viewLifecycleOwner,{
                it?.let {
                    note=it
                    editTitle.setText(it.textedit)
                    editDescr.setText(it.description)
                   // editDate.text= it.duoDate.toString()
                    douDateEnd.setText(it.duoDate.toString())
                    creatDate.setText(it.date.toString())
                   isDone.isChecked= it.isDone

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
       // note.duoDate=date
       // editDate.text=date.toString()

    }

}

