package com.alphaStore.user.seeders

import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class SeederMaster(
    private val countrySeeder: CountrySeeder,
    private val enumInitializer: EnumInitializer
) : CommandLineRunner {

    @Transactional
    override fun run(vararg args: String?) {
        seedCountry()
        enumInitializer.insertDataStatus()
        enumInitializer.insertAccessRole()
        enumInitializer.insertHttpMethod()
        enumInitializer.insertOtpRequiredFor()
        enumInitializer.insertOtpVerificationResult()
        enumInitializer.insertProductMainCategory()
        enumInitializer.insertProductSubCategory()
        enumInitializer.insertUserType()
        println(">>>>>>>Seeding completed successfully")
    }


    private fun seedCountry(){
        println("Going for country seeding")
        countrySeeder.mayInsertCountryData()
        println("Done seeding for country")
    }
}