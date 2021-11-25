package kr.co.study.data

import kr.co.study.domain.GithubRepo

data class GithubRepoRes(
    @SerializedName("name")
    private val _name: String,

    @SerializedName("id")
    private val _id: String,

    @SerializedName("create_at")
    private val _date: String,

    @SerializedName("html_url")
    private val _url: String
) : GithubRepo {

    override val name: String
        get() = _name

    override val url: String
        get() = _url
}
