/**
 * MIT License
 *
 * Copyright (c) 2021-2022 TriumphTeam
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package dev.triumphteam.core.func

import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardCopyOption

/**
 * Utils taken from AuthMe/ConfigMe
 */
fun copyFileFromResources(path: String, temporaryFolder: Path): Path {
    return try {
        val source = getJarPath(path)
        val destination: Path = temporaryFolder.resolve(source.fileName)
        Files.createFile(destination)
        Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING)
        destination
    } catch (e: IOException) {
        throw IllegalStateException("Could not copy test file", e)
    }
}

fun getJarPath(path: String): Path {
    println(object : Any() {}.javaClass.getResourceAsStream("config.yml"))
    val filePath = object : Any() {}.javaClass.getResource(path).path
    // Windows prepends the path with a '/' or '\', which Paths cannot handle
    val appropriatePath = if (System.getProperty("os.name").contains("indow")) filePath.substring(1) else filePath

    return Paths.get(appropriatePath)
}
