package com.example.mysextaapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.types.RealmObject
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var realm: Realm
    private val scope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Configuración de Realm
        val config = RealmConfiguration.Builder(schema = setOf(Infraccion::class))
            .name("infracciones.realm")
            .schemaVersion(1)
            .build()
        realm = Realm.open(config)

        val etRutInspector = findViewById<EditText>(R.id.etRutInspector)
        val etNombreLocal = findViewById<EditText>(R.id.etNombreLocal)
        val etDireccion = findViewById<EditText>(R.id.etDireccion)
        val etInfraccion = findViewById<EditText>(R.id.etInfraccion)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)

        btnGuardar.setOnClickListener {
            val rut = etRutInspector.text.toString().trim()
            val nombre = etNombreLocal.text.toString().trim()
            val direccion = etDireccion.text.toString().trim()
            val infraccion = etInfraccion.text.toString().trim()

            if (rut.isEmpty() || nombre.isEmpty() || direccion.isEmpty() || infraccion.isEmpty()) {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                guardarInfraccion(rut, nombre, direccion, infraccion)
            }
        }
    }

    private fun guardarInfraccion(rut: String, nombre: String, direccion: String, infraccion: String) {
        scope.launch {
            realm.write {
                copyToRealm(Infraccion().apply {
                    this.rutInspector = rut
                    this.nombreLocal = nombre
                    this.direccion = direccion
                    this.infraccion = infraccion
                })
            }
            runOnUiThread {
                Toast.makeText(this@MainActivity, "Infracción guardada con éxito", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}
