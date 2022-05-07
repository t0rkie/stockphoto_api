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
    val id: Long = 0,

    // 画像ファイルパス
    @get: NotBlank
    val imagePath: String = "",

    // 料金
    @get: NotNull
    val photoPrice: Int = 0,

    // 説明
    val description: String = "",
)