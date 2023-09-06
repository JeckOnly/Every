package com.sjy.every

import org.junit.Test

import org.junit.Assert.*
import java.util.concurrent.ConcurrentHashMap
import kotlin.concurrent.thread

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun just_test() {
        val list = mutableListOf<Int>(0, 1, 2)
        val list1: List<Int> = list
        list.add(3)
        println("li st: ${list.toString()}")
        println("list1: ${list1.toString()}")
        println(list1 == list)
        println(list1 === list)

        val list3 = mutableListOf(1,2,3)
        val list4 = mutableListOf(1,2,3)
        println(list3 == list4)
        println(list3 === list4)
    }
}