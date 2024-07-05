package data

import kotlinx.serialization.Serializable




@Serializable
data class UserRequestCorreo(
    val usuario: String,
    val id_uneg: Int,
    val tipo_conexion: String,
    val ip_conexion: String
)