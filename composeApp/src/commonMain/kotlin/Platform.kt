import io.ktor.client.HttpClient

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform



