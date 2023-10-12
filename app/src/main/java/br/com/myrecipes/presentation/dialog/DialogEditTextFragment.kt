package br.com.myrecipes.presentation.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.setFragmentResult
import br.com.myrecipes.databinding.FragmentDialogEditTextBinding
import java.lang.IllegalStateException

class DialogEditTextFragment : DialogFragment() {

    private lateinit var binding: FragmentDialogEditTextBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val titleText = arguments?.getString(TITLE_TEXT)
            ?: throw IllegalArgumentException("Dialog can't load without title")
        val placeholderText = arguments?.getString(PLACE_HOLDER)
            ?: throw IllegalArgumentException("Dialog can't load without a placeholder")

        return activity?.let {
            binding =
                FragmentDialogEditTextBinding.inflate(requireActivity().layoutInflater).apply {
                    etEditText.hint = placeholderText
                    tvTitle.text = titleText
                }

            AlertDialog.Builder(it)
                .setView(binding.root)
                .setPositiveButton("Confirmar") { _, _ ->
                    setFragmentResult(
                        FRAGMENT_RESULT,
                        bundleOf(
                            EDIT_TEXT_VALUE to binding.etEditText.text.toString()
                        )
                    )
                }
                .setNegativeButton("Cancelar") { _, _ ->
                    dismiss()
                }
                .create()
        } ?: throw IllegalStateException("A activity não pode ser nula")
    }

    companion object {
        const val TITLE_TEXT = "TITLE_TEXT"
        const val PLACE_HOLDER = "PLACE_HOLDER"
        const val FRAGMENT_RESULT = "FRAGMENT_RESULT"
        const val EDIT_TEXT_VALUE = "EDIT_TEXT_VALUE"

        fun show(
            title : String,
            placeholder : String,
            fragmentManager: FragmentManager,
            tag: String = DialogEditTextFragment::class.java.simpleName.toString()
        ) {
            DialogEditTextFragment().apply {
                arguments = bundleOf(
                    TITLE_TEXT to title,
                    PLACE_HOLDER to placeholder,
                )
            }.show(
                fragmentManager,
                tag,
            )
        }
    }
}