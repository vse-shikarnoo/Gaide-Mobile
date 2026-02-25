package kv.gaide.navigation.auth

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AuthNavigationViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val KEY = "auth_stack"

    private val _stack = MutableStateFlow(
        savedStateHandle.get<List<String>>(KEY)
            ?.map { AuthScreens.fromRoute(it) }
            ?: listOf(AuthScreens.Onboarding)
    )

    val stack: StateFlow<List<AuthScreens>> = _stack

    fun push(screen: AuthScreens) {
        update(_stack.value + screen)
    }

    fun pop(): Boolean {
        return if (_stack.value.size > 1) {
            update(_stack.value.dropLast(1))
            true
        } else false
    }

    fun clear() {
        update(listOf(AuthScreens.Onboarding))
    }

    private fun update(newStack: List<AuthScreens>) {
        _stack.value = newStack
        savedStateHandle[KEY] = newStack.map { it.toRoute() }
    }
}