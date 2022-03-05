package bimerso.megabitefood.retopokebowl.network

import bimerso.megabitefood.retopokebowl.models.DetailResponse
import retrofit2.Response
import retrofit2.http.*

interface API {
    @GET("bff/menu-item/detail/{id}")
    suspend fun getDetailFoodById(@Path("id") id:String) :Response<DetailResponse>


}