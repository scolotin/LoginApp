package com.scolotin.logintestapp.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.scolotin.logintestapp.R
import com.scolotin.logintestapp.databinding.FragmentLoginBinding
import com.scolotin.logintestapp.model.Authentication
import com.scolotin.logintestapp.viewmodel.LoginViewModel

class LoginFragment : Fragment(R.layout.fragment_login) {
    private var viewBinding: FragmentLoginBinding? = null
    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentLoginBinding.bind(view)
        viewBinding = binding

        viewModel.authenticationStatus.observe(viewLifecycleOwner, { checkAuthentication(it) })

        viewBinding?.btnLogin?.setOnClickListener {
            val username = viewBinding?.fieldLogin?.text.toString()
            val password = viewBinding?.fieldPassword?.text.toString()

            showInvalidLogin(username.isBlank())
            showInvalidPassword(password.isBlank())

            if (username.isNotBlank() && password.isNotBlank()) {
                showInvalidLogin(false)
                showInvalidPassword(false)
                viewModel.authUser(username, password)
            }
        }
    }

    private fun showInvalidLogin(isShow: Boolean) {
        viewBinding?.fieldLoginLayout?.error = if (isShow) {
                                                   getString(R.string.txt_error_login)
                                               }
                                               else null
    }

    private fun showInvalidPassword(isShow: Boolean) {
        viewBinding?.fieldPasswordLayout?.error = if (isShow) {
                                                      getString(R.string.txt_error_password)
                                                  }
                                                  else null
    }

    private fun checkAuthentication(authentication: Authentication) {
        when (authentication.status) {
            "true" -> login(authentication.token!!)
            else   -> showErrorAuth(authentication.errorMsg!!)
        }
    }

    private fun showErrorAuth(errorMsg: String) {
        this.view?.let { Snackbar.make(it, errorMsg, Snackbar.LENGTH_SHORT).show() }
    }

    private fun login(token: String) {
        activity?.supportFragmentManager?.apply {
            beginTransaction()
                .replace(R.id.container, PaymentsFragment.newInstance(Bundle().apply {
                    putString(PaymentsFragment.BUNDLE_EXTRA, token)
                }))
                .commitNow()
        }
    }

    override fun onDestroyView() {
        viewBinding = null
        super.onDestroyView()
    }

    companion object {
        fun newInstance() = LoginFragment()
    }
}