package di

import RepoImpl
import data.RepoImplKoin
import domain.Repository
import domain.RepositoryKoin
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import org.koin.dsl.module
import presentacion.QrViewModel


//di Dependency Inyection
fun appModule()  = module {
    single<HttpClient> { HttpClient { install(ContentNegotiation) { json() } } }
    single<RepositoryKoin> { RepoImplKoin(get()) }
    factory { QrViewModel(get())}
}