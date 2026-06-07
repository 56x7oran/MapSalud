package ec.edu.mapsalud

import android.content.Intent
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AlertDialogLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import ec.edu.mapsalud.databinding.ActivityLoginPageBinding
import ec.edu.mapsalud.datos.FirebaseManager
import ec.edu.mapsalud.enum.Type

import ec.edu.mapsalud.utils.EmailJSRequest
import ec.edu.mapsalud.utils.RetrofitClient
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class LoginPage : AppCompatActivity() {

    lateinit var binding: ActivityLoginPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initVariables()
        initListeners()
    }

    var counter: Int = 0

    var type: Type? = null

    private fun initVariables() {
        type = Type.PATIENT
    }

    private fun initListeners() {
        binding.btnSignin.setOnClickListener {
            loginUser()
        }


        binding.btnDoctor.setOnClickListener {
            type = Type.DOCTOR
            updateSelection()
        }

        binding.btnPatient.setOnClickListener {
            type = Type.PATIENT
            updateSelection()
        }

        binding.txtCreateAccount.setOnClickListener {
            val intent = Intent(this, SignUpPage::class.java)
            startActivity(intent)
            finish()
        }

        binding.forgotPassword.setOnClickListener {

        }
    }

    private fun loginUser() {

        val email = binding.txtEmail.text.toString().trim()
        val password = binding.txtPassword.text.toString()

        if (email.isEmpty()) {
            showMessage("Ingrese un correo")
            return
        }

        if (password.isEmpty()) {
            showMessage("Ingrese una contraseña")
            return
        }

        binding.btnSignin.isEnabled = false

        FirebaseManager.auth.signInWithEmailAndPassword(
            email,
            password
        )
            .addOnSuccessListener {

                val user = FirebaseManager.auth.currentUser

                user?.reload()?.addOnSuccessListener {

                    if (user.isEmailVerified) {

                        showMessage("Bienvenido")

                        val intent = Intent(
                            this,
                            Main_Screen::class.java
                        )

                        intent.putExtra(
                            "xx1",
                            "Bienvenido de nuevo"
                        )

                        startActivity(intent)
                        finish()

                    } else {

                        FirebaseManager.auth.signOut()

                        showMessage(
                            "Debe verificar su correo electrónico"
                        )
                    }

                    binding.btnSignin.isEnabled = true
                }
            }
            .addOnFailureListener {

                binding.btnSignin.isEnabled = true
                counter++
                if (counter >= 3) {
                    sendSecurityAlert(email)
                }
                showMessage(
                    "Correo o contraseña incorrectos"
                )
            }
    }

    private fun updateSelection() {

        if (type == Type.PATIENT) {

            binding.btnPatient.setBackgroundTintList(
                getColorStateList(R.color.primary)
            )

            binding.btnDoctor.setBackgroundTintList(
                getColorStateList(R.color.surface_container_high)
            )

            binding.btnPatient.setTextColor(getColor(R.color.yellow_cremy))
            binding.btnDoctor.setTextColor(getColor(R.color.information_text_color))

        } else {

            binding.btnDoctor.setBackgroundTintList(
                getColorStateList(R.color.primary)
            )

            binding.btnPatient.setBackgroundTintList(
                getColorStateList(R.color.surface_container_high)
            )
            binding.btnDoctor.setTextColor(getColor(R.color.yellow_cremy))
            binding.btnPatient.setTextColor(getColor(R.color.information_text_color))
        }
    }


    private fun showMessage(message: String) {

        val snackbar = Snackbar.make(
            binding.root,
            message,
            Snackbar.LENGTH_LONG
        )

        snackbar.setBackgroundTint(
            getColor(R.color.black_soft)
        )

        snackbar.setTextColor(
            getColor(R.color.white)
        )

        snackbar.show()
    }

    private fun sendSecurityAlert(email: String) {

        val request = EmailJSRequest(
            service_id = "--",
            template_id = "-r",
            user_id = "--",
            accessToken = "--",
            template_params = mapOf(
                "user_name" to "Usuario",
                "email" to email,
                "date" to SimpleDateFormat(
                    "dd/MM/yyyy",
                    Locale.getDefault()
                ).format(Date()),
                "time" to SimpleDateFormat(
                    "HH:mm",
                    Locale.getDefault()
                ).format(Date())

            )
        )

        RetrofitClient.emailService
            .sendEmail(request)
            .enqueue(
                object : retrofit2.Callback<Void> {

                    override fun onResponse(
                        call: retrofit2.Call<Void>,
                        response: retrofit2.Response<Void>
                    ) {
                        if (response.isSuccessful) {
                            showMessage("Email enviado correctamente")
                        } else {
                            showMessage("Error al enviar email: ${response.code()}")
                        }
                    }

                    override fun onFailure(
                        call: retrofit2.Call<Void>,
                        t: Throwable
                    ) {
                        showMessage("Novali")
                    }
                }
            )
    }
}