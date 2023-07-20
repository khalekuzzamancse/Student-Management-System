package com.khalekuzzaman.just.cse.text_editor.text_ordering


data class BulletManager(
    val text: String,
    val start: Int,
    val end: Int,
) {
    companion object {
        private const val BULLET = '•'
        private const val NEW_LINE = '\n'
    }

    fun removeBullets(onBulletRemove: (String, Int) -> Unit) {
        val result = StringBuilder(text)
        for (i in end downTo start) {
            if (i in result.indices && result[i] == BULLET) {
                result.deleteCharAt(i)
                onBulletRemove(result.toString(), i)
            }
        }
    }

    fun insertBullet(onInsert: (String, Int) -> Unit) {
        val result = StringBuilder(text)
        for (i in end downTo start) {
            val bounded = i in result.indices && result[i] == NEW_LINE && result[i + 1] != BULLET
            if (bounded) {
                result.insert(i + 1, BULLET)
                onInsert(result.toString(), i + 1)
            }
        }
        val hasNotBullet =
            start in result.indices && result[start] != BULLET && result[start] != NEW_LINE
        if (hasNotBullet) {
            result.insert(start, BULLET)
            onInsert(result.toString(), start)

        }

    }


}


