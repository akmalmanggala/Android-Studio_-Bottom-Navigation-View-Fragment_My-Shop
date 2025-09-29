package com.example.myshop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myshop.databinding.FragmentCheckoutBinding
class CheckoutFragment : Fragment() {
    private var _binding: FragmentCheckoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //        assign _binding
        _binding = FragmentCheckoutBinding.inflate(layoutInflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            //baca argumen yang dikirim dari home fragment
            val args: CheckoutFragmentArgs by navArgs()
            val productName = args.productName

            //set text menjadi product name
            txtProductName.text = productName

            address.setOnClickListener {
                // buat action untuk navigasi ke address
                var action = CheckoutFragmentDirections.actionCheckoutFragmentToAddressFragment()
                // Cari nav controller kemudian navigasi action kalau sudah ketemu
                findNavController().navigate(action)
            }
            //ambil data yang dikirim oleh address fragment
            findNavController()
                .currentBackStackEntry
                ?.savedStateHandle
                ?.let { handle ->
                    handle.getLiveData<String>("address") //ambil data dengan key address
                        .observe(viewLifecycleOwner){ res ->
                            // edit addres di update dengan data dari address fragment
                            address.setText(res)
                        }
                }
            btnDone.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }
}