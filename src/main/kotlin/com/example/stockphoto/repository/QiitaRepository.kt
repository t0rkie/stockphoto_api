package com.example.stockphoto.repository

import com.example.stockphoto.model.Qiita
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface QiitaRepository: JpaRepository<Qiita, Long>