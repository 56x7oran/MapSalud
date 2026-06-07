package ec.edu.mapsalud.utils

import android.app.Dialog
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.postDelayed
import ec.edu.mapsalud.R

object MessageManager {

    fun showSuccessBanner(
        activity: AppCompatActivity,
        message: String
    ) {

        val view = activity.layoutInflater.inflate(
            R.layout.view_banner,
            null
        )

        view.findViewById<TextView>(
            R.id.bannerMessage
        ).text = message

        val dialog = Dialog(activity)

        dialog.setContentView(view)

        dialog.show()

        Handler(
            Looper.getMainLooper()
        ).postDelayed({

            dialog.dismiss()

        }, 3000)
    }
}