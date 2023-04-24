package com.example.licenta2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.AuthResult

class FirebaseLogIn : AppCompatActivity() {

    private lateinit var login_email: EditText
    private lateinit var login_parola: EditText
    private lateinit var buttonLogin: Button

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_firebase_log_in)

            FirebaseApp.initializeApp(this);


        login_email = findViewById(R.id.login_email)
        login_parola = findViewById(R.id.login_parola)
        buttonLogin = findViewById(R.id.buttonLogIn)

        firebaseAuth = FirebaseAuth.getInstance()

        buttonLogin.setOnClickListener {
                val email = login_email.getText().toString().trim { it <= ' ' }
                val parola = login_parola.getText().toString().trim { it <= ' ' }

                // Autentificare utilizand adresa de email si parola
                firebaseAuth.signInWithEmailAndPassword(email, parola)
                    .addOnCompleteListener(OnCompleteListener<AuthResult?> { task ->
                        if (task.isSuccessful) {
                            // Autentificare reusita
                            Toast.makeText(
                                this@FirebaseLogIn,
                                "Autentificare reusita!",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            // Autentificare esuata
                            Toast.makeText(
                                this@FirebaseLogIn,
                                "Autentificare esuata! Verifica adresa de email si parola.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })
            }


        val btnCreazaCont = findViewById<TextView>(R.id.textViewCreeazaCont)
        btnCreazaCont.setOnClickListener {
            val intent = Intent(this, FirebaseAuth::class.java)
            startActivity(intent)
        }
        }
  }

