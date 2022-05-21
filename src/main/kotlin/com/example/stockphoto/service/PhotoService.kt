package com.example.stockphoto.service

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileOutputStream
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

@Service
class PhotoService {

//    val dir = "src/main/resources/static/img"
    val dir = "images"
    fun saveImage(image: MultipartFile): String {
        val name = UUID.randomUUID().toString()
        val ext = when (image.contentType) {
            "image/png" -> "png"
            "image/jpeg" -> "jpg"
            else -> return ResponseEntity.badRequest().body(
                "content type '${image.contentType}' is invalid."
            ).toString()
        }
        val filePath = "${dir}/${name}.${ext}"
        FileOutputStream(filePath).write(image.bytes)

        return filePath
    }

    fun getImage(imagePath: String): ByteArray {
//        println("imagePath: ${imagePath}")

        val path = File(".").absoluteFile.parent
//        println("path: ${path}")

        val imageFullPath = "${path}/${imagePath}"
        println(imageFullPath)
        val imagePath = Paths.get(imageFullPath)
        val image = Files.readAllBytes(imagePath)

        return image
    }
}