package `in`.janitri.httpwithktorclient.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import `in`.janitri.httpwithktorclient.data.remote.PostServiceImp
import `in`.janitri.httpwithktorclient.data.remote.PostsService
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideClient():HttpClient{
        return HttpClient(Android){
            install(Logging) {
                level = LogLevel.ALL
            }
            install(JsonFeature) {
                serializer = KotlinxSerializer()
            }
        }
    }

    @Provides
    @Singleton
    fun providePostsService(client: HttpClient): PostsService {
        return PostServiceImp(client)
    }
}