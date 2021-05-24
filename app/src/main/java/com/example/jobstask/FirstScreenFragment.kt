package com.example.jobstask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.example.jobstask.databinding.FragmentFirstScreenBinding
import com.example.jobstask.interfaces.ClickHandlers

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [FirstScreenFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstScreenFragment : Fragment() , ClickHandlers.FirstScreenHandler {
    lateinit var binding:FragmentFirstScreenBinding
    lateinit var navController:NavController
    // TODO: Rename and change types of parameters


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_first_screen, container, false)
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.handler=this
        navController=Navigation.findNavController(view)
    }
    override fun onStartClick() {
        navController.navigate(R.id.jobsListScreenFragment)
    }


}