package com.e.apiplaceholder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


class ListFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var Adapter:CommentsAdapter
    private  var listadoDeComentarios= listOf<Comments>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val myView:View= inflater.inflate(R.layout.fragment_list, container, false)
        recyclerView=myView.findViewById(R.id.recyclerview)
        return myView

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager=LinearLayoutManager(requireActivity())
        Adapter= CommentsAdapter()
        recyclerView.adapter=Adapter

        getComentarios()
    }

    private fun getComentarios() {
        CoroutineScope(Dispatchers.IO).launch {
            val call= getrofit().create(ApiService::class.java).getComentarios()
            val response= call.body()
            activity?.runOnUiThread{
                if (call.isSuccessful)
                    listadoDeComentarios= response!!
            }?: Adapter.submitList(listadoDeComentarios)
        }

        }
    }

    private fun getrofit():Retrofit{
    return Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    }





