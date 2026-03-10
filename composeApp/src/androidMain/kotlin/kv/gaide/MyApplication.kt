package kv.gaide

import android.app.Application
import kv.gaide.data.repository.auth.AuthRepositoryImpl

class MyApplication : Application() {
    val authRepository by lazy { AuthRepositoryImpl() }
}