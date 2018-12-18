package com.egecius.workmanagerdemo

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

        observeWorkerProgress(request)
    }

    private fun observeWorkerProgress(request: OneTimeWorkRequest) {
        WorkManager.getInstance().getWorkInfoByIdLiveData(request.id).observe(this, Observer { workInfo ->
            Log.i("Eg:MainActivity:39", "observeWorkerProgress workInfo $workInfo")
        })
    }
}
