package com.example.licenta2.ui.autentificare

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.licenta2.R


private val handler = Handler()

private val delayDuration = 2000L

class SplashFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_splash, container, false)

        // Programăm înlocuirea fragmentului cu fragmentul de login după întârzierea specificată
        handler.postDelayed({
            findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
        }, delayDuration)

        return view


    }

    override fun onDestroyView() {

        handler.removeCallbacksAndMessages(null)
        super.onDestroyView()
    }

}