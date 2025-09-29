package com.example.myshop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myshop.databinding.FragmentEditBinding
class EditFragment : Fragment() {

    private var _binding: FragmentEditBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){

            val args: EditFragmentArgs by navArgs()
            val currentName = args.Name
            val currentNim = args.NIM
            val currentAddress = args.Province

            edtName.setText(currentName)
            edtNim.setText(currentNim)
            edtAddress.setText(currentAddress)

            val action = EditFragmentDirections.actionEditFragmentToEditAddressFragment()

            edtAddress.setOnClickListener {
                findNavController().navigate(action)
            }

            btnBatal.setOnClickListener {
                findNavController().navigateUp()
            }



            btnSimpan.setOnClickListener {

                val name = edtName.text.toString()
                val nim = edtNim.text.toString()
                val address = edtAddress.text.toString()

                findNavController().apply { //apply nilai ke navStateHandle
                    previousBackStackEntry?.savedStateHandle?.set("address", address)
                    previousBackStackEntry?.savedStateHandle?.set("nim", nim)
                    previousBackStackEntry?.savedStateHandle?.set("name", name)
                }
                    .navigateUp()
            }

            findNavController()
                .currentBackStackEntry
                ?.savedStateHandle
                ?.let { handle ->
                    handle.getLiveData<String>("address") //ambil data dengan key address
                        .observe(viewLifecycleOwner){ res ->
                            // edit addres di update dengan data dari address fragment
                            edtAddress.setText(res)
                        }
                }

        }


    }
}