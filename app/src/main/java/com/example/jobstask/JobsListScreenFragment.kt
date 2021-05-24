package com.example.jobstask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jobstask.adapters.JobAdapter
import com.example.jobstask.databinding.FragmentFirstScreenBinding
import com.example.jobstask.databinding.FragmentJobsListScreenBinding
import com.example.jobstask.viewmodel.JobsViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import com.example.jobstask.model.Result
import com.google.android.material.snackbar.Snackbar


/**
 * A simple [Fragment] subclass.
 * Use the [JobsListScreenFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class JobsListScreenFragment : Fragment() {
    lateinit var binding: FragmentJobsListScreenBinding
    private val jobsViewModel by viewModel<JobsViewModel>()
    private lateinit var jobAdapter: JobAdapter

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
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_jobs_list_screen, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
   subscribeUi()
    }

    private fun subscribeUi() {

        jobsViewModel.jobList.observe(this.viewLifecycleOwner, Observer { result ->

            when (result.status) {
                Result.Status.SUCCESS -> {
                    result.let { list ->
                     jobAdapter= JobAdapter(list.data!!)
                        binding.jobsRecyclerView.adapter=jobAdapter

                    }

                }

                Result.Status.ERROR -> {
                    result.message?.let {
                        showError(it)
                    }

                }

                Result.Status.LOADING -> {

                }
            }

        })
    }
    private fun showError(msg: String) {
        Snackbar.make(requireView(), msg, Snackbar.LENGTH_INDEFINITE).setAction("DISMISS") {
        }.show()
    }
}