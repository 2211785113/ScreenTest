package com.example.screentest

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.enjoy.screenpush.IMyAidlInterface

/**
 * 通过隐式意图来绑定服务
 */
class MainActivity : AppCompatActivity() {

    private lateinit var iMyAidlInterface: IMyAidlInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //第一步
        bindService(Intent("cc.abto.server"), object : ServiceConnection {
            override fun onServiceDisconnected(name: ComponentName?) {

            }

            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service)
            }
        }, Context.BIND_AUTO_CREATE)

        //第二步
        findViewById<Button>(R.id.btn_click).setOnClickListener {
            Toast.makeText(this, iMyAidlInterface.name, Toast.LENGTH_SHORT).show() //打印name
        }
    }
}