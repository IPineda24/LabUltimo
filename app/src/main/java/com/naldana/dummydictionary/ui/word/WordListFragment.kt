package com.naldana.dummydictionary.ui.word

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.naldana.dummydictionary.DummyDictionaryApplication
import com.naldana.dummydictionary.R
import com.naldana.dummydictionary.databinding.FragmentWordListBinding
import com.naldana.dummydictionary.ui.ViewModelFactory

class WordListFragment : Fragment() {
    private val viewModelFactory by lazy {
        val application = requireActivity().application as DummyDictionaryApplication
        ViewModelFactory(application.getDictionaryRepository())
    }
    private val viewModel: WordViewModel by viewModels {
        viewModelFactory
    }
    private lateinit var binding: FragmentWordListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_word_list, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val wordListRecyclerView = binding.wordListRecyclerView
        val wordAdapter = WordAdapter()
        wordListRecyclerView.apply {
            adapter = wordAdapter
        }

        viewModel.getAllWords()

        viewModel.status.observe(viewLifecycleOwner) { status ->
            when(status){
                is WordUiState.Error -> Log.d("Word list status", "Error" , status.exception)
                WordUiState.Loading -> Log.d("Word list status" , "Loading")
                is WordUiState.Success -> handleSuccess(status,wordAdapter)
            }
        }
         }

        private fun handleSuccess(status: WordUiState.Success, wordAdapter: WordAdapter){
            status.word.observe(viewLifecycleOwner){data ->
                wordAdapter.setData(data)
            }
        }
}