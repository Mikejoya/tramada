package com.example.tramada20.proveedores.api.ApiService

import com.example.tramada20.proveedores.models.Proveedor
import com.example.tramada20.proveedores.models.Producto
import com.example.tramada20.proveedores.models.Venta
import com.example.tramada20.proveedores.models.DetalleVenta
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("proveedores")
    fun crearProveedor(@Body proveedor: Proveedor): Call<Proveedor>

    @POST("productos")
    fun crearProducto(@Body producto: Producto): Call<Producto>

    @POST("venta") // Ruta de la API para guardar una venta
    fun crearVenta(@Body venta: Venta): Call<Venta>

    @POST("detalle_venta") // Ruta de la API para guardar un detalle de venta
    fun crearDetalleVenta(@Body detalleVenta: DetalleVenta): Call<DetalleVenta>
}
