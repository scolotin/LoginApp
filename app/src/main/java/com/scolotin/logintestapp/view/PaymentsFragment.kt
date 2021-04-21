package com.scolotin.logintestapp.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.scolotin.logintestapp.R
import com.scolotin.logintestapp.databinding.FragmentPaymentsBinding
import com.scolotin.logintestapp.model.Payment
import com.scolotin.logintestapp.viewmodel.PaymentsViewModel

class PaymentsFragment : Fragment(R.layout.fragment_payments) {
    private var viewBinding: FragmentPaymentsBinding? = null
    private val viewModel: PaymentsViewModel by lazy {
        ViewModelProvider(this).get(PaymentsViewModel::class.java)
    }
    private val paymentAdapter = PaymentsFragmentAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentPaymentsBinding.bind(view)
        viewBinding = binding

        viewBinding?.toolbar?.run {
            inflateMenu(R.menu.menu_payments)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_logout -> {
                        logout()
                        true
                    }
                    else -> false
                }
            }
        }

        viewBinding?.paymentsList?.adapter = paymentAdapter

        viewModel.payments.observe(viewLifecycleOwner, { displayPayments(it) })
        arguments?.let { viewModel.getPayments(it.getString(BUNDLE_EXTRA, null)) }
    }

    private fun logout() {
        arguments = null
        activity?.supportFragmentManager?.apply {
            beginTransaction()
                .replace(R.id.container, LoginFragment.newInstance())
                .commitNow()
        }
    }

    private fun displayPayments(paymentsList: ArrayList<Payment>) {
        paymentAdapter.setPaymentsList(paymentsList)
    }

    override fun onDestroyView() {
        viewBinding = null
        super.onDestroyView()
    }

    companion object {
        const val BUNDLE_EXTRA = "payments"

        fun newInstance(bundle: Bundle) = PaymentsFragment().apply {
            arguments = bundle
        }
    }
}