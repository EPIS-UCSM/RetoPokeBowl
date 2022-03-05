package bimerso.megabitefood.retopokebowl.ui.features.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import bimerso.megabitefood.retopokebowl.ui.features.detail.repository.DetailFoodRepository

class DetailFoodViewModelProviderFactory(
    val repository: DetailFoodRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailFoodViewModel(repository) as T
    }
}
