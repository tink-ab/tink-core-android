package com.tink.core

import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

internal class TinkTest {

    @Test
    fun `check for initialization`() {

        assertThrows(IllegalStateException::class.java) {
            Tink.setUser("accessToken")
        }

        assertThrows(IllegalStateException::class.java) {
            Tink.providerRepository()
        }
    }
}
