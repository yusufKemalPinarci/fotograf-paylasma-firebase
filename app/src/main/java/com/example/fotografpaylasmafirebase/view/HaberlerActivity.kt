package com.example.fotografpaylasmafirebase.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fotografpaylasmafirebase.R
import com.example.fotografpaylasmafirebase.adapter.HaberRecyclerAdapter
import com.example.fotografpaylasmafirebase.model.Post
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class HaberlerActivity : AppCompatActivity() {
    private  lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseFirestore
    private lateinit var recyclerViewAdapter:HaberRecyclerAdapter

    var PostListesi =ArrayList<Post>()


    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.title = "Haydarların Paylaştığı Resimler"

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_haberler)

        auth = FirebaseAuth.getInstance()
        database = FirebaseFirestore.getInstance()

        verileriAl()
        var layoutManager=LinearLayoutManager(this)
        val recylerView = findViewById<RecyclerView>(R.id.haberler_recyclerView)
        recylerView.layoutManager=layoutManager
        recyclerViewAdapter=HaberRecyclerAdapter(PostListesi)
        recylerView.adapter=recyclerViewAdapter




    }

    fun verileriAl() {

        database.collection("Post").orderBy("tarih", Query.Direction.DESCENDING)

            .addSnapshotListener { snapshot, error ->

                if (error != null) {
                    Toast.makeText(this, error.localizedMessage, Toast.LENGTH_LONG).show()


                } else {
                    if (snapshot != null) {
                        val documents = snapshot.documents
                        PostListesi.clear()
                        for (document in documents) {

                            val kullaniciEmail = document.get("kullaniciEmail") as String
                            val kullaniciYorum = document.get("kullaniciYorum") as String
                            val gorselUrl = document.get("gorselUrl") as String


                            val indirilenPost= Post(kullaniciEmail,kullaniciYorum, gorselUrl)
                            PostListesi.add(indirilenPost)


                        }

                        recyclerViewAdapter.notifyDataSetChanged()
                    }
                }
            }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.secenekler_menusu, menu)



        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.fotografPaylas) {
            val intent = Intent(this, FotografPaylasmaActivity::class.java)
            startActivity(intent)


        } else if (item.itemId == R.id.cikisYap) {
            auth.signOut()
            val intent = Intent(this, KullaniciActivity::class.java)
            startActivity(intent)
            finish()
        }

        return super.onOptionsItemSelected(item)
    }

}