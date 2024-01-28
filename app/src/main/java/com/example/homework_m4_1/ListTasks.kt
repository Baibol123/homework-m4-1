package com.example.homework_m4_1

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListTasks(
    var name: String = String(),
    var position: Int = 0,
    val type: TypeTasks = TypeTasks.IN_PROGRESS,
    var category: String = "Default"
) : Parcelable

@Parcelize
enum class TypeTasks(val id: Int = 0) : Parcelable {
    LIST,
    IN_PROGRESS(1),
    COMPLETED(2)
}