import data.UserRequest
import data.UserRequestCorreo
import domain.Repository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.json.Json

private const val BASE_URL =
   "http://sslcbpopen.eastus2.cloudapp.azure.com:8080/saa-rest/webresources/intranetSAA"
//"http://74.249.92.43:8080/saa-rest/webresources/intranetSAA/"

class RepoImpl(private val httpClient: HttpClient) : Repository {

    private val json = Json {
        ignoreUnknownKeys = true
    }

    override suspend fun getDataUsuario(userRequest: UserRequest): List<ResponseData> {
        //val jsonBody = Json.encodeToString(userRequest)
        val response = httpClient.post("$BASE_URL/logueoColaborador") {
            contentType(ContentType.Application.Json)
            setBody(
                userRequest
            )
        }

        return try {
            val responseBody = response.body<String>()
            val networkResponse = json.decodeFromString<ResponseData>(responseBody)
            return listOf(networkResponse)

        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    override suspend fun getDataUsuarioCorreo(userRequestCorreo: UserRequestCorreo): List<ResponseData> {
        val response = httpClient.post("$BASE_URL/logueoCorreoColaborador") {
            contentType(ContentType.Application.Json)
            setBody(
                userRequestCorreo
            )
        }

        return try {
            val responseBody = response.body<String>()
            val networkResponse = json.decodeFromString<ResponseData>(responseBody)
            return listOf(networkResponse)

        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}



