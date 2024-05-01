package com.lealapps.bodygrowth.feature.addEditTraining.components

import android.app.DatePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.lealapps.bodygrowth.R
import java.sql.Timestamp
import java.util.Calendar

@Composable
fun DateCard(
    date: Timestamp?,
    onSelectNewDate: (Timestamp) -> Unit
) {
    val context = LocalContext.current
    val formattedDate = date.toFormattedDate()
    TextField(
        value = formattedDate,
        onValueChange = {},
        label = @Composable { Text("Selecionar Data") },
        enabled = false,
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.background_buttomDate))
            .clickable {
                val instanceCalendar = Calendar.getInstance()
                val year = instanceCalendar.get(Calendar.YEAR)
                val month = instanceCalendar.get(Calendar.MONTH)
                val day = instanceCalendar.get(Calendar.DAY_OF_MONTH)
                DatePickerDialog(
                    context,
                    { _, year, month, day ->
                        onSelectNewDate.invoke(Timestamp.valueOf("$year-${month + 1}-$day 12:00:00"))
                    },
                    year,
                    month,
                    day
                ).show()
            },
    )
}
private fun Timestamp?.toFormattedDate(): String = if (this == null) "Insira a data" else
    toString().take(10)