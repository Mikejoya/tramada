package com.example.tramada20.proveedores.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tramada20.proveedores.api.ApiClient
import com.example.tramada20.proveedores.models.Proveedor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.tramada20.R

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etTelefono = findViewById<EditText>(R.id.etTelefono)
        val etDireccion = findViewById<EditText>(R.id.etDireccion)
        val etCorreo = findViewById<EditText>(R.id.etCorreo)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)
        val btnVenta = findViewById<Button>(R.id.btnVenta)
        val btnProductos = findViewById<Button>(R.id.btnProducto)

        btnGuardar.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            val telefono = etTelefono.text.toString().trim()
            val direccion = etDireccion.text.toString().trim()
            val correo = etCorreo.text.toString().trim()

            if (nombre.isEmpty() || telefono.isEmpty() || direccion.isEmpty() || correo.isEmpty()) {
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val proveedor = Proveedor(nombre = nombre, telefono = telefono, direccion = direccion, correo = correo)

            ApiClient.apiService.crearProveedor(proveedor).enqueue(object : Callback<Proveedor> {
                override fun onResponse(call: Call<Proveedor>, response: Response<Proveedor>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@MainActivity, "Proveedor guardado", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@MainActivity, "Error al guardar", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Proveedor>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Fallo en la conexión", Toast.LENGTH_SHORT).show()
                }
            })
        }
        btnVenta.setOnClickListener{
            val intent = Intent(this, VentaActivity::class.java)
            startActivity(intent)
        }
        btnProductos.setOnClickListener{
            val intent = Intent(this, ProductoActivity::class.java)
            startActivity(intent)
        }
    }
}