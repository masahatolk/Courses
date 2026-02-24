package com.hits.feature.main.ui.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
fun formatDateToReadable(dateString: String): String {
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val outputFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale("ru"))

    val date = LocalDate.parse(dateString, inputFormatter)
    val formatted = date.format(outputFormatter)
    return formatted.replaceFirstChar { it.uppercase() }
}