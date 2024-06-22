package di
import data.RepoImpl
import domain.Repository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import org.koin.dsl.module
import presentacion.UserViewModel

fun appModule()  = module {

    single<HttpClient> {HttpClient { install(ContentNegotiation) { json()} }}
   // single{ ExpenseManager }.withOptions { createdAtStart() }
    single<Repository> { RepoImpl(get())}
    factory { UserViewModel(get())}

}