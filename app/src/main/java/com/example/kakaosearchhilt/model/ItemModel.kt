package com.example.kakaosearchhilt.model

data class ItemModel(
    val thumbnailURL: String,
    val displaySitename: String,
    val datetime: String,
    val isLoved: Boolean = false
) {
    companion object {
        fun new() = ItemModel(
            thumbnailURL = "https://picsum.photos/200",
            displaySitename = "사이트 이름 공간",
            datetime = "2002-02-02 12:34:56",
            isLoved = false,
        )
    }
}
