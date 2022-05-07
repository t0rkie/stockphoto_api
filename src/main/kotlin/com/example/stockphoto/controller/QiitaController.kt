package com.example.stockphoto.controller

import com.example.stockphoto.model.Qiita
import com.example.stockphoto.repository.QiitaRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class QiitaController(private val qiitaRepository: QiitaRepository) {

    @GetMapping("/qiitas")
    fun getAllQiitas(): List<Qiita> =
        qiitaRepository.findAll()

    @PostMapping("/qiitas")
    fun createNewQiita(@Valid @RequestBody qiita: Qiita): Qiita =
        qiitaRepository.save(qiita)

    @GetMapping("/qiitas/{id}")
    fun getQiitaBiId(@PathVariable(value = "id") qiitaed: Long): ResponseEntity<Qiita> {
        return qiitaRepository.findById(qiitaed).map { qiita ->
            ResponseEntity.ok(qiita)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PutMapping("/qiitas/{id}")
    fun updateQiitaById(@PathVariable(value = "id") qiitaed: Long,
                        @Valid @RequestBody newQiita: Qiita): ResponseEntity<Qiita> {

        return qiitaRepository.findById(qiitaed).map { existingQiita ->
            val updateQiita: Qiita = existingQiita
                .copy(title = newQiita.title, content = newQiita.content)
            ResponseEntity.ok().body(qiitaRepository.save(updateQiita))
        }.orElse(ResponseEntity.notFound().build())

    }

    @DeleteMapping("/qiitas/{id}")
    fun deleteQiitaById(@PathVariable(value = "id") qiitaed: Long): ResponseEntity<Void> {

        return qiitaRepository.findById(qiitaed).map { qiita ->
            qiitaRepository.delete(qiita)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())
    }
}