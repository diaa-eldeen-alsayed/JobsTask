package com.example.jobstask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jobstask.adapters.JobAdapter

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
class JobsListScreenFragment() : Fragment() {
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
        setupswipeRefresh()
    }
fun setupswipeRefresh(){
    binding.swipeRefresh.setOnRefreshListener {
        binding.jobsRecyclerView.adapter=null
        subscribeUi()
        binding.swipeRefresh.isRefreshing=false

    }
}
    private fun subscribeUi() {

        jobsViewModel.getAllJobs().observe(requireActivity(), Observer { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    binding.progressBar.visibility=View.INVISIBLE
                    result.let { list ->
                        binding.jobsRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                     jobAdapter= JobAdapter(list.data!!,jobsViewModel)
                        binding.jobsRecyclerView.adapter=jobAdapter

                    }

                }

                Result.Status.ERROR -> {
                    binding.progressBar.visibility=View.INVISIBLE
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