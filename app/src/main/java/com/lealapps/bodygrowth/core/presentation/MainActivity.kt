package com.lealapps.bodygrowth.core.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.lealapps.bodygrowth.core.presentation.navigation.MainNavGraph
import com.lealapps.bodygrowth.feature.designSystem.theme.BodyGrowthTheme
import com.lealapps.bodygrowth.feature.trainingList.TrainingListActions
import com.lealapps.bodygrowth.feature.trainingList.TrainingListScreen
import com.lealapps.bodygrowth.feature.trainingList.TrainingListState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BodyGrowthTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainNavGraph(navController = rememberNavController())
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BodyGrowthTheme {
        MainNavGraph(navController = rememberNavController())
    }
}