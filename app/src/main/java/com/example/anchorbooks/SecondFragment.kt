package com.example.anchorbooks

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.anchorbooks.databinding.FragmentSecondBinding


class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding
    private val viewModel: ViewModelBooks by activityViewModels()
    var idImage: String = ""

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding= FragmentSecondBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var  adapter= AdapterDetail()
        binding.rvDetail.adapter= adapter
        binding.rvDetail.layoutManager = GridLayoutManager(context,1)


     /* viewModel.getBooksDetail().observer(viewLifecycleOwner, Observer<> {
            it?.let {
                Log.d("LISTADO IMAGE",it.toString())
                adapter.update(it)

            }
        })
        adapter.selectedItemDetail().observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it.favourite) {
                    it.favourite = false
                    viewModel.updateFavouriteBooks(it)
                    Toast.makeText(context, "ya no es favorito", Toast.LENGTH_LONG).show()
                }else {


                    it.favourite = true
                    viewModel.updateFavouriteBooks(it)
                    Toast.makeText(context, "añadido a fav", Toast.LENGTH_LONG).show()
                }


            }
        })*/

    }
}