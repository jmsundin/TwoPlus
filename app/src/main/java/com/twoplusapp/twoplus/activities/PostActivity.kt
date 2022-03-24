package com.twoplusapp.twoplus.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.sundin.twoplus.R

class PostActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        setSupportActionBar(findViewById(R.id.toolbarPostActivity))

        val bundle: Bundle? = intent.extras

        findViewById<ImageView>(R.id.ivPostImage).setImageResource(bundle?.get("postImage") as Int)
        findViewById<TextView>(R.id.tvPostTitle).text = bundle.get("postTitle") as String
        findViewById<TextView>(R.id.tvPostDescription).text = bundle.get("postDescription") as String
        findViewById<TextView>(R.id.tvPostTime).text = bundle.get("postTime") as String
        findViewById<TextView>(R.id.tvPostDate).text = bundle.get("postDate") as String
        findViewById<TextView>(R.id.tvPostLocation).text = bundle.get("postLocation") as String

        val btnBackPostActivity: ImageView = findViewById(R.id.btnBackPostActivity)
        btnBackPostActivity.setOnClickListener {
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }


    }

}