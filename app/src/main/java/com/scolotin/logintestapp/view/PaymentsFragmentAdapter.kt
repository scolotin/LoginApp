package com.scolotin.logintestapp.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.scolotin.logintestapp.R
import com.scolotin.logintestapp.databinding.ItemFragmentPaymentsBinding
import com.scolotin.logintestapp.getDate
import com.scolotin.logintestapp.model.Payment

class PaymentsFragmentAdapter : RecyclerView.Adapter<PaymentsFragmentAdapter.ViewHolder>() {

    private var paymentsList: List<Payment> = listOf()

    fun setPaymentsList(paymentsList: List<Payment>) {
        this.paymentsList = paymentsList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context).apply {
            inflate(R.layout.item_fragment_payments, parent, false)
        }
        val viewBinding = ItemFragmentPaymentsBinding.inflate(inflater, parent, false)
        return ViewHolder(viewBinding)
    }

    override fun getItemCount(): Int = paymentsList.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.viewBinding.run {
            desc.text = formatDescriptionAndDate(paymentsList[position].desc, paymentsList[position].created)
            money.text = formatAmountAndCurrency(paymentsList[position].amount, paymentsList[position].currency)
        }
    }

    private fun formatDescriptionAndDate(desc: String, created: Long): String {
        var formatString = ""
        formatString = if (desc.isNotBlank()) {
            formatString.plus(desc)
        } else {
            formatString.plus("---")
        }
        formatString = formatString.plus(" - ")
        formatString = if (created != 0L) {
            formatString.plus(getDate(created))
        } else {
            formatString.plus("---")
        }

        return formatString
    }

    private fun formatAmountAndCurrency(amount: Float?, currency: String?): String {
        var formatString = ""
        formatString = if (amount != null) {
            formatString.plus(amount.toString())
        }
        else {
            formatString.plus("---")
        }
        formatString = formatString.plus(" ")
        formatString = if (!currency.isNullOrBlank()) {
            formatString.plus(currency)
        }
        else {
            formatString.plus("---")
        }

        return formatString
    }

    class ViewHolder(val viewBinding: ItemFragmentPaymentsBinding) : RecyclerView.ViewHolder(
        viewBinding.root
    )
}