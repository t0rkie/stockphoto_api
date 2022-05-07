package com.example.stockphoto.service

import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.FileOutputStream
import java.util.*


@Service
class PhotoService {

    val dir = "src/main/resources/static/img"
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
        println(imagePath)
        val relativeImagePath = imagePath.removePrefix("src/main/resources/")
        val image: ByteArray = this.javaClass.classLoader.getResource(relativeImagePath).readBytes()

//        val headers = HttpHeaders()
//        val contentType = "Content-Type"
//        val contentTypeValue = MediaType.IMAGE_JPEG.toString()
//
//        headers.set(contentType, contentTypeValue)

        return image
    }
}