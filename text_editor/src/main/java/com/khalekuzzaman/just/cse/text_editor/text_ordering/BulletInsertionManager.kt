package com.khalekuzzaman.just.cse.text_editor.text_ordering


data class Ordering(
    val index: Int,
    val shift: Int,
)


data class BulletInsertionManager(
    val text: String,
    val start: Int,
    val end: Int,
) {
    companion object {
        const val BULLET = '•'
        const val NEW_LINE = '\n'
    }

    private val result = StringBuilder(text)

    private fun findBulletSlots(): List<Int> {
        val indices = mutableListOf<Int>()
        var bulletPosition = text[start] != BULLET && text[start] != NEW_LINE
        if (bulletPosition)
            indices.add(start)

        for (i in start + 1 until end) {
            bulletPosition = text[i] == NEW_LINE && text[i + 1] != BULLET
            if (bulletPosition)
                indices.add(i + 1)
        }
        return indices
    }

    fun findIndicesWithShift(): List<Ordering> {
        val indicesWithShift = mutableListOf<Ordering>()
        findBulletSlots().forEachIndexed { i, v ->
            val bulletsBeforeIndex = i + 1
            indicesWithShift.add(Ordering(index = v, shift = bulletsBeforeIndex))
        }
        return indicesWithShift
    }


    fun insertBullets(): Pair<String,List<Ordering>> {
        val updatedEnd = end + findBulletSlots().size
        var hasNotBullet = result[start] != BULLET && result[start] != NEW_LINE
        if (hasNotBullet) {
            result.insert(start, BULLET)
        }
        for (i in start + 1 until updatedEnd) {
            val bounded = i + 1 <= updatedEnd && i + 1 < result.length
            hasNotBullet = result[i] == NEW_LINE && result[i + 1] != BULLET

            if (bounded && hasNotBullet) {
                result.insert(i + 1, BULLET)
            }
        }
        return Pair(result.toString(),findIndicesWithShift())
    }



}


