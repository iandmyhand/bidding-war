package com.example.bidding_war

import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.jdbc.core.JdbcTemplate

@SpringBootApplication
class BiddingWarApplication

fun main(args: Array<String>) {
    runApplication<BiddingWarApplication>(*args)
}


@SpringBootApplication
class ApplyProduct(
    val jdbcTemplate: JdbcTemplate
): CommandLineRunner {

    override fun run(vararg args: String?) {
        val log = LoggerFactory.getLogger(this.javaClass)

        log.info("Creating tables");
        jdbcTemplate.execute("DROP TABLE product IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE product(" + "product_id SERIAL, product_name VARCHAR(255), description VARCHAR(255))");

        val splitUpNames = listOf<String>("1 BigMac best", "2 SanghaiChicken normal", "3 MacNugget worst")
            .map{ it.split(" ").toTypedArray()}

        splitUpNames.forEach { log.info("Inserting product record for ${it[0]} / ${it[1]} / ${it[2]}") }

        jdbcTemplate.batchUpdate("INSERT INTO product(product_id, product_name, description) VALUES (?, ?,?)", splitUpNames);

        log.info("Querying for customer records where product = 'BigMac':");

        jdbcTemplate.query("SELECT product_id, product_name, description FROM product") {
                rs, rowNum -> println("${rs.getLong("product_id")}, ${rs.getString("product_name")}, ${rs.getString("description")} $rowNum")
        }
    }
}
