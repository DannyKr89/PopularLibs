package ru.dk.popularlibs

class CountersModel {

    private val counters = mutableListOf(0, 0, 0)

    fun getCounters() = counters
    fun getCounter(index: Int) = counters[index]

    fun increaseCounter(index: Int): Int {
        counters[index]++
        return counters[index]
    }

    fun setCounters(list: List<Int>) {
        counters.clear()
        counters.addAll(list)
    }
}