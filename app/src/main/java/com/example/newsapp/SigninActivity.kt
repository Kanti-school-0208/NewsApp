package com.example.newsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import com.example.newsapp.hepler.Login
import com.example.newsapp.hepler.toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_signin.*

class SigninActivity : AppCompatActivity() {

    private lateinit var mauth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        mauth = FirebaseAuth.getInstance()


        signin.setOnClickListener {
            val email = schoolemail.text.toString().trim()
            val pass = schoolpass.text.toString().trim()

            if (email.isEmpty()) {
                schoolemail.error = "Enter Email id"
                schoolemail.requestFocus()
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                schoolemail.error = "Enter valid  Email id"
                schoolemail.requestFocus()
                return@setOnClickListener
            }
            if (pass.isEmpty() || pass.length < 6) {
                schoolpass.error = "Enter  al least 6 digit password"
                schoolpass.requestFocus()
                return@setOnClickListener
            }
            verifiedemail(email, pass)
        }

        clickhere.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
    private fun verifiedemail(email: String, pass: String) {

        mauth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this) { task ->

                if (task.isSuccessful) {
                    startActivity(Intent(this, ProfileActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    })
                    finish()
                } else {
                    task.exception?.message?.let {
                        toast(it)
                    }
                }
            }
    }

    override fun onStart() {
        super.onStart()
        mauth.currentUser?.let {
            Login()
        }
    }
}