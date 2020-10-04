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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pikachufinal.Pikachuapp.ViewModel.PokeAdapter
import com.example.pikachufinal.Pikachuapp.ViewModel.PokemonViewModel
import com.example.pikachufinal.Pikachuapp.entities.TodosPoke
import com.example.pikachufinal.R
import kotlinx.android.synthetic.main.fragment_first.*


class FirstFragment : Fragment(),PokeAdapter.CallbackInterface {
    lateinit var mViewModel: PokemonViewModel
    lateinit var adapter: PokeAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel= ViewModelProvider(this).get(PokemonViewModel::class.java)
        adapter = PokeAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = recyclerView
        recyclerView.layoutManager = GridLayoutManager(context,2)
        recyclerView.adapter = adapter

        mViewModel.exposeLiveDataFromDatabase().observe(viewLifecycleOwner,Observer {
            Log.d("VIEW",it.toString())
            adapter.updateAdapter(it)
        })
        Glide.with(this).load("https://tecnogeek.net/wp-content/uploads/2016/07/Pokemon-GO.webp").into(imageView2)

     //   findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
    }

override fun passTheData(todosPoke: TodosPoke){
    val bundle = Bundle()
    bundle.putString("id",todosPoke.pokemon)
    Log.d("Nicolsajajaja",todosPoke.pokemon)
    findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment,bundle)
}

}