package com.lealapps.bodygrowth.feature.loginScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

@Composable
fun LoginScreenRoute(
    coordinator: LoginScreenCoordinator = rememberLoginScreenCoordinator()
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsState(LoginScreenState())

    // UI Actions
    val actions = rememberLoginScreenActions(coordinator)

    // UI Rendering
    LoginScreenScreen(uiState, actions)
}


@Composable
fun rememberLoginScreenActions(coordinator: LoginScreenCoordinator): LoginScreenActions {
    return remember(coordinator) {
        LoginScreenActions(
        )
    }
}