package com.example.pikachufinal.Pikachuapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.pikachufinal.Pikachuapp.ViewModel.PokemonViewModel
import com.example.pikachufinal.Pikachuapp.entities.TodosPoke
import com.example.pikachufinal.R
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.android.synthetic.main.itempoke_list_view.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    lateinit var mViewModel2:   PokemonViewModel
    lateinit var pokemon: TodosPoke
    var mId2: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel2 = ViewModelProvider(this).get(PokemonViewModel::class.java)
        arguments.let {
            mId2 = arguments?.getString("id") ?: ""
          //  Log.d("SEGUNDO", mId2.toString())
        }
        //mViewModel2 = ViewModelProvider(this).get(PokemonViewModel::class.java)
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

        mId2.let {

            mViewModel2.obtainPokemonByID(mId2).observe(viewLifecycleOwner, Observer {
                pokemon = it
                context?.let {  it1-> Glide.with(it1).load(it.image).into(imageView3) }
                tv_id.text = "Pokemon numero " + it.pokemon
                text_name.text = it.name
               tv_types.text = "Tipo   :" + it.types
                tvtypes2.text = "Habilidades   :" + it.abilities
            })
                Glide.with(view.context)
                text_name.text = it.toString()

            //    Glide.with(view.context).load(it).into(textView3)
            //        textView3.text = it.ca



            }

         // ratingfavorito.setOnRatingBarChangeListener { x,stars, iss ->
           //   pokemon.apretar= stars.toDouble()
            //  mViewModel2.updatePokemon(pokemon)
          //}

        //Glide.with(this).load("https://estaticos.muyinteresante.es/media/cache/1140x_thumb/uploads/images/gallery/5b0fcfa25bafe83df2c203b3/pokemon0_0.jpg").into(imageView3)

            view.findViewById<Button>(R.id.button_second).setOnClickListener {
                findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)

            }
        }
    }





  //      view.findViewById<Button>(R.id.button_second).setOnClickListener {
//    findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)


