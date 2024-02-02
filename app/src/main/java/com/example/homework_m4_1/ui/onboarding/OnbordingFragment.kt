package com.example.homework_m4_1.ui.onboarding

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.homework_m4_1.R
import com.example.homework_m4_1.databinding.FragmentOnbordingBinding
class OnbordingFragment : Fragment() {
    private lateinit var binding: FragmentOnbordingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnbordingBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.nextbtn.setOnClickListener {
            val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)
            viewPager?.currentItem = 1
        }

        return view
    }


}