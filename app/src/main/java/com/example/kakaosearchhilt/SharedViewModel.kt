package com.example.kakaosearchhilt

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kakaosearchhilt.data.ImageSearchRepository
import com.example.kakaosearchhilt.model.ItemModel
import com.example.kakaosearchhilt.pref.PrefSearchKeywordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val imageSearchRepository: ImageSearchRepository,
    private val prefSearchKeywordRepository: PrefSearchKeywordRepository,
) : ViewModel() {
//    companion object {
//        val factory = viewModelFactory {
//            initializer {
//                val myApp =
//                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MyApp)
//                val container = myApp.container
//                SharedViewModel(
//                    imageSearchRepository = container.imageSearchRepository,
//                    prefSearchKeywordRepository = container.prefSearchKeywordRepository,
//                )
//            }
//        }
//    }

    private val _searchResultSF = MutableStateFlow<List<ItemModel>>(emptyList())
    val searchResultSF: StateFlow<List<ItemModel>> = _searchResultSF
//    fun setSearchResultLd(list: List<ItemModel>) {
//        _searchResultLd.value = list
//    }
//
//    fun postSearchResultLd(list: List<ItemModel>) {
//        _searchResultLd.postValue(list)
//    }

    fun updateLoved(item: ItemModel, isLoved: Boolean): Boolean {
        val index = searchResultSF.value.indexOf(item)
        if (index < 0) return false
        _searchResultSF.value = _searchResultSF.value.toMutableList().apply {
            this[index] = item.copy(isLoved = isLoved)
        }
        return true
    }


//// mutableStateListOf 로 구현해보려 했는데 뭔가 갱신이 안됨.
//// 근데 다른 문제였던 것 같아서 나중에 다시 시도하기로.
//    private var _myBoxList = mutableStateListOf<ItemModel>()
//    val myBoxList: List<ItemModel> get() = _myBoxList
//    fun setMyBoxList(list: List<ItemModel>) {
//        _myBoxList = list.toMutableStateList()
//    }
//
//    fun addToMyBoxList(item: ItemModel) {
//        _myBoxList.add(item)
//    }
//
//    fun removeFromMyBoxList(item: ItemModel) {
//        _myBoxList.remove(item)
//    }

    private val _myBoxList = MutableStateFlow<List<ItemModel>>(emptyList())
    val myBoxList: StateFlow<List<ItemModel>> = _myBoxList
    private fun setMyBoxList(list: List<ItemModel>) {
        _myBoxList.value = list
    }

    fun addToMyBoxList(item: ItemModel) {
        setMyBoxList(myBoxList.value + item)
    }

//    fun removeFromMyBoxList(item: ItemModel) {
//        setMyBoxList(myBoxList.value!! - item)
//    }

    fun removeFromMyBoxList(thumbnailURL: String) {
        val i = myBoxList.value.indexOfFirst { it.thumbnailURL == thumbnailURL }
        if (i >= 0) setMyBoxList(myBoxList.value.toMutableList().apply { removeAt(i) })
    }

    fun searchImage(
        query: String,
        sort: String? = null,
        page: Int? = null,
        size: Int? = null
    ) = viewModelScope.launch {
        _searchResultSF.value = imageSearchRepository.searchImage(query, sort, page, size)
        prefSearchKeywordRepository.save(query)
    }

    fun loadKeyword() = prefSearchKeywordRepository.load()

}
