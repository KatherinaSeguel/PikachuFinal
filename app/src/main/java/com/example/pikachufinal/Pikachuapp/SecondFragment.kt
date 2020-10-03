package com.example.pikachufinal.Pikachuapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.pikachufinal.Pikachuapp.Caracterist.CaractRepository
import com.example.pikachufinal.Pikachuapp.Caracterist.CaractViewModel
import com.example.pikachufinal.Pikachuapp.ViewModel.PokemonViewModel
import com.example.pikachufinal.R
import kotlinx.android.synthetic.main.fragment_second.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    lateinit var mViewModel: CaractViewModel
    var mId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            mId = arguments?.getString("id") ?: ""
            Log.d("SEGUNDO",mId.toString())
        }
        mViewModel = ViewModelProvider(this).get(CaractViewModel::class.java)
    }



    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mId.let {
          mViewModel.obtainFromInternet(it)
        }



     //   view.findViewById<Button>(R.id.button_second).setOnClickListener {
       //     findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }
