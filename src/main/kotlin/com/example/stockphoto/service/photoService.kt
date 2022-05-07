package com.example.stockphoto.service

import com.example.stockphoto.model.Photo
import org.springframework.stereotype.Service

@Service
class photoService {
    fun test(imagePath: String) {
        println("imagePath: ${imagePath}")
    }
}