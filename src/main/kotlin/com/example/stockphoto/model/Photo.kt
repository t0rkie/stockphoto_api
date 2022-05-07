package com.example.stockphoto.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
data class Photo (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    // 画像ファイルパス
    @get: NotBlank
    var imagePath: String,

    // 料金
    @get: NotNull
    var photoPrice: Int,

    // 説明
    var description: String,
)