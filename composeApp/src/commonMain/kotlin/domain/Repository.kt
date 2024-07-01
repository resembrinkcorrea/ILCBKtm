package domain


import ResponseData
import data.UserRequest
import io.ktor.http.cio.Response

interface Repository {
    suspend fun getDataUsuario(userRequest: UserRequest):List<ResponseData>
}