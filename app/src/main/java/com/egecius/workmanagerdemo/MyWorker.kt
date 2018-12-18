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
        val threadName = Thread.currentThread().name
        Log.v("Eg:MyWorker:13", "doWork starting work on thread:.... $threadName")

        Thread.sleep(3_000)

        Log.v("Eg:MyWorker:23", "..... work finished")
    }
}