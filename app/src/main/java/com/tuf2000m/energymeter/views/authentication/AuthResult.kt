package com.tuf2000m.energymeter.views.authentication

import com.google.firebase.auth.FirebaseUser

sealed class AuthResult {
    data class Success(val user: FirebaseUser?) : AuthResult()
    data class Failure(val exception: Exception?) : AuthResult()
}
