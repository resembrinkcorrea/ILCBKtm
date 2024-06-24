package domain


import data.UserRequest
import io.ktor.http.cio.Response

interface Repository {
    suspend fun getDataUsuario(userRequest: UserRequest):List<RepoImpl.ResponseData>
}