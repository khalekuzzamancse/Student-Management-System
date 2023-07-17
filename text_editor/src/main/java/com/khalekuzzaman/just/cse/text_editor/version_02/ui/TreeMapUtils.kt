package com.khalekuzzaman.just.cse.text_editor.version_02.ui

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.khalekuzzaman.just.cse.text_editor.version_02.Formatter
import com.khalekuzzaman.just.cse.text_editor.version_02.Formatters
import java.util.TreeMap


interface TreeMapUtils {
    fun add(key: Int, set: Set<Formatter>): TreeMap<Int, Set<Formatter>>
    fun add(key: Int, formatter: Formatter): TreeMap<Int, Set<Formatter>>
    fun remove(key: Int): TreeMap<Int, Set<Formatter>>
    fun get(key: Int): Set<Formatter>
    fun remove(key: Int, formatter: Formatter): TreeMap<Int, Set<Formatter>>
    fun shiftKey(keys: List<Int>, shiftAmount: Int): TreeMap<Int, Set<Formatter>>
}
/*
  We don't allow to insert key with empty formatter
        because empty==no formatter so need to to store.

        Creating a complete object new map instead of muting old to
        avoid unexpected behaviour
        We do not want to replace the exiting formatter,
        If the key is already present that means it has formatter because we
        do not allowed empty formatter,in that case we take pull the existing formatter
        then add the new formatter to it

        Before removing check if the key is exits,if the key does not exits then do nothing
        While shifting,after shifting check if by some reason any index become -ve,
        that case filter out the -ve index
 */

data class TreeMapUtilsImp(
    val map: TreeMap<Int, Set<Formatter>>,
) : TreeMapUtils {
    companion object {
        const val TAG = "TreeMapUtilsImp01: "
    }

    override fun add(key: Int, set: Set<Formatter>): TreeMap<Int, Set<Formatter>> {
        if (key < 0) {
            throw IllegalArgumentException("add():Negative key not allowed($key)")
        }
        if (set.isEmpty())
            return TreeMap(map)
        val updatedMap = TreeMap(map)
        updatedMap[key] = get(key).plus(set)
        return updatedMap
    }

    override fun add(key: Int, formatter: Formatter): TreeMap<Int, Set<Formatter>> {
        if (key < 0) {
            throw IllegalArgumentException("add():Negative key not allowed($key)")
        }
        val updatedMap = TreeMap(map)
        updatedMap[key] = get(key).plus(formatter)
        return updatedMap
    }

    override fun remove(key: Int): TreeMap<Int, Set<Formatter>> {
        if (!map.containsKey(key))
            return TreeMap(map)
        val updatedMap = TreeMap(map)
        updatedMap.remove(key)
        return updatedMap
    }

    override fun remove(key: Int, formatter: Formatter): TreeMap<Int, Set<Formatter>> {
        if (!map.containsKey(key))
            return TreeMap(map)
        val set = get(key).filter { it != formatter }.toSet()
        val updatedMap = TreeMap(map)
        updatedMap[key] = set
        return updatedMap
    }

    override fun get(key: Int): Set<Formatter> {
        return map[key] ?: emptySet()
    }

    override fun shiftKey(keys: List<Int>, shiftAmount: Int): TreeMap<Int, Set<Formatter>> {
        val updatedMap = map
            .mapKeys {
                if (it.key in keys) {
                    it.key + shiftAmount
                } else {
                    it.key
                }
            }
            .filterKeys { it >= 0 }
        return TreeMap(updatedMap)
    }

}

@Preview
@Composable
private fun Test() {
    val map = TreeMap<Int, Set<Formatter>>()
    map[1] = setOf(Formatters.Bold, Formatters.Italic)
    Log.i(
        TreeMapUtilsImp.TAG,
        "${TreeMapUtilsImp(map).shiftKey(listOf(4,2),-2)}"
    )

}












