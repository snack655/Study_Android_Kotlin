package kr.co.study.ch15_service

import android.annotation.TargetApi
import android.app.Service
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.getSystemService
import kotlinx.coroutines.*
import kr.co.study.ch15_outer.MyAIDLInterface
import kr.co.study.ch15_service.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var connectionMode = "none"

    //Messenger
    lateinit var messenger: Messenger
    lateinit var replyMessenger: Messenger
    var messengerJob: Job? = null

    //aidl
    var aidlService: MyAIDLInterface? = null
    var aidlJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //messenger..........
        onCreateMessengerService()

        //aidl................
        onCreateAIDLService()

        //jobscheduler......................
        onCreateJobScheduler()

    }

    override fun onStop() {
        super.onStop()
        if(connectionMode === "messenger"){
            onStopMessengerService()
        }else if(connectionMode === "aidl"){
            onStopAIDLService()
        }
        connectionMode="none"
        changeViewEnable()
    }

    fun changeViewEnable() = when (connectionMode) {
        "messenger" -> {
            binding.messengerPlay.isEnabled = false
            binding.aidlPlay.isEnabled = false
            binding.messengerStop.isEnabled = true
            binding.aidlStop.isEnabled = false
        }
        "aidl" -> {
            binding.messengerPlay.isEnabled = false
            binding.aidlPlay.isEnabled = false
            binding.messengerStop.isEnabled = false
            binding.aidlStop.isEnabled = true
        }
        else -> {
            //초기상태. stop 상태. 두 play 버튼 활성상태
            binding.messengerPlay.isEnabled = true
            binding.aidlPlay.isEnabled = true
            binding.messengerStop.isEnabled = false
            binding.aidlStop.isEnabled = false

            binding.messengerProgress.progress = 0
            binding.aidlProgress.progress = 0
        }
    }

    // Messenger Handler
    inner class HandlerReplyMsg : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                10 -> {
                    // 재생 후 지속 시간이 전송되면
                    val bundle = msg.obj as Bundle
                    bundle.getInt("duration")?.let {
                        when {
                            it > 0 -> {
                                binding.messengerProgress.max = it
                                val backgroundScope = CoroutineScope(Dispatchers.Default + Job())
                                messengerJob = backgroundScope.launch {
                                    while (binding.messengerProgress.progress < binding.messengerProgress.max) {
                                        delay(1000)
                                        binding.messengerProgress.incrementProgressBy(1000)
                                    }
                                }
                                changeViewEnable()
                            }
                            else -> {
                                connectionMode = "none"
                                unbindService(messengerConnection)
                                changeViewEnable()
                            }
                        }
                    }
                }
            }
        }
    }

    // Messenger connection
    val messengerConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.d("TestTest", "onServiceConnected... ")
            messenger = Messenger(service)
            val msg = Message()
            msg.replyTo = replyMessenger
            msg.what = 10
            messenger.send(msg)
            connectionMode = "messenger"
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            Log.d("TestTest", "onServiceDisconnected... ")
        }
    }

    private fun onCreateMessengerService() {
        replyMessenger = Messenger(HandlerReplyMsg())
        binding.messengerPlay.setOnClickListener {
            val intent = Intent("ACTION_SERVICE_Messenger")
            intent.setPackage("kr.co.study.ch15_outer")
            bindService(intent, messengerConnection, Context.BIND_AUTO_CREATE)
        }
        binding.messengerStop.setOnClickListener {
            val msg = Message()
            msg.what = 20
            messenger.send(msg)
            unbindService(messengerConnection)
            messengerJob?.cancel()
            connectionMode = "none"
            changeViewEnable()
        }
    }
    private fun onStopMessengerService() {
        val msg = Message()
        msg.what = 20
        messenger.send(msg)
        unbindService(messengerConnection)
    }

    //aidl connection
    val aidlConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            aidlService = MyAIDLInterface.Stub.asInterface(service)
            aidlService!!.start()
            binding.aidlProgress.max = aidlService!!.maxDuration
            val backgroundScope = CoroutineScope(Dispatchers.Default + Job())
            aidlJob = backgroundScope.launch {
                while (binding.aidlProgress.progress < binding.aidlProgress.max) {
                    delay(1000)
                    binding.aidlProgress.incrementProgressBy(1000)
                }
            }
            connectionMode = "aidl"
            changeViewEnable()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            aidlService = null
        }
    }
    private fun onCreateAIDLService() {
        binding.aidlPlay.setOnClickListener {
            val intent = Intent("ACTION_SERVICE_AIDL")
            intent.setPackage("kr.co.study.ch15_outer")
            bindService(intent, aidlConnection, Context.BIND_AUTO_CREATE)
        }
        binding.aidlStop.setOnClickListener {
            aidlService!!.stop()
            unbindService(aidlConnection)
            aidlJob?.cancel()
            connectionMode="none"
            changeViewEnable()
        }
    }
    private fun onStopAIDLService() {
        unbindService(aidlConnection)
    }

    //JobScheduler
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun onCreateJobScheduler(){

    }

}