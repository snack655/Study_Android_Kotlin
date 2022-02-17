package kr.co.study.ch15_outer

import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.*
import java.lang.Exception

class MyMessengerService : Service() {
    // 액티비티의 데이터를 전달받는 메신저
    lateinit var messenger: Messenger
    // 액티비티에 데이터를 전달하는 메신저
    lateinit var replyMessenger: Messenger
    lateinit var player: MediaPlayer

    override fun onCreate() {
        super.onCreate()
        player = MediaPlayer()
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }

    // 액티비티로부터 메시지가 전달되었을 때
    inner class IncomingHandler(
        context: Context,
        private val applicationContext: Context = context.applicationContext
    ) : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                10 -> {
                    // 서비스에 연결되자마자 전달되는 메시지
                    replyMessenger = msg.replyTo
                    if (!player.isPlaying) {
                        player = MediaPlayer.create(this@MyMessengerService, R.raw.music)
                        try {
                            // 지속 시간 전송
                            val replyMsg = Message()
                            replyMsg.what = 10
                            val replyBundle = Bundle()
                            replyBundle.putInt("duration", player.duration)
                            replyMsg.obj = replyBundle
                            replyMessenger.send(replyMsg)
                            // 음악 재생
                            player.start()
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
                20 -> {
                    // 멈춤 메시지
                    if (player.isPlaying)
                        player.stop()
                }
                else -> super.handleMessage(msg)
            }
        }
    }

    override fun onBind(intent: Intent): IBinder {
        messenger = Messenger(IncomingHandler(this))
        return messenger.binder
    }
}