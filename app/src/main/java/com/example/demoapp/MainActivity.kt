package com.example.demoapp

import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.demoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // enable view binding
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnSubmit.setOnClickListener {
            if(binding.numberSet1.text.toString().trim().isNotEmpty() &&
                binding.numberSet2.text.toString().trim().isNotEmpty() &&
                binding.numberSet3.text.toString().trim().isNotEmpty()){

               hideKeyboard()

                binding.apply {
                    // show the input data
                    tVSet1.setText(binding.numberSet1.text.toString().trim())
                    tVSet2.setText(binding.numberSet2.text.toString().trim())
                    tVSet3.setText( binding.numberSet3.text.toString().trim())

                    // make other text field visible
                    tVSet1.visibility=View.VISIBLE
                    tVSet2.visibility=View.VISIBLE
                    tVSet3.visibility=View.VISIBLE
                    btnCalculate.visibility=View.VISIBLE
                    txtV1.visibility=View.VISIBLE
                    txtV2.visibility=View.VISIBLE
                    txtV3.visibility=View.VISIBLE

                    // clear the text input filed
                    numberSet1.text?.clear()
                    numberSet2.text?.clear()
                    numberSet3.text?.clear()
                }
            }
        }

        binding.btnCalculate.setOnClickListener {
            binding.apply {
                // result text field visible
                tVUnion.visibility=View.VISIBLE
                tVHighestNumber.visibility=View.VISIBLE
                tVIntersect.visibility=View.VISIBLE
                btnClear.visibility=View.VISIBLE
                // put input data to list
                val list1 = tVSet1.text.toString().split(",").map { it.trim().toInt() }
                val list2 = tVSet2.text.toString().split(",").map { it.trim().toInt() }
                val list3 = tVSet3.text.toString().split(",").map { it.trim().toInt() }

                // prepare result
                val intersect = list1.intersect(list2).intersect(list3)
                val union = list1.union(list2).union(list3)
                val highest = union.maxOrNull() ?: 0

                //show result
                tVUnion.setText("Union: ${union.joinToString(", ")}")
                tVIntersect.setText("Intersect: ${intersect.joinToString(", ")}")
                tVHighestNumber.setText("Highest: $highest")
            }
        }

        binding.btnClear.setOnClickListener {
            binding.apply {
                tVUnion.visibility=View.INVISIBLE
                tVIntersect.visibility=View.INVISIBLE
                tVHighestNumber.visibility=View.INVISIBLE
                tVSet1.visibility=View.INVISIBLE
                tVSet2.visibility=View.INVISIBLE
                tVSet3.visibility=View.INVISIBLE
                btnClear.visibility=View.INVISIBLE
                btnCalculate.visibility=View.INVISIBLE
                txtV1.visibility=View.INVISIBLE
                txtV2.visibility=View.INVISIBLE
                txtV3.visibility=View.INVISIBLE
            }
        }

    }
    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        val currentFocusView = currentFocus
        currentFocusView?.let {
            imm.hideSoftInputFromWindow(it.windowToken, 0)
            it.clearFocus()

        }
    }
}
