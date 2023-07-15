package teacher_section.text_editor.ordering_text

class OrderTextConverter(
    private val originalText: String,
    private val start: Int,
    private val end: Int,
) {

    private val bulletPoint: Char = '•'
    private val beforeSelectedText: String = originalText.substring(0, start)
    private val afterSelectedText: String = originalText.substring(end)
    private val selectedText: String = originalText.substring(start, end)
    private fun getConvertedText(updatedSelectedText: String) =
        beforeSelectedText + updatedSelectedText + afterSelectedText

    fun formatWithBullet(): String {
        val replacedText = TextModifier(selectedText)
            .removeExistingBulletPoints()
            .removeExistingNumberPoints()
            .addPointBefore1stCharacter(bulletPoint.toString())
            .addBulletPoints()
            .text
        return getConvertedText(replacedText)
    }

    fun formatWithNumber(): String {
        val replacedText = TextModifier(selectedText)
            .removeExistingNumberPoints()
            .removeExistingBulletPoints()
            .addPointBefore1stCharacter("1.")
            .insertNumberPoint()
            .text
        return getConvertedText(replacedText)
    }

    fun clearFormat(): String {
        val replacedText = TextModifier(selectedText)
            .removeExistingBulletPoints()
            .removeExistingNumberPoints()
            .text
        return getConvertedText(replacedText)
    }
}

data class TextModifier(
    val text: String,
) {
    fun removeExistingBulletPoints(): TextModifier {
        return TextModifier(text.replace(Regex("•"), ""))
    }

    fun removeExistingNumberPoints() =
        TextModifier(text.replace(Regex("""\d+\.\s*"""), ""))

    fun addPointBefore1stCharacter(point: String) =
        TextModifier(StringBuilder(text).insert(0, point).toString())

    fun addBulletPoints() = TextModifier(text.replace("\n", "\n•"))
    fun insertNumberPoint(): TextModifier {
        val lines = text.split("\n")
        var count = 2
        val result = StringBuilder()
        for (line in lines) {
            result.append(line)
            if (count <= lines.size) {
                result.append("\n$count.")
                count++
            }
        }
        return TextModifier(result.toString())
    }

}