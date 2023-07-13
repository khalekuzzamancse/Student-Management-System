package teacher_section.text_editor

class UnOrderTextConverted(
    private val text: String,
    private val bulletPoint: Char = '•',
    private val start: Int,
    private val end: Int,
) {

    private fun findAllNewLineIndex(): List<Int> {
        val list = mutableListOf<Int>()
        for (i in start..end) {
            val index = i - 1
            val isWithinRange = index >= 0 && index < text.length
            if (isWithinRange && text[index] == '\n') {
                list.add(index)
            }
        }
        return list
    }

    private fun addBullets(): String {

        val textBuilder = StringBuilder(text)
        findAllNewLineIndex().forEachIndexed { i, index ->
            //after inserting a bullet the text size will increase by 1
            //that is why we have to shift by 1
            val insertBulletAt = index + 1 + i
            val isWithinRange=insertBulletAt >= 0 && insertBulletAt <= textBuilder.length - 1
            if (isWithinRange)
                textBuilder.insert(insertBulletAt, bulletPoint)
        }
        val isBeginningTextSelected=start==0
        if(isBeginningTextSelected)
            textBuilder.insert(0, bulletPoint)

        return textBuilder.toString()
    }

    fun getModifiedText(): String {
        return addBullets()
    }
}