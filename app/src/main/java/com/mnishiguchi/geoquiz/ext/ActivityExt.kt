package com.mnishiguchi.geoquiz.ext

import android.app.Activity
import java.io.IOException
import java.io.InputStream

// Load a file from app/src/main/assets folder.
fun Activity.readAssetFile(fileName: String): String {
    try {
        val file: InputStream = assets.open(fileName)
        val json: String = file.reader().readText()
        return json
    } catch (e: IOException) {
        e.printStackTrace()
    }

    return ""
}