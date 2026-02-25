package kv.gaide.navigation.main

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainNavigationViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val KEY = "main_stack"

    private val _stack = MutableStateFlow(
        savedStateHandle.get<List<String>>(KEY)
            ?.map { MainScreens.fromRoute(it) }
            ?: listOf(MainScreens.MuseumList)
    )

    val stack: StateFlow<List<MainScreens>> = _stack

    fun push(screen: MainScreens) {
        update(_stack.value + screen)
    }

    fun pop(): Boolean {
        return if (_stack.value.size > 1) {
            update(_stack.value.dropLast(1))
            true
        } else false
    }

    fun clear() {
        update(listOf(MainScreens.MuseumList))
    }

    private fun update(newStack: List<MainScreens>) {
        _stack.value = newStack
        savedStateHandle[KEY] = newStack.map { it.toRoute() }
    }
}