package com.example.homework_m4_1.ui.profile

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.homework_m4_1.databinding.FragmentNotificationsBinding
import com.example.homework_m4_1.ui.home.viewpager.HomeFragmentArgs
import com.example.homework_m4_1.ui.home.TaskEditFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions


class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initListeners()



        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFragmentResultListener(KEY2) { _, result ->
            val username = result.getString(USERNAME_KEY)
            val email = result.getString(EMAIL_KEY)
            val avatarUri = result.getString(AVATAR_URI_KEY)
            binding.userName.text = username
            binding.userEmail.text = email
            if (!avatarUri.isNullOrEmpty()) {
                Glide.with(this)
                    .load(Uri.parse(avatarUri))
                    .into(binding.userImage)
            }
        }
    }
    private fun initListeners() {
        val newSettingFragment = binding.settingButton
        newSettingFragment.setOnClickListener {
            navigateToNewProfileFragment()
        }
    }
    private fun navigateToNewProfileFragment() {
        val action = NotificationsFragmentDirections.actionNavigationNotificationsToProfileSettingFragment(
            "Username",
            "Email"
        )
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object {
        const val KEY2 = "KEY"
        const val USERNAME_KEY = "USERNAME"
        const val EMAIL_KEY = "EMAIL"
        const val AVATAR_URI_KEY = "AVATAR_URI"
    }
}