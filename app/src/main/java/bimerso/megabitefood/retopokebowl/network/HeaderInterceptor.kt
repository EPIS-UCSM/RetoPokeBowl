package bimerso.megabitefood.retopokebowl.network


import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
            .addHeader("Authorization","bearer " + "Url.API_KEY")
            .build()
        return chain.proceed(request)
    }
}