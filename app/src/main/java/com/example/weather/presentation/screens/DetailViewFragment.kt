package com.example.weather.presentation.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.weather.databinding.FragmentDetailViewBinding

/**
 * Created by Arun @ak - 14213  on 30/09/24.
 */
class DetailViewFragment: Fragment() {


    private lateinit var binding : FragmentDetailViewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailViewBinding.inflate(inflater,container,false)
        return binding.root
    }

}