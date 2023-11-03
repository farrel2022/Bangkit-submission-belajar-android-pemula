package com.dicoding.myrecyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvPlayer: RecyclerView
    private val list = ArrayList<Player>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvPlayer = findViewById(R.id.rv_player)
        rvPlayer.setHasFixedSize(true)

        list.addAll(getListPlayer())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_about -> {
                val intent = Intent(this, About::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListPlayer(): ArrayList<Player> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listPlayer = ArrayList<Player>()
        for (i in dataName.indices) {
            val player = Player(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listPlayer.add(player)
        }
        return listPlayer
    }

    private fun showRecyclerList() {
        rvPlayer.layoutManager = LinearLayoutManager(this)
        val listPlayerAdapter = ListPlayerAdapter(list)
        rvPlayer.adapter = listPlayerAdapter

        listPlayerAdapter.setOnItemClickCallback(object : ListPlayerAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Player) {
                showSelectedPlayer(data)
            }
        })
    }

    private fun showSelectedPlayer(player: Player) {
        Toast.makeText(this, "Kamu memilih " + player.name, Toast.LENGTH_SHORT).show()
        val intent = Intent(this, DetailPlayer::class.java)
        intent.putExtra(DetailPlayer.INFORMATION_PLAYER, player)
        startActivity(intent)
    }
}