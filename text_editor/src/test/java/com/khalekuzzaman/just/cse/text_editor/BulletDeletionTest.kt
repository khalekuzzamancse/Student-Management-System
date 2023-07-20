package com.khalekuzzaman.just.cse.text_editor

import com.khalekuzzaman.just.cse.text_editor.text_ordering.BulletDeletionManager
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class BulletDeletionTest {
    // @Test
    fun findBulletPosition() {
        val text = "•0\n•1\n•2"
        val expected = listOf(0, 3, 6)
        Assertions.assertEquals(
            expected,
            BulletDeletionManager(
                text = text,
                start = 0,
                end = text.length - 1
            ).findBulletsPosition()
        )
    }
}




fun main() {
    val text = "•0\n•1\n•2"
    println(
        BulletDeletionManager(
            text = text,
            start = 0,
            end = text.length - 1
        ).removeBullets().first
    )
    println(
        BulletDeletionManager(
            text = text,
            start = 0,
            end = text.length - 1
        ).removeBullets().second
    )

}