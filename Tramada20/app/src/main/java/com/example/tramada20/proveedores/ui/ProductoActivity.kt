package com.example.tramada20.proveedores.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tramada20.proveedores.api.ApiClient
import com.example.tramada20.proveedores.models.Producto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.tramada20.R

class ProductoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productos)

        val etNombre = findViewById<EditText>(R.id.etNombreProducto)
        val etGenero = findViewById<EditText>(R.id.etGeneroProducto)
        val etTipo = findViewById<EditText>(R.id.etTipoProducto)
        val etTalla = findViewById<EditText>(R.id.etTallaProducto)
        val etColor = findViewById<EditText>(R.id.etColorProducto)
        val etPeso = findViewById<EditText>(R.id.etPesoProducto)
        val etCantidad = findViewById<EditText>(R.id.etCantidadProducto)
        val etPrecio = findViewById<EditText>(R.id.etPrecioProducto)
        val etCategoria = findViewById<EditText>(R.id.etCategoriaProducto)
        val btnGuardar = findViewById<Button>(R.id.btnGuardarProducto)

        btnGuardar.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            val genero = etGenero.text.toString().trim()
            val tipo = etTipo.text.toString().trim()
            val talla = etTalla.text.toString().trim()
            val color = etColor.text.toString().trim()
            val peso = etPeso.text.toString().trim()
            val cantidad = etCantidad.text.toString().trim()
            val precio = etPrecio.text.toString().trim()
            val categoriaId = etCategoria.text.toString().trim()

            if (nombre.isEmpty() || genero.isEmpty() || tipo.isEmpty() || talla.isEmpty() ||
                color.isEmpty() || peso.isEmpty() || cantidad.isEmpty() || precio.isEmpty() || categoriaId.isEmpty()) {
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val producto = Producto(
                nombre_producto = nombre,
                genero_producto = genero,
                tipo_producto = tipo,
                talla_producto = talla,
                color_producto = color,
                peso_producto = peso.toInt(),
                cantidad_producto = cantidad.toInt(),
                precio_producto = precio.toInt(),
                categoria_productos_id = categoriaId.toInt()
            )

            ApiClient.apiService.crearProducto(producto).enqueue(object : Callback<Producto> {
                override fun onResponse(call: Call<Producto>, response: Response<Producto>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@ProductoActivity, "Producto guardado", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@ProductoActivity, "Error al guardar", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Producto>, t: Throwable) {
                    Toast.makeText(this@ProductoActivity, "Fallo en la conexión", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}
