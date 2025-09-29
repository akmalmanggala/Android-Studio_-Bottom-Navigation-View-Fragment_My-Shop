package com.example.myshop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.example.myshop.databinding.FragmentAddressBinding
import com.example.myshop.databinding.FragmentEditAddressBinding

class EditAddressFragment : Fragment() {
    private var _binding: FragmentEditAddressBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditAddressBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){

            val provinces = resources.getStringArray(R.array.provinces)

            val adapterProvinces = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                provinces)

            adapterProvinces.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            edtProvinces.adapter = adapterProvinces

            btnDone.setOnClickListener {
                // cari nav controller
                findNavController().apply { //apply nilai ke navStateHandle
                    previousBackStackEntry?.savedStateHandle?.set("address", edtProvinces.selectedItem.toString())
                }
                    .navigateUp()
            }
        }
    }
}