package ru.dk.popularlibs.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUserReposItem(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("default_branch")
    val defaultBranch: String,
    val description: String,
    val forks: Int,
    val homepage: String,
    @SerializedName("html_url")
    val htmlUrl: String,
    val language: String,
    val name: String,
    val size: Int,
    @SerializedName("updated_at")
    val updatedAt: String,
    val visibility: String,
    val watchers: Int
) : Parcelable