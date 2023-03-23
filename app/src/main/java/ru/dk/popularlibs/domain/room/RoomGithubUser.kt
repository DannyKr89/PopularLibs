package ru.dk.popularlibs.domain.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomGithubUser(
    @PrimaryKey
    var id: Int,
    var login: String,
    var avatarUrl: String,
    var reposUrl: String
)