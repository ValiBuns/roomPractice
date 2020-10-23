package com.example.roomexample.data.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomexample.R
import com.example.roomexample.data.User
import com.example.roomexample.data.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*


class addFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_add, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        view.add_btn.setOnClickListener {
            insertDataToDatabase()
        }
        return view
    }

    private fun insertDataToDatabase() {

        val firstName = editTextFirstName_et.text.toString()
        val lastName = editTextLastName_et.text.toString()
        val age = editTextNumber.text

        if (inputCheck(firstName,lastName,age)){
            //create a user
            val user = User(0,firstName,lastName, Integer.parseInt(age.toString()))
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_LONG).show()
            //Navigate back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(),"Please Fill out fields",Toast.LENGTH_LONG).show()
        }

    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable ): Boolean{

        return !((TextUtils.isEmpty(firstName)&&(TextUtils.isEmpty(lastName) && age.isEmpty())))
        
        
    }
}
