package com.study.biddingwar

import org.springframework.data.jpa.repository.JpaRepository

interface ItemRepository : JpaRepository<Item, Long> // <entity type, primary key type>
