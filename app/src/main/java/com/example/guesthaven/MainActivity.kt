package com.example.guesthaven

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var btn_get : Button
    private lateinit var btn_order : Button
    private lateinit var btn_contact : Button
    private lateinit var btn_login_admin : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btn_get = findViewById(R.id.btn_get)
        btn_order = findViewById(R.id.btn_ord)
        btn_contact = findViewById(R.id.btn_contact)
        btn_login_admin = findViewById(R.id.btn_login_adm)

        btn_get.setOnClickListener{
            startActivity(Intent(this,DataActivity::class.java))
        }

        btn_order.setOnClickListener {
            startActivity(Intent(this,OrderActivity::class.java))
        }

        btn_contact.setOnClickListener {
            startActivity(Intent(this,NewContactActivity::class.java))
        }

        btn_login_admin.setOnClickListener{
            startActivity(Intent(this,LoginActivity::class.java))
        }
    }
}