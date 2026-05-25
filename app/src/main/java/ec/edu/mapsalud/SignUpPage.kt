package ec.edu.mapsalud

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.chaos.view.PinView
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import ec.edu.mapsalud.databinding.ActivityLoginPageBinding
import ec.edu.mapsalud.databinding.ActivitySignUpPageBinding
import ec.edu.mapsalud.enum.Type

class SignUpPage : AppCompatActivity() {

    lateinit var binding: ActivitySignUpPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initVariables()
        initListeners()
    }

    var counter: Int = 0


    private fun initVariables() {
        counter = 1
    }

    private fun initListeners() {
        binding.btnSignUp.setOnClickListener {
            showVerificationDialog()
        }

        binding.txtAlradyHaveAccount.setOnClickListener {
            val intent = Intent(this, LoginPage::class.java)
            startActivity(intent)
            finish()
        }
    }


    private fun showVerificationDialog() {

        val view = layoutInflater.inflate(
            R.layout.activity_verification_code_dialog,
            null
        )

        val dialog = MaterialAlertDialogBuilder(this)
            .setView(view)
            .create()

        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        val pinView = view.findViewById<PinView>(R.id.pinView)

        val btnCancel = view.findViewById<MaterialButton>(R.id.btnCancel)

        val btnVerify = view.findViewById<MaterialButton>(R.id.btnVerify)

        btnCancel.setOnClickListener {
            dialog.dismiss()
        }

        btnVerify.setOnClickListener {

            if (pinView.text.toString().length == 6) {

                dialog.dismiss()

                val code = pinView.text.toString()

                Toast.makeText(this, code, Toast.LENGTH_SHORT).show()
            }
        }

        dialog.show()
        pinView.requestFocus()

        val imm = getSystemService(
            INPUT_METHOD_SERVICE
        ) as InputMethodManager

        imm.showSoftInput(
            pinView,
            InputMethodManager.SHOW_IMPLICIT
        )
        pinView.requestFocus()
    }
}