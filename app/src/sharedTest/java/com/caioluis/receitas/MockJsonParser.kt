package com.caioluis.receitas

import java.io.File
import java.util.*

class MockJsonParser {

    fun readFile(fileName: String): String {

        val result = StringBuilder()

        val classLoader = MockJsonParser::class.java.classLoader
        val file = File(classLoader!!.getResource(fileName).file)

        val scanner = Scanner(file)

        while (scanner.hasNextLine()) {
            val line = scanner.nextLine()
            result.append(line).append("\n")
        }

        scanner.close()

        return result.toString()
    }
}