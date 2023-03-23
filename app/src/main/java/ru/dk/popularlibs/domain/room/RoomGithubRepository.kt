package ru.dk.popularlibs.domain.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomGithubUser::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )
    ]
)
data class RoomGithubRepository(
    @PrimaryKey
    var id: Int,
    var userId: Int,
    var createdAt: String,
    var defaultBranch: String,
    var description: String,
    var forks: Int,
    var homepage: String,
    var htmlUrl: String,
    var language: String,
    var name: String,
    var size: Int,
    var updatedAt: String,
    var visibility: String,
    var watchers: Int
)
