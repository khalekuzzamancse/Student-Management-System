package teacher_section.text_editor

class OrderTextConverter(
    private val originalText: String,
    private val start: Int,
    private val end: Int,
) {

    private val bulletPoint: Char = '•'
    fun formatWithBullet(): String {
        val beforeSelectedText = originalText.substring(0, start)
        val afterSelectedText = originalText.substring(end)
        val selectedText = originalText.substring(start, end)
        //
        val replacedText = TextModifier(selectedText)
            .removeExistingBulletPoints()
            .removeExistingNumberPoints()
            .addPointBefore1stCharacter(bulletPoint.toString())
            .addBulletPoints()
            .text
        return beforeSelectedText + replacedText + afterSelectedText
    }

    fun formatWithNumber(): String {
        val text = originalText
        val beforeSelectedText = text.substring(0, start)
        val afterSelectedText = text.substring(end)
        val selectedText = text.substring(start, end)
        //
        val replacedText = TextModifier(selectedText)
            .removeExistingNumberPoints()
            .removeExistingBulletPoints()
            .addPointBefore1stCharacter("1.")
            .insertNumberPoint()
            .text
        return beforeSelectedText + replacedText + afterSelectedText
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