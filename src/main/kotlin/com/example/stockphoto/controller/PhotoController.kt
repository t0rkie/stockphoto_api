package com.example.stockphoto.controller

import com.example.stockphoto.model.Photo
import com.example.stockphoto.repository.PhotoRepository
import com.example.stockphoto.service.photoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/photo")
class PhotoController(private val photoRepository: PhotoRepository) {

    // 写真一覧
    @GetMapping("/index")
    fun getAllPhoto(): List<Photo> = photoRepository.findAll()

    // 写真1件
    @GetMapping("/show/{id}")
    fun getPhotoById(@PathVariable(value = "id") photoId: Long): ResponseEntity<Photo> {
        return photoRepository.findById(photoId).map { photo ->
            ResponseEntity.ok(photo)
        }.orElse(ResponseEntity.notFound().build())
    }

    // 新規登録
    @PostMapping("/store")
    fun createNewPhoto(@Valid @RequestBody photo: Photo): Photo {

        photoService().test(photo.imagePath)

        return photoRepository.save(photo)
    }

    // 更新
    @PutMapping("/update/{id}")
    fun updatePhotoById(@PathVariable(value = "id") photoId: Long,
                        @Valid @RequestBody newPhoto: Photo): ResponseEntity<Photo> {
        return photoRepository.findById(photoId).map { existingPhoto ->
            val updatePhoto: Photo = existingPhoto.copy(
                imagePath = newPhoto.imagePath,
                photoPrice = newPhoto.photoPrice,
                description = newPhoto.description
            )
            ResponseEntity.ok().body(photoRepository.save(updatePhoto))
        }.orElse(ResponseEntity.notFound().build())
    }

    // 削除
    @DeleteMapping("/delete/{id}")
    fun deletePhotoById(@PathVariable(value = "id") photoId: Long): ResponseEntity<Void> {
        return photoRepository.findById(photoId).map { photo ->
            photoRepository.delete(photo)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())
    }
}