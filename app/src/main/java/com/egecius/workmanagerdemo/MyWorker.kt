package com.egecius.workmanagerdemo

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {

        doMyWork()

        return Result.success()
    }

    private fun doMyWork() {
        Log.v("Eg:MyWorker:13", "doWork doing work")

        val threadName = Thread.currentThread().name
        Log.v("Eg:MyWorker:21", "doMyWork threadName $threadName")
    }
}