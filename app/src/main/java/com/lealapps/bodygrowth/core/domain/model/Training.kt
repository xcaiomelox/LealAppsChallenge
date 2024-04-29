package com.lealapps.bodygrowth.core.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity
data class Training(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val title: String = "",
    val description: String? = null,
    val date: Timestamp? = null,
    val isDone: Boolean = false,
    val exercises: List<Exercise> = listOf()
)


