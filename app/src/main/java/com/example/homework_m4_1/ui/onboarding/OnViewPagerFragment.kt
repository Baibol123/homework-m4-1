package com.example.homework_m4_1.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.homework_m4_1.R
import com.example.homework_m4_1.databinding.FragmentOnViewPagerBinding
import com.example.homework_m4_1.databinding.FragmentOnbordingBinding
import com.example.homework_m4_1.databinding.FragmentOnbordingTwoBinding
import com.example.homework_m4_1.ui.onboarding.OnbordingFragment
class OnViewPagerFragment : Fragment() {
    private lateinit var binding: FragmentOnViewPagerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnViewPagerBinding.inflate(inflater, container, false)
        val view = binding.root
        val fragmentList = arrayListOf<Fragment>(
            OnbordingFragment(),
            OnbordingTwoFragment(),
            OnbordingThreeFragment()
        )

        val adapter = OnboardingViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        binding.viewPager.adapter = adapter

        return view
    }

}