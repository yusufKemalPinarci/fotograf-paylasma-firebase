package com.example.fotografpaylasmafirebase.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.fotografpaylasmafirebase.R
import com.google.firebase.auth.FirebaseAuth

class KullaniciActivity : AppCompatActivity() {
    lateinit var auth : FirebaseAuth
    lateinit var email : EditText
    lateinit var password: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.title = "Haydar Uygulaması"

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_kullanici)
        auth =FirebaseAuth.getInstance()

        val guncelKullanici =auth.currentUser
        if(guncelKullanici !=null){
            val intent = Intent(this, HaberlerActivity::class.java)
            startActivity(intent)
            finish()

        }


    }

    fun girisYap(view: View){
         email = findViewById<EditText>(R.id.emailText)
        password=findViewById<EditText>(R.id.passwordText)
        if (email.text.toString().isEmpty() || password.text.toString().isEmpty()) {
            Toast.makeText(this, "Lütfen e-posta ve şifre alanlarını doldurun.", Toast.LENGTH_LONG).show()
            return
        }


        auth.signInWithEmailAndPassword(email.text.toString(),password.text.toString()).addOnCompleteListener { task->
            if(task.isSuccessful){
                val guncelKullanici =auth.currentUser?.email.toString()
                Toast.makeText(this,"hosgeldin $guncelKullanici",Toast.LENGTH_LONG).show()
                val intent = Intent(this, HaberlerActivity::class.java)
                startActivity(intent)
                finish()


            }
        }.addOnFailureListener { exception->
            Toast.makeText(this,exception.localizedMessage,Toast.LENGTH_LONG).show()

        }

    }
    fun kayitOl(view:View){
        email = findViewById<EditText>(R.id.emailText)
        password=findViewById<EditText>(R.id.passwordText)
        if (email.text.toString().isEmpty() || password.text.toString().isEmpty()) {
            Toast.makeText(this, "Lütfen e-posta ve şifre alanlarını doldurun.", Toast.LENGTH_LONG).show()
            return
        }

        auth.createUserWithEmailAndPassword(email.text.toString(),password.text.toString()).addOnCompleteListener { task->
            if(task.isSuccessful){
                //diğer aktiviteye git

                    val intent = Intent(this, HaberlerActivity::class.java)
                startActivity(intent)
                finish()
            }



        }.addOnFailureListener { exception ->
            Toast.makeText(applicationContext,exception.localizedMessage,Toast.LENGTH_LONG).show()
        }

    }

}