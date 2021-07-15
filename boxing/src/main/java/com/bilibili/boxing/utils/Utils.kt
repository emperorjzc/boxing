package com.bilibili.boxing.utils

import android.content.Context
import android.net.Uri
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

object Utils {

    fun saveMediaImageFile(context: Context, uri: Uri): File? {
        val imgFile: File? = context.applicationContext.getExternalFilesDir("image")
        if (imgFile == null || !imgFile.exists()) {
            imgFile?.mkdir()
        }
        try {
            val file = File(
                imgFile!!.absolutePath + File.separator +
                        System.currentTimeMillis().toString() + ".jpg"
            )
            // 使用openInputStream(uri)方法获取字节输入流
            val fileInputStream: InputStream =
                context.applicationContext.contentResolver.openInputStream(uri) ?: return null
            val fileOutputStream = FileOutputStream(file)
            val buffer = ByteArray(1024)
            var byteRead: Int
            while (-1 != fileInputStream.read(buffer).also { byteRead = it }) {
                fileOutputStream.write(buffer, 0, byteRead)
            }
            fileInputStream.close()
            fileOutputStream.flush()
            fileOutputStream.close()
            // 文件可用新路径 file.getAbsolutePath()
            return file
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
}