package com.example.jobstask.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jobstask.R
import com.example.jobstask.adapters.JobAdapter
import com.example.jobstask.databinding.FragmentJobDetailsBinding
import com.example.jobstask.databinding.FragmentJobsListScreenBinding
import com.example.jobstask.interfaces.ClickHandlers
import com.example.jobstask.model.JobItem
import com.example.jobstask.model.Result
import com.example.jobstask.viewmodel.JobsViewModel
import com.google.android.material.snackbar.Snackbar
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber


class JobDetailsFragment : Fragment() , ClickHandlers.JobDetailsHandler {
    lateinit var binding: FragmentJobDetailsBinding
    private var jobId: String?= ""
    private val jobsViewModel by viewModel<JobsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            jobId = it.getString("job_id")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater,
            R.layout.fragment_job_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            subscribeUi()


    }
    private fun subscribeUi() {

        jobId?.let {
            jobsViewModel.getJobById(it).observe(requireActivity(), Observer { result ->
                when (result.status) {
                    Result.Status.SUCCESS -> {
                        binding.  progressBar.visibility=View.INVISIBLE
                        result.let { response ->
                        binding.jobItem=response.data
                           binding.handler=this
                            binding.isFav= response.data!!.isFavorite


                        }

                    }

                }

            })
        }
    }

    override fun onFavoriteClick(jobItem: JobItem) {
        jobItem.isFavorite=!jobItem.isFavorite
        jobsViewModel.updateFavorite(jobItem)
        binding.isFav=jobItem.isFavorite

    }


}