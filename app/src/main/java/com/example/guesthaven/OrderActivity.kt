package com.example.guesthaven

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class OrderActivity : AppCompatActivity() {
    private lateinit var orderRecyclerView: RecyclerView
    private lateinit var orderAdapter: DataOrderAdapter
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        orderRecyclerView = findViewById(R.id.orderList)
        val ordedrArrayList = ArrayList<DatabaseOrder>()
        orderAdapter = DataOrderAdapter(ordedrArrayList)
        orderRecyclerView.adapter = orderAdapter
        orderRecyclerView.layoutManager = LinearLayoutManager(this)

        dbRef = FirebaseDatabase.getInstance().reference.child("booking")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val newList = mutableListOf<DatabaseOrder>()
                for (dataSnapshot in snapshot.children) {
                    val order = DatabaseOrder(
                        dataSnapshot.child("name").getValue(String::class.java) ?: "",
                        dataSnapshot.child("location").getValue(String::class.java) ?: "",
                        dataSnapshot.child("order_name").getValue(String::class.java) ?: "",
                        dataSnapshot.child("number").getValue(String::class.java) ?: "",
                        dataSnapshot.child("date").getValue(String::class.java) ?: "",
                    )
                    newList.add(order)
                }
                ordedrArrayList.addAll(newList) // Add new data
                orderAdapter.notifyDataSetChanged() // Notify adapter about the changes
            }


            override fun onCancelled(error: DatabaseError) {
                Log.e("FirebaseData", "onCancelled: $error")
            }
        })
    }
}