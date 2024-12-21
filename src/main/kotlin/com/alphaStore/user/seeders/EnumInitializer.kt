package com.alphaStore.user.seeders

import com.alphaStore.user.contract.enumRepo.*
import com.alphaStore.user.entity.enumsEntity.*
import com.alphaStore.user.enums.*
import org.springframework.stereotype.Component

@Component
class EnumInitializer(
    private val dataStatusRepository: DataStatusRepository,
    private val accessRoleRepository: AccessRoleRepository,
    private val httpMethodRepository: HttpMethodRepository,
    private val otpRequiredForRepository: OtpRequiredForRepository,
    private val otpVerificationResultRepository: OtpVerificationResultRepository,
    private val productMainCategoryRepository: ProductMainCategoryRepository,
    private val productSubCategoryRepository: ProductSubCategoryRepository,
    private val userTypeRepository: UserTypeRepository
) {
    fun insertAccessRole(){
        println("Seeding Started for AccessRole")
        val dataStatusEntities = AccessRole.values().map { enumValue ->
            AccessRoleEntity(
                value = enumValue,
                nameDescriptor = enumValue.nameDescriptor
            )
        }
        val toInsert: ArrayList<AccessRoleEntity> = ArrayList()
        dataStatusEntities.forEach{ entry->
            var shouldInsert = false
            val resultOnBasisOfEnumValue = accessRoleRepository.findByValue(
                entry.value.toString()
            )
            if(resultOnBasisOfEnumValue.isEmpty()) shouldInsert = true
            if (shouldInsert) {
                toInsert.add(entry)
            }
        }
        accessRoleRepository.saveAll(toInsert)
        println("Seeding Completed for AccessRole")
    }

    fun insertDataStatus(){
        println("Seeding Started for DataStatus")
        val dataStatusEntities = DataStatus.values().map { enumValue ->
            DataStatusEntity(
                value = enumValue,
                nameDescriptor = enumValue.nameDescriptor
            )
        }
        val toInsert: ArrayList<DataStatusEntity> = ArrayList()
        dataStatusEntities.forEach{ entry->
            var shouldInsert = false
            val resultOnBasisOfEnumValue = dataStatusRepository.findByValue(
                entry.value.toString()
            )
            if(resultOnBasisOfEnumValue.isEmpty()) shouldInsert = true
            if (shouldInsert) {
                toInsert.add(entry)
            }
        }
        dataStatusRepository.saveAll(toInsert)
        println("Seeding Completed for DataStatus")
    }

    fun insertHttpMethod(){
        println("Seeding Started for HttpMethod")
        val dataStatusEntities = HttpMethod.values().map { enumValue ->
            HttpMethodEntity(
                value = enumValue,
                nameDescriptor = enumValue.nameDescriptor
            )
        }
        val toInsert: ArrayList<HttpMethodEntity> = ArrayList()
        dataStatusEntities.forEach{ entry->
            var shouldInsert = false
            val resultOnBasisOfEnumValue = httpMethodRepository.findByValue(
                entry.value.toString()
            )
            if(resultOnBasisOfEnumValue.isEmpty()) shouldInsert = true
            if (shouldInsert) {
                toInsert.add(entry)
            }
        }
        httpMethodRepository.saveAll(toInsert)
        println("Seeding Completed for HttpMethod")
    }

    fun insertOtpRequiredFor(){
        println("Seeding Started for OtpRequiredFor")
        val dataStatusEntities = OtpRequiredFor.values().map { enumValue ->
            OtpRequiredForEntity(
                value = enumValue,
                nameDescriptor = enumValue.nameDescriptor
            )
        }
        val toInsert: ArrayList<OtpRequiredForEntity> = ArrayList()
        dataStatusEntities.forEach{ entry->
            var shouldInsert = false
            val resultOnBasisOfEnumValue = otpRequiredForRepository.findByValue(
                entry.value.toString()
            )
            if(resultOnBasisOfEnumValue.isEmpty()) shouldInsert = true
            if (shouldInsert) {
                toInsert.add(entry)
            }
        }
        otpRequiredForRepository.saveAll(toInsert)
        println("Seeding Completed for OtpRequiredFor")
    }

    fun insertOtpVerificationResult(){
        println("Seeding Started for OtpRequiredFor")
        val dataStatusEntities = OtpVerificationResult.values().map { enumValue ->
            OtpVerificationResultEntity(
                value = enumValue,
                nameDescriptor = enumValue.nameDescriptor
            )
        }
        val toInsert: ArrayList<OtpVerificationResultEntity> = ArrayList()
        dataStatusEntities.forEach{ entry->
            var shouldInsert = false
            val resultOnBasisOfEnumValue = otpVerificationResultRepository.findByValue(
                entry.value.toString()
            )
            if(resultOnBasisOfEnumValue.isEmpty()) shouldInsert = true
            if (shouldInsert) {
                toInsert.add(entry)
            }
        }
        otpVerificationResultRepository.saveAll(toInsert)
        println("Seeding Completed for OtpRequiredFor")
    }

    fun insertProductMainCategory(){
        println("Seeding Started for ProductMainCategory")
        val dataStatusEntities = ProductMainCategory.values().map { enumValue ->
            ProductMainCategoryEntity(
                value = enumValue,
                nameDescriptor = enumValue.nameDescriptor
            )
        }
        val toInsert: ArrayList<ProductMainCategoryEntity> = ArrayList()
        dataStatusEntities.forEach{ entry->
            var shouldInsert = false
            val resultOnBasisOfEnumValue = productMainCategoryRepository.findByValue(
                entry.value.toString()
            )
            if(resultOnBasisOfEnumValue.isEmpty()) shouldInsert = true
            if (shouldInsert) {
                toInsert.add(entry)
            }
        }
        productMainCategoryRepository.saveAll(toInsert)
        println("Seeding Completed for ProductMainCategory")
    }

    fun insertProductSubCategory(){
        println("Seeding Started for ProductSubCategory")
        val dataStatusEntities = ProductSubCategory.values().map { enumValue ->
            ProductSubCategoryEntity(
                value = enumValue,
                nameDescriptor = enumValue.nameDescriptor
            )
        }
        val toInsert: ArrayList<ProductSubCategoryEntity> = ArrayList()
        dataStatusEntities.forEach{ entry->
            var shouldInsert = false
            val resultOnBasisOfEnumValue = productSubCategoryRepository.findByValue(
                entry.value.toString()
            )
            if(resultOnBasisOfEnumValue.isEmpty()) shouldInsert = true
            if (shouldInsert) {
                toInsert.add(entry)
            }
        }
        productSubCategoryRepository.saveAll(toInsert)
        println("Seeding Completed for ProductSubCategory")
    }

    fun insertUserType(){
        println("Seeding Started for UserType")
        val dataStatusEntities = UserType.values().map { enumValue ->
            UserTypeEntity(
                value = enumValue,
                nameDescriptor = enumValue.nameDescriptor
            )
        }
        val toInsert: ArrayList<UserTypeEntity> = ArrayList()
        dataStatusEntities.forEach{ entry->
            var shouldInsert = false
            val resultOnBasisOfEnumValue = userTypeRepository.findByValue(
                entry.value.toString()
            )
            if(resultOnBasisOfEnumValue.isEmpty()) shouldInsert = true
            if (shouldInsert) {
                toInsert.add(entry)
            }
        }
        userTypeRepository.saveAll(toInsert)
        println("Seeding Completed for UserType")
    }

}