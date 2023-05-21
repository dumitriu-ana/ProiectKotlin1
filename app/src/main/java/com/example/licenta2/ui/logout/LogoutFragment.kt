package com.example.licenta2.ui.logout

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.licenta2.FirebaseInregistrare
import com.example.licenta2.R
import com.example.licenta2.databinding.FragmentLoginBinding
import com.example.licenta2.databinding.FragmentLogoutBinding

class LogoutFragment : Fragment() {



    private lateinit var logoutViewModel: LogoutViewModel
    private var _binding: FragmentLogoutBinding? = null
    private  val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        logoutViewModel = ViewModelProvider(this).get(LogoutViewModel::class.java)
        _binding = FragmentLogoutBinding.inflate(inflater, container, false)
        val root:View = binding.root

        binding.btnDeconectare.setOnClickListener {
            val intent = Intent(requireContext(), FirebaseInregistrare::class.java)
            startActivity(intent)
        }

        binding.buttonFeedback.setOnClickListener {
            trimiteFeedbackEmail()
        }

        return root
    }

    private fun trimiteFeedbackEmail() {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:dumitriuana288@gmail.com")
            putExtra(Intent.EXTRA_SUBJECT, "Feedback Liliana")
            putExtra(Intent.EXTRA_TEXT, "Mesaj feedback: ")
        }
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(requireContext(), "Nu există aplicații de email instalate.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding =null
    }

}