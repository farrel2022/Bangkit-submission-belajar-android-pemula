package com.dicoding.myrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.dicoding.myrecyclerview.databinding.DetailPlayerBinding

class DetailPlayer : AppCompatActivity() {

    companion object {
        const val INFORMATION_PLAYER = "information_player"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_player)

        val player = intent.getParcelableExtra<Player>(INFORMATION_PLAYER)
        player?.let {
            val imgItemPhoto = findViewById<ImageView>(R.id.img_item_photo)
            val tvItemName = findViewById<TextView>(R.id.tv_item_name)
            val tvItemDescription = findViewById<TextView>(R.id.tv_item_description)

            imgItemPhoto.setImageResource(it.photo)
            tvItemName.text=it.name
            tvItemDescription.text=it.description
        }
    }
}