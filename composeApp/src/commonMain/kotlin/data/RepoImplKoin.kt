package data

import BASE_URL
import domain.RepositoryKoin
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.json.Json
import model.ResponseQr

class RepoImplKoin(private val httpClient: HttpClient): RepositoryKoin {
    override suspend fun getQrUsuario(qrEntity: QrEntity): List<ResponseQr> {
        val response = httpClient.post("$BASE_URL/QrColaborador") {
            contentType(ContentType.Application.Json)
            setBody(
                qrEntity
            )
        }

        return try {
            val responseBody = response.body<String>()
            val networkResponse = Json.decodeFromString<ResponseQr>(responseBody)
            return listOf(networkResponse)

        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}