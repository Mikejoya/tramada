package com.example.tramada20.proveedores.api.ApiService

import com.example.tramada20.proveedores.models.Proveedor
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("proveedores")
    fun crearProveedor(@Body proveedor: Proveedor): Call<Proveedor>
}
