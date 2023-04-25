package com.example.licenta2.ui.facturi.adaugare_facturi.selectie

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.licenta2.R

class SelectareProdusFragment : Fragment() {

    companion object {
        fun newInstance() = SelectareProdusFragment()
    }

    private lateinit var viewModel: SelectareProdusViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_selectare_produs, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SelectareProdusViewModel::class.java)
        // TODO: Use the ViewModel
    }

}