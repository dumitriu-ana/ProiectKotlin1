package com.example.licenta2.ui.comenzi

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.licenta2.R

class ComenziFragment : Fragment() {

    companion object {
        fun newInstance() = ComenziFragment()
    }

    private lateinit var viewModel: ComenziViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_comenzi, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ComenziViewModel::class.java)
        // TODO: Use the ViewModel
    }

}