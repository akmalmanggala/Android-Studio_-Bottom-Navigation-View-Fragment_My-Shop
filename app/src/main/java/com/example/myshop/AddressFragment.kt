package com.example.myshop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.example.myshop.databinding.FragmentAddressBinding

class AddressFragment : Fragment() {

    private var _binding: FragmentAddressBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //assign binding
        _binding = FragmentAddressBinding.inflate(inflater, container, false)

        // set content view dengan root binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // kalau sudah di create viewnya, kita bisa akses
        with(binding){
            // ambil data provinces dari array
            val provinces = resources.getStringArray(R.array.provinces)

            // buat adapter yang mengatur tampilan dari spinner dan menghubungkan data privinces
            val adapterProvinces = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                provinces)

            // buat spinner dengan isi data dari array provinces
            adapterProvinces.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            // set spinnerProvinces adapter dengan adapterProvinces
            spinnerProvinces.adapter = adapterProvinces

            //handle button setelah di klik
            btnDone.setOnClickListener {
                // cari nav controller
                findNavController().apply { //apply nilai ke navStateHandle
                    previousBackStackEntry?.savedStateHandle?.set("address", spinnerProvinces.selectedItem.toString())
                }
                    .navigateUp()
            }
        }
    }
}