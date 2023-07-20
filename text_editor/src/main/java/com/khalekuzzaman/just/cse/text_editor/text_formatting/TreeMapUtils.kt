package com.khalekuzzaman.just.cse.text_editor.text_formatting

import java.util.TreeMap


interface TreeMapUtils {
    fun add(key: Int, set: Set<Formatter>): TreeMap<Int, Set<Formatter>>
    fun add(keys: List<Int>, formatter: Formatter): TreeMap<Int, Set<Formatter>>
    fun add(key: Int, formatter: Formatter): TreeMap<Int, Set<Formatter>>
    fun remove(key: Int): TreeMap<Int, Set<Formatter>>
    fun get(key: Int): Set<Formatter>
    fun remove(key: Int, formatter: Formatter): TreeMap<Int, Set<Formatter>>
    fun shiftKey(shiftAmount: Int, predicate: (key: Int) -> Boolean): TreeMap<Int, Set<Formatter>>
    fun doesExits(key: Int): Boolean
    fun doesNotExits(key: Int): Boolean
    fun getPreviousOf(key: Int): Set<Formatter>
    fun getNextOf(key: Int): Set<Formatter>
    fun areNeighborsEqual(key: Int): Boolean

    fun getNeighbourCommons(key: Int): Set<Formatter>
    fun hasNeighbourCommon(key: Int): Boolean
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

    override fun add(keys: List<Int>, formatter: Formatter): TreeMap<Int, Set<Formatter>> {
        val newMap = map
        keys.forEach { key ->
            if (key < 0) {
                throw IllegalArgumentException("add():Negative key not allowed($key)")
            }
            newMap[key] = get(key).plus(formatter)
        }
        return newMap
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
        if (set.isEmpty())
            remove(key)
        else
            updatedMap[key] = set
        return updatedMap
    }

    fun remove(keys: List<Int>, formatter: Formatter): TreeMap<Int, Set<Formatter>> {
        val updatedMap = TreeMap(map)
        keys.forEach { key ->
            if (map.containsKey(key)) {
                val set = get(key).filter { it != formatter }.toSet()
                if (set.isEmpty())
                    remove(key)
                else
                    updatedMap[key] = set
            }
        }
        return updatedMap
    }

    fun remove(keys: List<Int>): TreeMap<Int, Set<Formatter>> {
        val updatedMap =TreeMap(map)
        keys.forEach { key ->
            updatedMap.remove(key)
        }
        return updatedMap
    }

    override fun get(key: Int): Set<Formatter> {
        return map[key] ?: emptySet()
    }

    override fun shiftKey(
        shiftAmount: Int,
        predicate: (key: Int) -> Boolean,
    ): TreeMap<Int, Set<Formatter>> {

        val updatedMap = map
            .mapKeys {
                if (predicate(it.key)) {
                    it.key + shiftAmount
                } else {
                    it.key
                }
            }
            .filterKeys { it >= 0 }


        return TreeMap(updatedMap)
    }

    override fun doesExits(key: Int) = map.containsKey(key)
    override fun doesNotExits(key: Int) = !doesExits(key)
    fun doesExits(key: Int, formatter: Formatter) = get(key).contains(formatter)

    override fun getPreviousOf(key: Int) = get(key - 1)

    override fun getNextOf(key: Int) = get(key + 1)

    override fun areNeighborsEqual(key: Int): Boolean {
        /*
        in case neighbour the key may be  is not inserted yet so
        it is not map yet.
        so does not need to check if the key is exits or not
        but if it any one of the neighbour not exits then return false
         */
        if (doesNotExits(key - 1) || doesExits(key + 1))
            return false
        return getNextOf(key) == getPreviousOf(key)
    }

    override fun getNeighbourCommons(key: Int) = getPreviousOf(key).intersect(getNextOf(key))
    override fun hasNeighbourCommon(key: Int) = getNeighbourCommons(key) != emptySet<Formatter>()

}













