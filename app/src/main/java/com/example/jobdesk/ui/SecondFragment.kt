package com.example.jobdesk.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.jobdesk.R
import com.example.jobdesk.application.jobdeskApp
import com.example.jobdesk.databinding.FragmentSecondBinding
import com.example.jobdesk.model.Jobdesk

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var applicationContext: Context
    private val jobdeskViewModel: jobdeskViewModel by viewModels {
        JobdeskViewModelFactory((applicationContext as jobdeskApp).repository)
    }
    private val args : SecondFragmentArgs by navArgs()
    private var jobdesk: Jobdesk? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        applicationContext = requireContext().applicationContext
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        jobdesk = args.Jobdek
        if (jobdesk != null) {
            binding.saveButton.text = "Ubah"
        }
        val name = binding.nameEditTextText.text
        val address = binding.addressEditText.text
        val posicion = binding.posicionEditText.text

        binding.saveButton.setOnClickListener {
            //update
            if(name.isEmpty() || address.isEmpty() || posicion.isEmpty()){
                if (name.isEmpty()){
                    Toast.makeText(context, "name is required", Toast.LENGTH_SHORT).show()
                }
                if (address.isEmpty()){
                    Toast.makeText(context, "address is required", Toast.LENGTH_SHORT).show()
                }
                if (posicion.isEmpty()){
                    Toast.makeText(context, "posicion is required", Toast.LENGTH_SHORT).show()
                }
            }else{
                if (jobdeks == null){
                    val course = Jobdeks(name = name.toString(), address = address.toString(), posicion = posicion.toString())
                    jobdeksViewModel.insert(Jobdeks)
                }else{
                    val course = Jobdeks(id = Jobdeks?.id!!, name = name.toString(), address = address.toString(), posicion = posicion.toString())
                    jobdeksViewModel.update(Jobdeks)
                }
            findNavController().popBackStack() //un
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
