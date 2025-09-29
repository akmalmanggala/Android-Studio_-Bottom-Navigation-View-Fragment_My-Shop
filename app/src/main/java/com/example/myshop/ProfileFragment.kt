package com.example.myshop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.myshop.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){


            btnEditProfile.setOnClickListener {

                val currentName = txtName.text.toString()
                val currentAddres = txtProvince.text.toString()
                val currentNim = txtNim.text.toString()

                val action = ProfileFragmentDirections.actionProfileFragmentToEditFragment(
                    currentName,
                    currentNim,
                    currentAddres)

                findNavController().navigate(action)
            }

            findNavController()
                .currentBackStackEntry
                ?.savedStateHandle
                ?.let { handle ->
                    handle.getLiveData<String>("name") //ambil data dengan key address
                        .observe(viewLifecycleOwner){ res ->
                            // edit addres di update dengan data dari address fragment
                            txtName.text = res
                        }

                    handle.getLiveData<String>("address") //ambil data dengan key address
                        .observe(viewLifecycleOwner){ res ->
                            // edit addres di update dengan data dari address fragment
                            txtProvince.text = res
                        }

                    handle.getLiveData<String>("nim") //ambil data dengan key address
                        .observe(viewLifecycleOwner){ res ->
                            // edit addres di update dengan data dari address fragment
                            txtNim.text = res
                        }
                }
        }
    }
}