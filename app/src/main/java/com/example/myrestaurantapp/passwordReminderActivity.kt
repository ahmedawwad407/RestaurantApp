package com.example.myrestaurantapp

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class passwordReminderActivity : AppCompat() {
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var email: EditText
    var isEmailValid: Boolean = false
    lateinit var resetPassword: Button
    lateinit var signIn: Button
    lateinit var textDontHaveAccount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_reminder)

        email = findViewById(R.id.email)
        resetPassword = findViewById(R.id.ResetPassword)
        signIn = findViewById(R.id.Sign_In)
        textDontHaveAccount = findViewById(R.id.TextDontHaveAccount)

        resetPassword.setOnClickListener {
            SetValidation()
        }

        signIn.setOnClickListener {
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }

        textDontHaveAccount.setOnClickListener {
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }
    }
    fun SetValidation() {
        // Check for a valid email address.
        val emailText = email.text.toString()
        if (emailText.isEmpty()) {
            email.setError(getResources().getString(R.string.email_error))
            isEmailValid = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            email.setError(getResources().getString(R.string.error_invalid_email))
            isEmailValid = false
        } else {
            isEmailValid = true
        }

        if (isEmailValid) {
            firebaseAuth.sendPasswordResetEmail(emailText)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(getApplicationContext(), "please check your account email..", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(applicationContext, MainActivity::class.java))
                    } else {
                        val massage = it.exception!!.message
                        Toast.makeText(getApplicationContext(), "Error:$massage", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}
