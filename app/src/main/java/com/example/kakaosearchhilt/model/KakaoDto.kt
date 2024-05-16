package com.example.kakaosearchhilt.model

import com.squareup.moshi.Json

data class KakaoDto(
    val documents: List<Document>,
    val meta: Meta
)

data class Document(
    val collection: Collection,

    /** gson은 Date 알아서 먹어주는데 모시는 왜 못먹냐... */
    val datetime: String,

    @Json(name = "display_sitename")
    val displaySitename: String,

    @Json(name = "doc_url")
    val docURL: String,

    val height: Long,

    @Json(name = "image_url")
    val imageURL: String,

    @Json(name = "thumbnail_url")
    val thumbnailURL: String,

    val width: Long
)

enum class Collection(val value: String) {
    @Json(name = "blog")
    Blog("blog"),

    @Json(name = "cafe")
    Cafe("cafe"),

    @Json(name = "etc")
    Etc("etc"),

    @Json(name = "news")
    News("news");
}

data class Meta(
    @Json(name = "is_end")
    val isEnd: Boolean,

    @Json(name = "pageable_count")
    val pageableCount: Long,

    @Json(name = "total_count")
    val totalCount: Long
)
