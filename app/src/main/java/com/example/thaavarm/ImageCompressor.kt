package com.example.thaavarm

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.File
import java.io.FileOutputStream

//This code defines an ImageCompressor class with a function resizeAndCompressImage that resizes and compresses an image file to a specified maximum size and quality.
//It adjusts the image dimensions based on the larger side, scales it down, and saves the compressed image as a new file with "_compressed" added to the original file name.
//Hoping to allow images from gallery to be compressed and sent to API

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
