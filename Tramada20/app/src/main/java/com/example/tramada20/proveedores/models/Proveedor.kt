package com.example.tramada20.proveedores.models

data class Proveedor(
    val id: Int? = null,
    val nombre: String,
    val telefono: String,
    val direccion: String,
    val correo: String
)
