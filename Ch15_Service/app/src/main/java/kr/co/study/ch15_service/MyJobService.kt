package kr.co.study.ch15_service

import android.annotation.TargetApi
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.resources.Compatibility.Api18Impl.setAutoCancel
import androidx.core.content.getSystemService

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
class MyJobService : JobService() {
    override fun onStartJob(params: JobParameters?): Boolean {
        Log.d("TestTest", "onStartJob...............")
        val manager = getSystemService<NotificationManager>()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("oneId", "oneName", NotificationManager.IMPORTANCE_DEFAULT)
            channel.description = "oneDesc"
            manager?.createNotificationChannel(channel)
            Notification.Builder(this, "oneId")
        } else {
            Notification.Builder(this)
        }.run {
            setSmallIcon(android.R.drawable.ic_notification_overlay)
            setContentTitle("JobScheduler Title")
            setContentText("ContentMessage")
            setAutoCancel(true)
            manager?.notify(1, build())
        }
        return false
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        return false
    }
}