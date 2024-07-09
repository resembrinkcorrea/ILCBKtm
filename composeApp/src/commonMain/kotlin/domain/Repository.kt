package domain


import ResponseData
import data.QrEntity
import data.UserRequest
import data.UserRequestCorreo
import data.UsuarioCorreoRequest
import io.ktor.http.cio.Response
import model.ResponseQr

interface Repository {
    suspend fun getDataUsuario(userRequest: UserRequest):List<ResponseData>
    suspend fun getDataUsuarioCorreo(userRequest: UsuarioCorreoRequest):List<ResponseData>

}