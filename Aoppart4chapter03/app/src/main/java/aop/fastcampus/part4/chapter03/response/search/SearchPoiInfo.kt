package aop.fastcampus.part4.chapter03.response.search

data class SearchPoiInfo(
    val count: String,
    val page: String,
    val pois: Pois,
    val totalCount: String
)