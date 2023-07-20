package com.khalekuzzaman.just.cse.text_editor

import com.khalekuzzaman.just.cse.text_editor.text_formatting.Formatter
import com.khalekuzzaman.just.cse.text_editor.text_formatting.Formatters
import com.khalekuzzaman.just.cse.text_editor.text_formatting.TreeMapUtilsImp
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.TreeMap

class TreeMapUtilsTest {
    private val map = TreeMap<Int, Set<Formatter>>()


    init {
        map[1] = setOf(Formatters.Bold, Formatters.Italic, Formatters.UnderLine)
        map[3] = setOf(Formatters.Bold, Formatters.RedColor)
    }
    @Test
    fun hasCommonNeighbour() {
        val utilsImp = TreeMapUtilsImp(map)
        Assertions.assertEquals(true, utilsImp.hasNeighbourCommon(2))
        Assertions.assertEquals(false, utilsImp.hasNeighbourCommon(1))
        Assertions.assertEquals(false, utilsImp.hasNeighbourCommon(3))
    }
    @Test
    fun getNeighbourCommons() {
        val utilsImp = TreeMapUtilsImp(map)
        Assertions.assertEquals(setOf(Formatters.Bold), utilsImp.getNeighbourCommons(2))
        Assertions.assertEquals(emptySet<Formatter>(), utilsImp.getNeighbourCommons(1))
        Assertions.assertEquals(emptySet<Formatter>(), utilsImp.getNeighbourCommons(3))
        Assertions.assertEquals(emptySet<Formatter>(), utilsImp.getNeighbourCommons(5))
    }
}