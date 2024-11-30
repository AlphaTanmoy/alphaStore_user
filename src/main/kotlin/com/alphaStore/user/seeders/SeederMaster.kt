package com.alphaStore.user.seeders

import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class SeederMaster(
    private val countrySeeder: CountrySeeder
) : CommandLineRunner {

    @Transactional
    override fun run(vararg args: String?) {
        println("Going for country seeding")
        countrySeeder.mayInsertCountryData()
        println("Done seeding for country")
    }

}