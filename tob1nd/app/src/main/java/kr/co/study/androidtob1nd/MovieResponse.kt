package kr.co.study.androidtob1nd

data class MovieResponse(
    val movieListResult: MovieData
)

data class MovieData(
    val totCnt: Int,
    val source: String,
    val movieList: List<Movie>
)
