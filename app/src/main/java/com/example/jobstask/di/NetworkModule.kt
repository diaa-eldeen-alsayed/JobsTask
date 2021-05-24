import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.math.sin

val networkModule = module {
    val connectTimeout: Long = 40// 20s
    val readTimeout: Long = 40 // 20s

    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        // For logging
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    fun provideHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                .readTimeout(readTimeout, TimeUnit.SECONDS)
        okHttpClientBuilder.build()
        return okHttpClientBuilder.build()
    }

    fun provideRetrofit(client: OkHttpClient, baseUrl: String): Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
    }
    single {
        provideHttpLoggingInterceptor()
    }
    single {
        provideHttpClient(get())
    }
    single {
        val baseUrl = "https://jobs.github.com/"
        provideRetrofit(get(), baseUrl)
    }


}