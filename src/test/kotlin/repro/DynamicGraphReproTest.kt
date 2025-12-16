package dev.jonnya.repro

import dev.zacsweers.metro.BindingContainer
import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metro.GraphExtension
import dev.zacsweers.metro.Provides
import dev.zacsweers.metro.createDynamicGraph
import kotlin.test.Test
import kotlin.test.assertEquals

@DependencyGraph
interface RootGraph : ExtensionGraph.Factory

@GraphExtension
interface ExtensionGraph {
    val value: String

    @GraphExtension.Factory
    interface Factory {
        fun createSubGraph(
            @Provides value: String = "real"
        ): ExtensionGraph
    }
}

@BindingContainer
object FakeBindings {
    @Provides
    val value: String = "fake"
}

class DynamicGraphTest {
    @Test
    fun `replace bindings in subgraph`() {
        val subGraph =
            createDynamicGraph<RootGraph>(FakeBindings)
                .createSubGraph()

        // Fails unexpectedly
        assertEquals("fake", subGraph.value)
    }
}

