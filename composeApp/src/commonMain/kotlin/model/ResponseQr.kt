package model
import kotlinx.serialization.Serializable


@Serializable
data class ResponseQr(
    val flag_val: Int,
    val data_qr: List<DataQr>
)

@Serializable
data class DataQr(
    val codigo_qr: String
)