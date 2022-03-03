package com.wonmirzo.model

import com.google.gson.annotations.SerializedName

data class PosterResp(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("UserId")
    val userId: Int = 0,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("body")
    val body: String? = null
)
