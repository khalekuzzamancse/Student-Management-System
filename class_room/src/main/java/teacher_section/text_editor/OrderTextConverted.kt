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

     fun formatWithBullet(): String {
         //add a bullet before 1st character,so add a manually bullet to it
         val text=StringBuilder(text).insert(start,bulletPoint).toString()
         val prefix = text.substring(0,start)
         val suffix = text.substring(end)
         val rangeText = text.substring(start, end)
         val replacedText = rangeText.replace("\n", "\n$bulletPoint")
         return prefix + replacedText + suffix
    }
    fun formatWithNumber(): String {
        val l = start
        val r = end
        val prefix = text.substring(0, l)
        val suffix = text.substring(r)
        val rangeText = text.substring(l, r)
        val replacedText = rangeText.replace("\n", "\n")
        return prefix + replacedText + suffix
    }

}