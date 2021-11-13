package com.example.tiptime.ui.calculate

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tiptime.R
import com.example.tiptime.databinding.FragmentCalculateBinding
import java.text.NumberFormat

class CalculateFragment : Fragment() {

    private lateinit var calculateViewModel: CalculateViewModel
    private var _binding: FragmentCalculateBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        calculateViewModel =
            ViewModelProvider(this).get(CalculateViewModel::class.java)

        _binding = FragmentCalculateBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.calculatebutton
        calculateViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        _binding?.calculatebutton?.setOnClickListener{calculatetip()}
        return root


    }

    private fun calculatetip(){
        val stringTexField = binding.costOfService.text.toString()
        val cost = stringTexField.toDoubleOrNull()
        if (cost == null){
            binding.tipResult.text = ""
            return
        }

        val tipPercentage = when(binding.tipOptions.checkedRadioButtonId){
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }
        var tip = tipPercentage * cost
        if (binding.roundUpSwitch.isChecked) {
            tip = kotlin.math.ceil(tip)
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        _binding?.tipResult?.text = getString(R.string.tip_amount,formattedTip)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}