package com.example.homework_m4_1.ui.profile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.homework_m4_1.R
import com.example.homework_m4_1.databinding.FragmentProfileSettingBinding

class ProfileSettingFragment : Fragment() {

    private var _binding: FragmentProfileSettingBinding? = null
    private val binding get() = _binding!!

    private lateinit var args: NotificationsFragmentArgs
    private val PICK_IMAGE_REQUEST = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let { args = NotificationsFragmentArgs.fromBundle(it) }
        initView()
        initListeners()
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Bundle>(KEY2)?.observe(
            viewLifecycleOwner
        ) { result ->
            setFragmentResult(KEY2, result)
        }
    }

    private fun initView() {
        binding.editUsername.setText(args.userNamearg)
        binding.editEmail.setText(args.userEmailarg)
    }

    private fun initListeners() {
        binding.btnSave.setOnClickListener {
            val userDescription = binding.editUsername.text.toString()
            val emailDescription = binding.editEmail.text.toString()

            setFragmentResult(
                NotificationsFragment.KEY2,
                bundleOf(
                    NotificationsFragment.USERNAME_KEY to userDescription,
                    NotificationsFragment.EMAIL_KEY to emailDescription
                )
            )

            findNavController().navigateUp()
        }
        binding.userAvatar.setOnClickListener {
            openGallery()
        }

    }
    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            val imageUri = data.data

            setFragmentResult(
                NotificationsFragment.KEY2,
                bundleOf(
                    NotificationsFragment.AVATAR_URI_KEY to imageUri.toString(),
                    NotificationsFragment.USERNAME_KEY to binding.editUsername.text.toString(),
                    NotificationsFragment.EMAIL_KEY to binding.editEmail.text.toString()
                )
            )
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val KEY2 = "KEY"
    }
}
