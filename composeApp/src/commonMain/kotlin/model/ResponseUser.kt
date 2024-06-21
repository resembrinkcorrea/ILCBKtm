package model
import kotlinx.serialization.Serializable

@Serializable
data class ResponseUser(
    val dataCarrera: String,
    val dataMenu: String,
    val dataPerfil: String,
    val flag: Int,
    val mensaje: String
)