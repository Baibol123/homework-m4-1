package com.example.homework_m4_1.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.homework_m4_1.R
import com.example.homework_m4_1.databinding.FragmentOnbordingBinding
import com.example.homework_m4_1.databinding.FragmentOnbordingTwoBinding


class OnbordingTwoFragment : Fragment() {
    private lateinit var binding: FragmentOnbordingTwoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnbordingTwoBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.nextbtn.setOnClickListener {
            val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)
            viewPager?.currentItem = 2
        }

        return view
    }
}