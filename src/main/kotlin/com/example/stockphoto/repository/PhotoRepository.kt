package com.example.stockphoto.repository

import com.example.stockphoto.model.Photo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PhotoRepository: JpaRepository<Photo, Long>
