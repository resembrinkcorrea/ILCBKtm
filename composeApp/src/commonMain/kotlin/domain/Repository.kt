package domain


import ResponseData
import data.UserRequest
import data.UserRequestCorreo
import io.ktor.http.cio.Response

interface Repository {
    suspend fun getDataUsuario(userRequest: UserRequest):List<ResponseData>
    suspend fun getDataUsuarioCorreo(userRequest: UserRequestCorreo):List<ResponseData>
}