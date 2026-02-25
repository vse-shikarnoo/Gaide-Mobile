package kv.gaide.navigation.root

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RootNavigationViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val KEY = "root_graph"

    private val _graph = MutableStateFlow(
        savedStateHandle.get<String>(KEY)
            ?.let { RootGraph.fromRoute(it) }
            ?: RootGraph.Auth
    )

    val graph: StateFlow<RootGraph> = _graph

    fun openAuth() = setGraph(RootGraph.Auth)

    fun openMain() = setGraph(RootGraph.Main)

    private fun setGraph(graph: RootGraph) {
        _graph.value = graph
        savedStateHandle[KEY] = graph.toRoute()
    }
}