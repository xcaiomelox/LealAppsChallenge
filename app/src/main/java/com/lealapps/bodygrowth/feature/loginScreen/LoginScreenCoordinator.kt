package com.lealapps.bodygrowth.feature.loginScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class LoginScreenCoordinator(
    val viewModel: LoginScreenViewModel
) {
    val screenStateFlow = viewModel.stateFlow


}

@Composable
fun rememberLoginScreenCoordinator(
    viewModel: LoginScreenViewModel = hiltViewModel()
): LoginScreenCoordinator {
    return remember(viewModel) {
        LoginScreenCoordinator(
            viewModel = viewModel
        )
    }
}