package com.example.flashlightapp

import android.content.Context
import android.hardware.camera2.CameraManager
import android.media.MediaPlayer



import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {

    private lateinit var buttonFlash: ImageButton

    private lateinit var cameraM: CameraManager
    private var isFlash = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonFlash =findViewById(R.id.button)

//подключение звука
        val mediaPlayer = MediaPlayer.create(this, R.raw.click2)

        //подключение фонарика
        cameraM = getSystemService(Context.CAMERA_SERVICE) as CameraManager

        //слушатель нажатий
        buttonFlash.setOnClickListener {
            flashLightOnRoOff()
            mediaPlayer.start()
        }
    }
    //добавляем картинки и включаем фонарик
    private fun flashLightOnRoOff() =
        if (!isFlash) {
            val cameraListId = cameraM.cameraIdList[0]
            cameraM.setTorchMode(cameraListId, true)
            isFlash = true
            buttonFlash.setImageResource(R.drawable.button_green)

        } else {
            val cameraListId = cameraM.cameraIdList[0]
            cameraM.setTorchMode(cameraListId, false)
            isFlash = false
            buttonFlash.setImageResource(R.drawable.button_red)

        }
}
