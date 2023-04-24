package com.example.licenta2.ui.autentificare

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.licenta2.MainActivity
import com.example.licenta2.R
import com.example.licenta2.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel
    private var _binding: FragmentLoginBinding? = null
    private  val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val root:View = binding.root

        binding.buttonLogIn.setOnClickListener {
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }

        binding.textViewCreeazaCont.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_inregistrareFragment)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding =null
    }

}