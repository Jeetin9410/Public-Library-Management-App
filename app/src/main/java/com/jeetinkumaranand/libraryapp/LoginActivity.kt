package com.jeetinkumaranand.libraryapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeetinkumaranand.libraryapp.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : ComponentActivity() {

    //lateinit var loginBinding: ActivityLoginBinding
    //create an object from the FireBase off class in the global area
    val auth:FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginActivityUi()
           // Text(text = "Jeetin")
        }
        /*loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        val view = loginBinding.root
        setContentView(view)

        loginBinding.buttonSignin.setOnClickListener {


            val userEmail = loginBinding.editTextEmailSignin.text.toString()
            val userPassword = loginBinding.editTextPasswordSignin.text.toString()


            if (userEmail.isBlank() || userPassword.isBlank()){
                Toast.makeText(applicationContext,"Enter the details ",Toast.LENGTH_SHORT).show()
            }else {
                //call
                singinWithFirebase(userEmail, userPassword)
            }

        }
        loginBinding.buttonSignup.setOnClickListener {

            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intent)
        }

        loginBinding.buttonForgot.setOnClickListener {
            val intent = Intent(this, ForgetPasswordActivity::class.java)
            startActivity(intent)
        }
        loginBinding.buttonSigninWithPhoneNumber.setOnClickListener {
            val intent= Intent(this,PhoneActivity::class.java)
            startActivity(intent)
            finish()
        }*/
    }

    @Preview(showBackground = true)
    @Composable
    fun LoginActivityUi(){
        Surface(modifier = Modifier
            .height(IntrinsicSize.Max)
            .width(IntrinsicSize.Max)
            .padding(20.dp),
            color = Color(0xFFA1E2EB),
            shape = RoundedCornerShape(20.dp)
        ) {
            Column {
                Text(text = "Jeetin")
            }
        }
    }
    fun singinWithFirebase(userEmail:String, userPassword: String) {

        auth.signInWithEmailAndPassword(userEmail, userPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(applicationContext,"Login is successful",Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()

                } else {
                    Toast.makeText(applicationContext,task?.exception.toString(),Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun onStart() {
        super.onStart()
        val user = auth.currentUser
        if (user!= null){
            val intent = Intent(this@LoginActivity,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


}