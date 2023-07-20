package com.khalekuzzaman.just.cse.text_editor.text_ordering

data class BulletDeletionManager(
    val text: String,
    val start: Int,
    val end: Int,
) {
    companion object {
        const val BULLET = '•'
    }

    private fun isBullet(index: Int) =
        index <= end && index < text.length && text[index] == BULLET

    fun findBulletsPosition(): List<Int> {
        val indices = mutableListOf<Int>()
        for (i in start..end) {
            if (isBullet(i))
                indices.add(i)
        }
        return indices
    }

    private fun indicesWithShifting(): List<Ordering> {
        val indicesWithShift = mutableListOf<Ordering>()
        findBulletsPosition().forEachIndexed { i, v ->
            val bulletsBeforeIndex = i + 1
            indicesWithShift.add(Ordering(index = v, shift = -bulletsBeforeIndex))
        }
        return indicesWithShift
    }

    fun removeBullets(): Pair<String, List<Ordering>> {
        val result = StringBuilder(text)
        for (i in end downTo start) {
            if (result[i] == BULLET) {
                result.deleteCharAt(i)
            }
        }
        return Pair(result.toString(), indicesWithShifting())
    }
}