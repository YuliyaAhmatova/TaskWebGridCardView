package com.example.taskwebgridcardview

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var toolbarMain:Toolbar
    private lateinit var gridViewGV:GridView
    private var list = mutableListOf(
        GridViewModal("Gismeteo", R.drawable.gis, "https://www.gismeteo.ru/"),
        GridViewModal("Yandex", R.drawable.ya, "https://yandex.ru/"),
        GridViewModal("Mail", R.drawable.mail, "https://mail.ru/"),
        GridViewModal("Wikipedia", R.drawable.wik, "https://www.wikipedia.org/")
    )

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        toolbarMain = findViewById(R.id.toolbarMain)
        gridViewGV = findViewById(R.id.gridViewGV)

        setSupportActionBar(toolbarMain)
        title = "Мобильный браузер"
        toolbarMain.setLogo(R.drawable.ic_browser)

        val adapter = GridViewAdapter(list = list, this@MainActivity)
        gridViewGV.adapter = adapter

        gridViewGV.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("${list[position].link}")))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId){
            R.id.exitMenuMain -> {
                finishAffinity()
                Toast.makeText(
                    applicationContext,
                    "Программа завершена",
                    Toast.LENGTH_LONG
                    ).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}