package com.blogify

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class DetailActivity : AppCompatActivity() {
    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onContextItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = intent.getParcelableExtra<Hero>("key_hero")?.title?: "Detail"
        }

        val tvDetailTitle: TextView = findViewById(R.id.tv_detail_title)
        val tvDetailAuthor: TextView = findViewById(R.id.tv_detail_author)
        val tvDetailCategory: TextView = findViewById(R.id.tv_detail_category)
        val tvDetailDescription: TextView = findViewById(R.id.tv_detail_description)
        val ivDetailPhoto: ImageView = findViewById(R.id.iv_detail_photo)
        val tvDetailDate: TextView = findViewById(R.id.tv_detail_date)
        val tvDetailReadingTime: TextView = findViewById(R.id.tv_detail_reading_time)

        val hero = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra("key_hero", Hero::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("key_hero")
        }

        hero?.let {
            tvDetailTitle.text = it.title
            tvDetailAuthor.text = it.author
            tvDetailCategory.text = it.category
            tvDetailDescription.text = it.description
            ivDetailPhoto.setImageResource(it.photo)
            tvDetailDate.text = it.date
            tvDetailReadingTime.text = it.readingTime
        }

        val shareButton = findViewById<ImageButton>(R.id.action_share)
        shareButton.setOnClickListener {
            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "Share this article")
                type = "text/plain"
            }
            startActivity(Intent.createChooser(shareIntent, "Share via"))
        }
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
