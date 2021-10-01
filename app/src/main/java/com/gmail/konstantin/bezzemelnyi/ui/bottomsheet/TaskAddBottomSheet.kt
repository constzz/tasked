package com.gmail.konstantin.bezzemelnyi.ui.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gmail.konstantin.bezzemelnyi.common.extensions.setMultilineActionInputType
import com.gmail.konstantin.bezzemelnyi.common.extensions.setOnEditorActionDone
import com.gmail.konstantin.bezzemelnyi.databinding.BottomsheetDialogFragmentTaskAddBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class TaskAddBottomSheet(
    private val addTask: (content: String) -> Unit
) : BottomSheetDialogFragment() {

    private var _binding: BottomsheetDialogFragmentTaskAddBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomsheetDialogFragmentTaskAddBinding.inflate(inflater)

        setupLayout(binding)

        return binding.root
    }

    private fun setupLayout(binding: BottomsheetDialogFragmentTaskAddBinding) {
        with(binding) {
            contentEt.setMultilineActionInputType()

            confirmTaskAddTextView.setOnClickListener {
                finishAddingTask(contentEt.text.toString())
            }

            contentEt.setOnEditorActionDone {
                finishAddingTask(contentEt.text.toString())
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun finishAddingTask(content: String) {
        addTask(content)
        this@TaskAddBottomSheet.dismiss()
    }

    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle, addTask: (content: String) -> Unit): TaskAddBottomSheet {
            val fragment = TaskAddBottomSheet(addTask)
            fragment.arguments = bundle
            return fragment
        }
    }

}