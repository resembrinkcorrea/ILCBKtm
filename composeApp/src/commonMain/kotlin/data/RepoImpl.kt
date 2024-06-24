import data.UserRequest
import domain.Repository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.call.receive
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement

private const val BASE_URL =
    "http://sslcbpopen.eastus2.cloudapp.azure.com:8080/saa-rest/webresources/intranetSAA"

class RepoImpl(private val httpClient: HttpClient) : Repository {

    private val json = Json {
        ignoreUnknownKeys = true
    }

    override suspend fun getDataUsuario(userRequest: UserRequest): List<ResponseData> {
        //val jsonBody = Json.encodeToString(userRequest)
        val response = httpClient.post("$BASE_URL/logueoColaborador") {
            contentType(ContentType.Application.Json)
            setBody(userRequest
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



    @Serializable
    data class ResponseData(
        val data_menu: ArrayList<data_menu> = arrayListOf(),
        val flag_val: Int,
        val data_colaborador: ArrayList<data_colaborador> = arrayListOf()
    )

    @Serializable
    data class data_menu(
        val icono: String,
        val textoMenuAbrev: String,
        val textoMenu: String,
        val idMenu: Int,
        val imagen: String,
        val orden: Int,
        val nivel: Int,
        val idMenuPadre: Int
    )

    @Serializable
    data class data_colaborador(
        val tiempo_inactividad: String,
        val empl_url_foto: String,
        val id_docente: String,
        val genero_abrev: String,
        val id_atribp: String,
        val id_perfil: String,
        val id_pers_det: String,
        val uneg_nombre: String,
        val pers_apellido_mat: String,
        val pers_nombre: String,
        val pers_apellido_pat: String,
        val flag_inactividad: String,
        val id_sistema: String,
        val perf_nombre: String,
        val id_usuario: String,
        val id_tipo_usuario: String,
        val id_uneg: String
    )
}
