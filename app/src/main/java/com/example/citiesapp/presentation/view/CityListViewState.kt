import com.example.citiesapp.presentation.model.CityUiModel

sealed class CityListViewState {
    data object Loading : CityListViewState()
    data class Success(val cities: List<CityUiModel>) : CityListViewState()
    data class Error(val message: String) : CityListViewState()
}
