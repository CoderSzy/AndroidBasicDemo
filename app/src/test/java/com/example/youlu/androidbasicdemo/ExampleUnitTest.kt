package com.example.youlu.androidbasicdemo

import org.junit.Test

import org.junit.Assert.*

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
    fun removeEleFromList() {
        val datas = mutableListOf(1, 2, 3, 4, 5)
        val datasFilter = mutableListOf<Int>()
//        datas.forEach {
//            if (it % 2 == 0) {
//                datas.removeAt(it)
//            }
//        }
//        for (i in 0..datas.size) {
//            if (datas[i] % 2 == 0) {
//                datas.removeAt(i)
//            }
//        }
        datas.forEach {
            if (it % 2 == 0) {
                datasFilter.add(datas[it])
            }
        }
        datas.removeAll(datasFilter)
        println("size is ${datas.size}")
    }

    @Test
    fun addBitToString() {
        val dataList = mutableListOf(1, 2, 11, 5, 12)
//        dataList.forEach {
//            val element = "${dataList[it]}"
//            if (element.length < 2) {
//                StringBuilder(element).insert(0, "0")
//            }
//        }
        for (i in 0 until dataList.size) {
            val element = "${dataList[i]}"
            if (element.length < 2) {
                val insert = StringBuilder(element).insert(0, "0").toString()
                println(insert)
            }
        }
        println(dataList)
    }
}
