package com.example.homework_m4_1.ui.onboarding

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.homework_m4_1.R
import com.example.homework_m4_1.databinding.FragmentOnbordingBinding
import com.example.homework_m4_1.databinding.FragmentOnbordingThreeBinding


class OnbordingThreeFragment : Fragment() {
    private lateinit var binding: FragmentOnbordingThreeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnbordingThreeBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.finish.setOnClickListener {
            findNavController().navigate(R.id.action_onViewPagerFragment_to_navigation_home)
            onBoardingFinished()
        }

        return view
    }

    private fun onBoardingFinished(){
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", true)
        editor.apply()
    }

}