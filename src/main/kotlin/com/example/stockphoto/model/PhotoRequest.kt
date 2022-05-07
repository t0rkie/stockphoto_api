package com.example.stockphoto.model

import org.springframework.web.multipart.MultipartFile

data class PhotoRequest (
    // 画像
    val image: MultipartFile,

    // 料金
    val photoPrice: Int,

    // 説明
    val description: String,
)