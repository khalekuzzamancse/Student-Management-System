package teacher_section.class_work.assign_assignment

class UnOrderTextConverted(
    private val text: String,
    private val bulletPoint: Char = '•',
) {
    private fun findAllNewLineIndex() = text.mapIndexedNotNull { index, char ->
        if (char == '\n')
            index else null
    }

    private fun addBullets(): String {
        val textBuilder = StringBuilder(text)
        findAllNewLineIndex().forEachIndexed { i, index ->
            //after inserting a bullet the text size will increase by 1
            //that is why we have to shift by 1
            val insertBulletAt = index + 1 + i;
            textBuilder.insert(insertBulletAt, bulletPoint)
        }
        //add a extra bullet at index 0
        textBuilder.insert(0, bulletPoint)
        return textBuilder.toString()
    }
    fun getModifiedText(): String {
        return addBullets()
    }
}