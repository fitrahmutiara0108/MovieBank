package com.mandiri.moviebank.presentation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.mandiri.moviebank.databinding.FragmentProfileBinding
import com.mandiri.moviebank.helper.SharedPrefHelper
import com.mandiri.moviebank.presentation.home.LoginActivity

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedPrefHelper: SharedPrefHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPrefHelper = SharedPrefHelper(
            requireActivity().getSharedPreferences(
                "mypref",
                AppCompatActivity.MODE_PRIVATE
            )
        )

        val profileData = sharedPrefHelper.getProfileData()
        binding.tvProfileName.text = profileData.first
        binding.tvProfileEmail.text = profileData.second
        binding.tvName.text = profileData.first
        binding.tvEmail.text = profileData.second
        binding.tvPhoneNumber.text = profileData.third

        binding.btnLogout.setOnClickListener {
            sharedPrefHelper.clearDataPref()
            startActivity(Intent(requireContext(), LoginActivity::class.java))
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
