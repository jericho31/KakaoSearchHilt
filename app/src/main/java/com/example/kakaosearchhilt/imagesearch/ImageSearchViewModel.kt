//package com.example.kakaosearchhilt.imagesearch
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import androidx.lifecycle.viewmodel.initializer
//import androidx.lifecycle.viewmodel.viewModelFactory
//import com.example.kakaosearchhilt.SharedViewModel
//import com.example.kakaosearchhilt.data.ImageSearchRepository
//import com.example.kakaosearchhilt.model.ItemModel
//import com.example.kakaosearchhilt.myContainer
//import kotlinx.coroutines.launch
//
//class ImageSearchViewModel(
//    private val imageSearchRepository: ImageSearchRepository,
//    // TODO: 이거 아니라고 함...
//    private val sharedViewModel: SharedViewModel
//) : ViewModel() {
//
////    private val _uiState: MutableLiveData<SearchListUiState> =
////        MutableLiveData(SearchListUiState.init())
////    val uiState: LiveData<SearchListUiState> get() = _uiState
//
//    // 참조로 넘어오겠지...? 그냥 쓰면 되겠지..?
//    private val searchResultLd = sharedViewModel.searchResultLd
//    val uiState: LiveData<List<ItemModel>> get() = searchResultLd
//
//    /**
//     * Gets Kakao query data from the Kakao image search API Retrofit service and updates the
//     * [searchResultLd] [] [MutableLiveData].
//     */
//    fun searchImage(
//        query: String,
//        sort: String? = null,
//        page: Int? = null,
//        size: Int? = null
//    ) = viewModelScope.launch {
//        searchResultLd.postValue(imageSearchRepository.searchImage(query, sort, page, size))
//    }
//
//    companion object {
//        /**
//         * Factory for [ImageSearchViewModel] that takes [ImageSearchRepository] as a dependency
//         */
//        fun createFactory(sharedViewModel: SharedViewModel) = viewModelFactory {
//            // TODO: initializer를 감싸면 매번 새 뷰모델. 아니면 기존 뷰모델 맞나? 그러면 안감싸도?
//            initializer {
//                ImageSearchViewModel(
//                    imageSearchRepository = myContainer.imageSearchRepository,
//                    sharedViewModel = sharedViewModel
//                )
//            }
//        }
//    }
//}
