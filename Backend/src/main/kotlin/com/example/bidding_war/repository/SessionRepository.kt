package com.example.bidding_war.repository


import com.example.bidding_war.model.Session
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface SessionRepository : JpaRepository<Session, Long> {
}