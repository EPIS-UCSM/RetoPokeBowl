package bimerso.megabitefood.retopokebowl.ui.features.detail.viewmodel


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import bimerso.megabitefood.retopokebowl.configuration.Constant
import bimerso.megabitefood.retopokebowl.models.Data
import bimerso.megabitefood.retopokebowl.models.DetailResponse
import bimerso.megabitefood.retopokebowl.ui.features.detail.repository.DetailFoodRepository
import kotlinx.coroutines.Dispatchers


class DetailFoodViewModel(private val repository: DetailFoodRepository): ViewModel() {
    sealed class ViewState{
        object Loading: ViewState()
        data class Failure(val error:String) : ViewState()
        data class GetDetailFood(val entidad: Data) : ViewState()
    }
    fun getDetailFood(id:String) = liveData(Dispatchers.IO){
        emit(ViewState.Loading)
        try {
            val response = repository.getDetailFood(id)
            if (response.isSuccessful) {
                response.body()?.let { detailFood->
                    if(detailFood.code == Constant.RESPONSE_SUCCESSFUL){
                        emit(ViewState.GetDetailFood(detailFood.data))
                    }
                    else{
                        emit(ViewState.Failure("Hubo un error, intente mas tarde."))
                    }
                }
            }
            else
                emit(ViewState.Failure(response.errorBody().toString()))
        }
        catch (e:Exception){

            emit(ViewState.Failure(e.toString()))
        }
    }


}