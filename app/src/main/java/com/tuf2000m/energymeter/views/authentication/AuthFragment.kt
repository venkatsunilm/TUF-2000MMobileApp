package com.tuf2000m.energymeter.views.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.tuf2000m.energymeter.R

import com.tuf2000m.energymeter.data.model.auth.Auth
import com.tuf2000m.energymeter.databinding.FragmentAuthBinding
import com.tuf2000m.energymeter.utils.SharedPreferenceManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AuthFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!
    private var isLogin = true

    @Inject
    lateinit var sharedPreferences: SharedPreferenceManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth

        binding.buttonLogin.setOnClickListener {
            if (binding.editTextEmail.text.isNotEmpty() && binding.editTextPassword.text.isNotEmpty()) {
                binding.buttonLogin.visibility = View.INVISIBLE
                binding.pb.visibility = View.VISIBLE
                if (!isLogin) {
                    createAccount(
                        binding.editTextEmail.text.toString(),
                        binding.editTextPassword.text.toString()
                    )
                } else {
                    signIn(
                        binding.editTextEmail.text.toString(),
                        binding.editTextPassword.text.toString()
                    )
                }
            }
        }
        binding.tvSignup.setOnClickListener {
            isLogin = !isLogin
            setLoginSignupView()
        }
    }

    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    sharedPreferences.saveUSer(Auth(user?.email, user?.displayName))
                    navigateToHome()
                } else {
                    showHideViews()
                    Toast.makeText(requireContext(), task.exception?.message, Toast.LENGTH_SHORT)
                        .show()
                }
            }
    }

    private fun showHideViews() {
        binding.buttonLogin.visibility = View.VISIBLE
        binding.pb.visibility = View.GONE
    }

    private fun navigateToHome() {
        findNavController().navigate(R.id.action_authFragment_to_homeFragment)
        binding.editTextEmail.text.clear()
        binding.editTextPassword.text.clear()
    }

    private fun signIn(email: String, password: String) {

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    sharedPreferences.saveUSer(Auth(user?.email, user?.displayName))
                    navigateToHome()
                } else {
                    showHideViews()
                    Toast.makeText(
                        requireContext(),
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()

                }
            }

    }

    private fun setLoginSignupView() {
        if (isLogin) {
            binding.buttonLogin.text = getString(R.string.login)
            binding.tvSignup.text = getString(R.string.dha)
        } else {
            binding.buttonLogin.text = getString(R.string.signup)
            binding.tvSignup.text = getString(R.string.btl)

        }
    }

    override fun onResume() {
        super.onResume()
        validateUser()
    }

    private fun validateUser() {
        if (sharedPreferences.getUser() != null)
            navigateToHome()
    }
}