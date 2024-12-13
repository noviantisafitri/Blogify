package com.blogify

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        supportActionBar?.apply {
            title = "About"
            setDisplayHomeAsUpEnabled(true)
        }

        val nameArray = resources.getStringArray(R.array.nama)
        val emailArray = resources.getStringArray(R.array.email)
        val photoArray = resources.obtainTypedArray(R.array.photo_profile)

        val aboutData = About(
            name = nameArray[0],
            email = emailArray[0],
            photo = photoArray.getResourceId(0, -1)
        )

        photoArray.recycle()

        val imgProfile: ImageView = findViewById(R.id.img_profile)
        val tvName: TextView = findViewById(R.id.tv_name)
        val tvEmail: TextView = findViewById(R.id.tv_email)

        tvName.text = aboutData.name
        tvEmail.text = aboutData.email
        imgProfile.setImageResource(aboutData.photo)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}