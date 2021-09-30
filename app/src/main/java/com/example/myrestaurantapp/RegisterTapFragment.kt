package com.example.qarapp

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.myrestaurantapp.HomeActivity
import com.example.myrestaurantapp.R
import com.example.myrestaurantapp.passwordReminderActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegisterTapFragment : Fragment() {
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var progressDialog: ProgressDialog
    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var confirmPassword: EditText
    lateinit var register: Button
    lateinit var forgetPassword: TextView
    lateinit var name: EditText
    lateinit var phone: EditText
    lateinit var address: EditText

    var isNameValid = false
    var isEmailValid: Boolean = false
    var isPasswordValid: Boolean = false
    var isConfirmPasswordValid: Boolean = false
    var isPhoneValid: Boolean = false
    var isAddressValid: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.register_tap_fragment, container, false)
        name = v.findViewById(R.id.UserName)
        email = v.findViewById(R.id.email)
        password = v.findViewById(R.id.password)
        register = v.findViewById(R.id.Sign_Up)
        forgetPassword = v.findViewById(R.id.PasswordReminder)
        confirmPassword = v.findViewById(R.id.passwordConfirm)
        address = v.findViewById(R.id.address)
        phone = v.findViewById(R.id.phone)

        // progressDialog
        progressDialog = ProgressDialog(v.context)
        progressDialog.setTitle(R.string.waitDialog)
        progressDialog.setMessage(getString(R.string.creatAccountDialog))
        progressDialog.setCanceledOnTouchOutside(false)

        // init FirebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()

        register.setOnClickListener {

            SetValidation(v)
        }

        forgetPassword.setOnClickListener {
            startActivity(Intent(v.context, passwordReminderActivity::class.java))
        }
        return v
    }

    fun SetValidation(view: View) {
        val addressText = address.text.toString().trim()
        val phoneText = phone.text.toString().trim()
        val emailText = email.text.toString().trim()
        val passwordText = password.text.toString().trim()
        val confirmPasswordText = confirmPassword.text.toString().trim()
        val nameText = name.text.toString().trim()

        // Check for a valid name.
        if (nameText.isEmpty()) {
            name.setError(getResources().getString(R.string.name_error))
            isNameValid = false
        } else {
            isNameValid = true
        }
        // Check for a valid email address.
        if (emailText.isEmpty()) {
            email.setError(getResources().getString(R.string.email_error))
            isEmailValid = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            email.setError(getResources().getString(R.string.error_invalid_email))
            isEmailValid = false
        } else {
            isEmailValid = true
        }

        // Check for a valid password.
        if (passwordText.isEmpty()) {
            password.setError(getResources().getString(R.string.password_error))
            isPasswordValid = false
        } else if (passwordText.length < 6) {
            password.setError(getResources().getString(R.string.error_invalid_password))
            isPasswordValid = false
        } else {
            isPasswordValid = true
        }
        // Check for a Confirm password.
        if (confirmPasswordText.isEmpty()) {

            confirmPassword.setError(getResources().getString(R.string.ConfirmPassword_error))
            isConfirmPasswordValid = false
        } else if (confirmPasswordText != passwordText) {
            confirmPassword.setError(getResources().getString(R.string.NotMatchPass_error))
            isConfirmPasswordValid = false
        } else {
            isConfirmPasswordValid = true
        }

        // Check for a Phone.
        if (phoneText.isEmpty()) {
            phone.setError(getResources().getString(R.string.phone_error))
            isPhoneValid = false
        } else if (!Patterns.PHONE.matcher(phoneText).matches()) {
            phone.setError(getResources().getString(R.string.phone_error))
            isPhoneValid = false
        } else {
            isPhoneValid = true
        }

        // Check for a address.
        if (addressText.isEmpty()) {
            address.setError(getResources().getString(R.string.address_error))
            isAddressValid = false
        } else {
            isAddressValid = true
        }

        if (isNameValid && isEmailValid && isPasswordValid && isConfirmPasswordValid &&
            isAddressValid && isPhoneValid
        ) {
            addDataInFirebase()
            firebaseSignUp(view)
        }
    }

    private fun addDataInFirebase() {
        val db = Firebase.firestore
        val addressText = address.text.toString()
        val phoneText = phone.text.toString()
        val nameText = name.text.toString()
        val passwordText = password.text.toString()
        val emailText = email.text.toString()

        val user = hashMapOf(
            "Name" to nameText,
            "Phone" to phoneText,
            "Address" to addressText,
            "Email" to emailText,
            "Password" to passwordText,
        )

        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d("TAG", "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w("TAG", "Error adding document", e)
            }
    }

    fun firebaseSignUp(view: View) {
        val emailtext = email.text.toString().trim()
        val passwordtext = password.text.toString().trim()
        progressDialog.show()

        // create account
        firebaseAuth.createUserWithEmailAndPassword(emailtext, passwordtext)
            .addOnSuccessListener {
                // signUP success
                progressDialog.dismiss()
                // get user info
                val firebaseUser = firebaseAuth.currentUser
                val email = firebaseUser!!.email
                Toast.makeText(view.context, "Account created by $email", Toast.LENGTH_SHORT)
                    .show()

                startActivity(Intent(view.context, HomeActivity::class.java))
                activity?.finish()
            }
            .addOnFailureListener {
                // signUP Failed
                progressDialog.dismiss()
                Toast.makeText(
                    view.context,
                    "signUP Failed due to ${it.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }
}
