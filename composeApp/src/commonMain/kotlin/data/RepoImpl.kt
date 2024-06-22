package data

import domain.Repository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

import io.ktor.http.parseAndSortContentTypeHeader
import model.ResponseUser

//private const val BASE_URL = "http://74.249.92.43:8080/saa-rest/webresources/intranetSAA/"
private const val BASE_URL = "http://sslcbpopen.eastus2.cloudapp.azure.com:8080/saa-rest/webresources/intranetSAA/"


class RepoImpl(private val httpClient: HttpClient) : Repository {
    override suspend fun getDataUsuario(userRequest: UserRequest): List<ResponseUser> {
        val response = httpClient.post("$BASE_URL/logueoEstudiante") {
            contentType(ContentType.Application.Json)
            setBody(UserRequest
            )
        }

        val networkResponse: List<ResponseUser> = response.body()
        if(networkResponse.isEmpty()) return emptyList()
        return  networkResponse.map { networkExpense ->
            ResponseUser (
                dataMenu = networkExpense.dataMenu,
                dataCarrera = networkExpense.dataCarrera,
                dataPerfil = networkExpense.dataPerfil,
                flag = networkExpense.flag,
                mensaje = networkExpense.mensaje
            )
        }
    }
}