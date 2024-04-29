package com.lealapps.bodygrowth.feature.loginScreen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LoginScreenScreen(
    state: LoginScreenState,
    actions: LoginScreenActions,
) {
    // TODO() -> Criar tela de login
}

@Composable
@Preview(name = "LoginScreen")
private fun LoginScreenScreenPreview() {
    LoginScreenScreen(
        state = LoginScreenState(),
        actions = LoginScreenActions()
    )
}

