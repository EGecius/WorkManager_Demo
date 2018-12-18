package com.egecius.workmanagerdemo

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import java.util.*

class MainActivity : AppCompatActivity() {

    private var uuidOfQueuedRequest : UUID? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupCancelButton()
    }

    private fun setupCancelButton() {
        findViewById<Button>(R.id.cancel_work).setOnClickListener {
            uuidOfQueuedRequest?.let { it1 -> WorkManager.getInstance().cancelWorkById(it1) }
        }
    }

    override fun onResume() {
        super.onResume()
        demoWorkManager()
    }

    private fun demoWorkManager() {

        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .build()

        val request: OneTimeWorkRequest = OneTimeWorkRequest.Builder(MyWorker::class.java)
            .setConstraints(constraints)
            .addTag("egis")
            .build()

        WorkManager.getInstance().enqueue(request)
        uuidOfQueuedRequest = request.id

        observeWorkerProgress(request)
    }

    private fun observeWorkerProgress(request: OneTimeWorkRequest) {
        WorkManager.getInstance().getWorkInfoByIdLiveData(request.id).observe(this, Observer { workInfo ->
            val state = workInfo?.state
            Log.i("Eg:MainActivity:39", "observeWorkerProgress workInfo $state")
            Toast.makeText(this, "$state", Toast.LENGTH_SHORT).show()
        })
    }
}
