package com.example.tramada20.proveedores.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tramada20.proveedores.api.ApiClient
import com.example.tramada20.proveedores.models.Venta
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.tramada20.R

class VentaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_venta)

        val etFechaVenta = findViewById<EditText>(R.id.etFechaVenta)
        val etEstadoPago = findViewById<EditText>(R.id.etEstadoPago)
        val etTipoVenta = findViewById<EditText>(R.id.etTipoVenta)
        val etTotalVenta = findViewById<EditText>(R.id.etTotalVenta)
        val etComentarios = findViewById<EditText>(R.id.etComentarios)
        val etClientesId = findViewById<EditText>(R.id.etClienteId)
        val btnGuardarVenta = findViewById<Button>(R.id.btnGuardarVenta)
        val btnDetalleVenta = findViewById<Button>(R.id.btnAbrirDetalleVenta)

        btnGuardarVenta.setOnClickListener {
            val fechaVenta = etFechaVenta.text.toString().trim()
            val estadoPago = etEstadoPago.text.toString().trim()
            val tipoVenta = etTipoVenta.text.toString().trim()
            val totalVenta = etTotalVenta.text.toString().trim().toIntOrNull()
            val comentarios = etComentarios.text.toString().trim()
            val clientesId = etClientesId.text.toString().trim().toIntOrNull()

            if (estadoPago.isEmpty() || tipoVenta.isEmpty() || totalVenta == null || clientesId == null) {
                Toast.makeText(this, "Todos los campos obligatorios deben completarse", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val venta = Venta(fecha_venta = fechaVenta,estado_pago = estadoPago, tipo_venta = tipoVenta, total_venta = totalVenta, comentarios = comentarios, clientes_id = clientesId)
            ApiClient.apiService.crearVenta(venta).enqueue(object : Callback<Venta> {
                override fun onResponse(call: Call<Venta>, response: Response<Venta>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@VentaActivity, "Venta registrada", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@VentaActivity, "Error al registrar venta", Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<Venta>, t: Throwable) {
                    Toast.makeText(this@VentaActivity, "Fallo en la conexión", Toast.LENGTH_SHORT).show()
                }
            })
        }

        btnDetalleVenta.setOnClickListener {
            val intent = Intent(this, DetalleVentaActivity::class.java)
            startActivity(intent)
        }
    }
}
