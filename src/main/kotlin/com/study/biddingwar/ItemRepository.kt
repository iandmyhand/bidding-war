package com.study.biddingwar

import org.springframework.data.repository.CrudRepository

interface ItemRepository : CrudRepository<Item, Long> // <entity type, primary key type>