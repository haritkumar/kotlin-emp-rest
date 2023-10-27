package com.emp.entity

import jakarta.persistence.*
import java.io.Serializable
import java.time.LocalDateTime

@MappedSuperclass
open class BaseEntity : Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, insertable = false)
    var id: Long? = null

    @Column(name = "created_at", updatable = false)
    var createdAt: LocalDateTime? = null

    @PrePersist
    protected fun prePersist() {
        if (createdAt == null) createdAt = LocalDateTime.now()
    }
}