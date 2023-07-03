package com.example.jobdesk.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jobdesk.R
import com.example.jobdesk.application.jobdeskApp
import com.example.jobdesk.databinding.FragmentFirstBinding
import com.example.jobdesk.model.Jobdesk

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var applicationContext: Context
    private val jobdeskViewModel: jobdeskViewModel by viewModels {
        JobdeskViewModelFactory((applicationContext as jobdeskApp).repository)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        applicationContext = requireContext().applicationContext
    }


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = jobdeskListAdapter{ Jobdesk ->
            val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(Jobdesk)
            findNavController().navigate(action)
        }
        binding.dataRecyclerView.adapter = adapter
        binding.dataRecyclerView.layoutManager = LinearLayoutManager(context)
        jobdeskViewModel.allJobdesk.observe(viewLifecycleOwner) { Jobdesk ->
            Jobdesk.let {
                if (Jobdesk.isEmpty()) {
                    binding.emptyTextView.visibility = View.VISIBLE
                    binding.illustrationImageView.visibility = View.VISIBLE
                }else{
                    binding.emptyTextView.visibility = View.GONE
                    binding.emptyTextView.visibility = View.GONE
                }
                adapter.submitList(Jobdesk)
            }

        }

        binding.addFAB.setOnClickListener {
          //  val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(null)
            //findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}