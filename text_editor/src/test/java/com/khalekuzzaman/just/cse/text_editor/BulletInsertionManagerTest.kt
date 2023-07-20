package com.khalekuzzaman.just.cse.text_editor

import com.khalekuzzaman.just.cse.text_editor.text_ordering.BulletInsertionManager
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


class BulletInsertionManagerTest {

    @Test
    fun testInsertBullets() {
        var text = "0\n1\n2"
        val expected = "•0\n•1\n•2"
        Assertions.assertEquals(
            expected,
            BulletInsertionManager(text = text, start = 0, end = text.length - 1)
                .insertBullets().first
        )
        text = "•0\n1\n2"
        Assertions.assertEquals(
            expected,
            BulletInsertionManager(text = text, start = 0, end = text.length - 1)
                .insertBullets().first
        )
        text = "•0\n•1\n2"
        Assertions.assertEquals(
            expected,
            BulletInsertionManager(text = text, start = 0, end = text.length - 1)
                .insertBullets().first
        )
        text = "•0\n•1\n•2"
        Assertions.assertEquals(
            expected,
            BulletInsertionManager(text = text, start = 0, end = text.length - 1)
                .insertBullets().first
        )
    }
}
