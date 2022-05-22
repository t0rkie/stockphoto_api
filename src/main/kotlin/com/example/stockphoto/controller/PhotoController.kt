package com.example.stockphoto.controller

import com.example.stockphoto.model.Photo
import com.example.stockphoto.model.PhotoRequest
import com.example.stockphoto.model.PhotoResponse
import com.example.stockphoto.repository.PhotoRepository
import com.example.stockphoto.service.PhotoService
import org.springframework.data.jpa.repository.Query
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.io.File
import javax.validation.Valid

@RestController
@RequestMapping("/api/photo")
@CrossOrigin
class PhotoController(private val photoRepository: PhotoRepository) {

    // 写真一覧
    @GetMapping("/index")
    fun getAllPhoto(): List<PhotoResponse> {
        val photoList =  photoRepository.findAll()
        val photoResponse = photoList.map { photo ->
            val image = PhotoService().getImage(photo.imagePath)
            PhotoResponse(
                image = image
            )
        }
        return photoResponse
    }

    // 写真1件
    @GetMapping("/show/{id}")
    fun getPhotoById(@PathVariable(value = "id") photoId: Long): ResponseEntity<PhotoResponse> {
//        val photo = photoRepository.findById(photoId).map { photo ->
//            ResponseEntity.ok(photo)
//        }.orElse(ResponseEntity.notFound().build())

        val photo = photoRepository.findById(photoId)
        val image = PhotoService().getImage(photo.get().imagePath)
        val photoResponse = PhotoResponse(
            image = image
        )
        return ResponseEntity.ok(photoResponse)
    }

    // 新規登録
    @PostMapping("/store")
    fun createNewPhoto(@Valid @ModelAttribute photoRequest: PhotoRequest): Photo {

        val imagePath = PhotoService().saveImage(photoRequest.image)
        val photo = Photo(
            id = 0,
            imagePath = imagePath,
            photoPrice = photoRequest.photoPrice,
            description = photoRequest.description
        )

        return photoRepository.save(photo)
    }

    // 更新
    @PutMapping("/update/{id}")
    fun updatePhotoById(@PathVariable(value = "id") photoId: Long,
                        @Valid @RequestBody newPhoto: Photo
    ): ResponseEntity<Photo> {
        return photoRepository.findById(photoId).map { existingPhoto ->
            val updatePhoto: Photo = existingPhoto.copy(
                imagePath = newPhoto.imagePath,
                photoPrice = newPhoto.photoPrice,
                description = newPhoto.description
            )
            ResponseEntity.ok().body(photoRepository.save(updatePhoto))
        }.orElse(ResponseEntity.notFound().build())
    }

    // 全て削除
    @DeleteMapping("/delete_all")
    fun deleteAllPhoto() {
        println("delete_all: called")
        photoRepository.deleteAll()
        resetAutoIncrement()

        val path = File(".").absoluteFile.parent
        val imagePath = "${path}/images/"
        println("imagePath: $imagePath")

        val f = File(imagePath)
        f.listFiles().forEach { file ->  file.delete() }

    }

    // 削除
    @DeleteMapping("/delete/{id}")
    fun deletePhotoById(@PathVariable(value = "id") photoId: Long): ResponseEntity<Void> {
        return photoRepository.findById(photoId).map { photo ->
            photoRepository.delete(photo)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())
    }

    @Query(value = "ALTER TABLE photo AUTO_INCREMENT = 1;", nativeQuery = true)
    fun resetAutoIncrement() {
    }
}