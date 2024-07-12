package com.example.thaavarm

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class ImageCompressor {
    fun resizeAndCompressImage(filePath: String, maxSize: Int, quality: Int): File {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeFile(filePath, options)

        var width = options.outWidth
        var height = options.outHeight
        var scale = 1

        if (width > height) {
            if (width > maxSize) {
                scale = width / maxSize
            }
        } else {
            if (height > maxSize) {
                scale = height / maxSize
            }
        }

        options.inJustDecodeBounds = false
        options.inSampleSize = scale

        val resizedBitmap = BitmapFactory.decodeFile(filePath, options)

        val compressedFile = File(filePath.replace(".jpg", "_compressed.jpg"))
        var outputStream: FileOutputStream? = null
        try {
            outputStream = FileOutputStream(compressedFile)
            resizedBitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)
        } finally {
            outputStream?.close()
        }

        return compressedFile
    }
}
