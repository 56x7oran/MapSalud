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
import ec.edu.mapsalud.enum.Type

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
        counter = 1
        type = Type.PATIENT
    }

    private fun initListeners() {
        binding.btnSignin.setOnClickListener {

            var msg = ""
            if (binding.txtEmail.text.toString() == "admin"
                && binding.txtPassword.text.toString() == "admin"
            ) {
                var intent = Intent(this, Main_Screen::class.java)

                //Actualizacion 6 de mayo
                intent.putExtra("xx1", "Bienvenido de nuevo")
                startActivity(intent)
            } else
                Toast.makeText(
                    this,
                    "Nombre de usuario/Contraseniac incorrecto",
                    Toast.LENGTH_SHORT
                ).show()
        }


        binding.btnDoctor.setOnClickListener {
            type = Type.DOCTOR
            updateSelection()
        }

        binding.btnPatient.setOnClickListener {
            type = Type.PATIENT
            updateSelection()
        }

        binding.txtCreateAccount.setOnClickListener{
            val intent = Intent(this, SignUpPage::class.java)
            startActivity(intent)
            finish()
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
}