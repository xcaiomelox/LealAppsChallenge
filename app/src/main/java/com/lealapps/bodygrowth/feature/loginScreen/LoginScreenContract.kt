package com.lealapps.bodygrowth.feature.loginScreen


/**
 * UI State that represents LoginScreenScreen
 **/
class LoginScreenState

/**
 * LoginScreen Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class LoginScreenActions(
    val onClick: () -> Unit = {}
)