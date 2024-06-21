package data

import kotlinx.serialization.Serializable


@Serializable
data class UserRequest(
     val usuario: String,
     val contrasena: String,
     val uneg: Int,
     val tipo_conexion: String,
     val ip_conexion: String,
     val sistema: String
)