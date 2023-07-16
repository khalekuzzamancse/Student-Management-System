package com.khalekuzzaman.just.cse.text_editor.version_02

import java.util.TreeMap

interface TextChangeListener {
    fun onTextChange(): TreeMap<Int, Set<Formatter>>
}


data class SingleCharacterChangeListener(
    private val changeUtils: SingleCharacterChangeUtils,
    private var formattedIndices: TreeMap<Int, Set<Formatter>>,
) : TextChangeListener {

    override fun onTextChange(): TreeMap<Int, Set<Formatter>> {
        /*
      If no character change then need to to shift.
      if character inseretd then need shift
      if character removed then:
          check if the removed characeter was formatted
          if formataed then remove it
          then shift the remaining indices
      Shift the indices if need.
      if character was removed or updated then shift it
       */
        if (changeUtils.isTextUnchanged())
            return formattedIndices

        if (changeUtils.hasRemoval()) {
            val removedFrom = changeUtils.getChangeIndex()
            if (wasRemovedIndexFormatted(removedFrom))
                removeIndex(removedFrom)
        }
        shiftFormattedIndices(changeUtils.getChangeIndex(), changeUtils.getShiftAmount())
        return formattedIndices
    }

    private fun wasRemovedIndexFormatted(key: Int) = formattedIndices.containsKey(key)
    private fun shiftFormattedIndices(
        characterChangedAt: Int,
        shiftAmount: Int,
    ) {
        val updatedFormattedIndices = mutableMapOf<Int, Set<Formatter>>()
        for ((key, value) in formattedIndices) {
            val newKey = if (characterChangedAt <= key) key + shiftAmount else key
            updatedFormattedIndices[newKey] = value
        }
        formattedIndices = TreeMap(updatedFormattedIndices)
    }

    private fun removeIndex(index: Int) {
        // val key = formattedIndices.keys.elementAt(index)
        val updatedFormattedIndices = formattedIndices.filterKeys { it != index }
        formattedIndices = TreeMap(updatedFormattedIndices)
    }

}


//fun main() {
//    val a = "012345678"
//    val b = "02345678"
//    val map = TreeMap<Int, Set<Formatter>>()
//    map[3] = setOf(Formatters.Bold)
//    map[4] = setOf(Formatters.Bold)
//    map.forEach{
//        print("${it.key} ")
//    }
//    val listener = SingleCharacterChangeListener(
//        SingleCharacterChangeUtils(previousText = a, currentText = b),
//        TreeMap(map)
//    )
//
//    println("")
//    listener.onTextChange().forEach{
//        print("${it.key} ")
//    }
//}