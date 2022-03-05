package bimerso.megabitefood.retopokebowl.ui.features.detail.repository

import bimerso.megabitefood.retopokebowl.network.RetrofitInstance

class DetailFoodRepository (){
    suspend fun getDetailFood(id:String) = RetrofitInstance.api.getDetailFoodById(id)

}