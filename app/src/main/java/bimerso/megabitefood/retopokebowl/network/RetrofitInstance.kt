package bimerso.megabitefood.retopokebowl.network

import bimerso.megabitefood.retopokebowl.network.Url.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInstance {
    companion object{
        private val retrofit by lazy {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder().addInterceptor(logging).build()

            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(client).build()
        }
        val api by lazy {
            retrofit.create(API::class.java)
        }
    }
    //Add token no necesary now
    open fun provideApi(): API {
        val httpClientBuilder = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        httpClientBuilder.addInterceptor(loggingInterceptor)
        httpClientBuilder.addInterceptor(HeaderInterceptor())

        val httpClient = httpClientBuilder.build()
        val url = Url.BASE_URL
        return Retrofit.Builder()
            .baseUrl(url!!)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(API::class.java)
    }
}