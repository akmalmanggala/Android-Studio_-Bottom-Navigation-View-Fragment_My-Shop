package com.example.myshop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.myshop.databinding.FragmentHomeBinding
class HomeFragment : Fragment() {
    //buat binding ke UI
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //set content view
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){

            // ambil product name dari UI
            var productName = txtProductName.text.toString()

            //init action dari fragment direction
            val action = HomeFragmentDirections.actionHomeFragmentToCheckoutFragment(productName)

            //on Click, navigasi ke checkout fragment
            btnBuy.setOnClickListener {
                //cari nav controller kemudian navigasi action kalau sudah ketemu
                findNavController().navigate(action)
            }
        }
    }
}