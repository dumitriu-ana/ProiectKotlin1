package com.example.licenta2

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.FirebaseApp

class FirebaseInregistrare : AppCompatActivity() {

    private lateinit var inregistrare_mail: EditText
    private lateinit var inregistrare_parola1: EditText
    private lateinit var inregistrare_parola2: EditText
    private lateinit var btnInregistrare: Button

    private lateinit var firebaseAuth: FirebaseAuth

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firebase_auth)

        FirebaseApp.initializeApp(this)



//
//        FirebaseApp.initializeApp(this)
//
//        inregistrare_mail = findViewById(R.id.inregistrare_email)
//        inregistrare_parola1 = findViewById(R.id.inregistrare_parola1)
//        inregistrare_parola2 = findViewById(R.id.inregistrare_parola2)
//        btnInregistrare = findViewById(R.id.btn_inregistrare)
//
//        firebaseAuth = FirebaseAuth.getInstance()
//
//        btnInregistrare.setOnClickListener {
//            val email = inregistrare_mail.text.toString().trim()
//            val password1 = inregistrare_parola1.text.toString().trim()
//            val password2 = inregistrare_parola2.text.toString().trim()
//
//            if (password1 != password2) {
//                Toast.makeText(this, "Parolele nu corespund", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }
//
//            // Creeaza utilizatorul in baza de date Firebase
//            firebaseAuth.createUserWithEmailAndPassword(email, password1)
//                .addOnCompleteListener(this) { task ->
//                    if (task.isSuccessful) {
//                        // Inregistrare reusita
//                        Toast.makeText(this, "Contul a fost creat cu succes!", Toast.LENGTH_SHORT)
//                            .show()
//                    } else {
//                        // Inregistrare esuata
//                        Toast.makeText(
//                            this,
//                            "Inregistrarea a esuat: " + task.exception?.message,
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }
//                }
//        }

//        val btnConectareContExistent = findViewById<TextView>(R.id.inregistrare_login)
//        btnConectareContExistent.setOnClickListener {
//            val intent = Intent(this, FirebaseAuth::class.java)
//            startActivity(intent)
//        }
    }
    }
