package com.example.studentdata

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.studentdata.model.MahasiswaModel
import com.example.studentdata.viewmodel.MahasiswaViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout


class FormDialogFragment : DialogFragment() {
    private var toolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppTheme_FullScreenDialog)
    }

    //SET WIDTH&HEIGHT SUPAYA FULL SCREEN
    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window!!.setLayout(width, height)

        }
    }

    //CREATE DIALOG view
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view: View = inflater.inflate(R.layout.form_dialog, container, false)
        toolbar = view.findViewById(R.id.toolbar)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(requireView(), savedInstanceState)
        val viewmodel = ViewModelProvider(requireActivity()).get(MahasiswaViewModel::class.java)
        toolbar!!.inflateMenu(R.menu.form_dialog)
        //Handle close button
        toolbar!!.setNavigationOnClickListener { v: View? ->
            dismiss()
        }
        //handle save button
        toolbar!!.setOnMenuItemClickListener { item: MenuItem? ->
            //read data from input
            val nama = view.findViewById<TextInputLayout>(R.id.nama).editText?.text.toString()
            val nim = view.findViewById<TextInputLayout>(R.id.nim).editText?.text.toString()
            val kelas = view.findViewById<TextInputLayout>(R.id.kelas).editText?.text.toString()
            //check if input is empty
            if (nama.isEmpty() || nim.isEmpty() || kelas.isEmpty()) {
                Toast.makeText(requireActivity(), "Please fill all required form!", Toast.LENGTH_SHORT).show();
                false
            } else {
                //send data to viewmodel
                viewmodel.addMahasiswa(nama, nim, kelas)//save data to VM
                Snackbar.make(requireActivity().findViewById(android.R.id.content), "Data saved sucesfully !", Snackbar.LENGTH_SHORT).show();
                dismiss()
                true
            }
        }
    }

    companion object {
        const val TAG = "FormDialog"
    }
}
