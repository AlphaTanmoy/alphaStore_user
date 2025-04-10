package com.alphaStore.user.service

import com.alphaStore.user.contract.aggregator.UserCartAggregator
import com.alphaStore.user.contract.aggregator.UserRepoAggregator
import com.alphaStore.user.contract.aggregator.UserWishListAggregator
import com.alphaStore.user.contract.repo.EncodingUtilContract
import com.alphaStore.user.contract.repo.EncryptionMasterContract
import com.alphaStore.user.entity.User
import com.alphaStore.user.entity.UserCart
import com.alphaStore.user.entity.UserWishList
import com.alphaStore.user.error.BadRequestException
import com.alphaStore.user.error.UnAuthorizedException
import com.alphaStore.user.model.GetProfile
import com.alphaStore.user.model.LoginWithEmailRequest
import com.alphaStore.user.model.PaginationResponse
import com.alphaStore.user.model.ProductResponse
import com.alphaStore.user.utils.DateUtil
import com.alphaStore.user.utils.KeywordsAndConstants.EMAIL_REGEX
import com.alphaStore.user.utils.password.PasswordEncoderMaster
import feign.FeignException
import org.springframework.stereotype.Component

@Component
class UserService (
    private val userRepoAggregator: UserRepoAggregator,
    private val userCartAggregator: UserCartAggregator,
    private val userWishListAggregator: UserWishListAggregator,
    private val encodingUtilContract: EncodingUtilContract,
    private val encryptionMaster: EncryptionMasterContract,
    private val dateUtilContract: DateUtil,
    private val passwordEncoderMaster: PasswordEncoderMaster
) {

    fun loginUsingEmailId(emailId: String, password: String, loginWithEmailRequest: LoginWithEmailRequest): User {
        if (!emailId.matches(Regex(EMAIL_REGEX))) {
            throw BadRequestException("Please enter a valid email id")
        }
        val user =
            userRepoAggregator.findByEmailIdAndDataStatus(
                emailId,
            )
        if (user.isNotEmpty()) {
            val findUser = user[0]
            if (passwordEncoderMaster.matches(password, findUser.password))
                return findUser
        }
        throw UnAuthorizedException()
    }


    fun getUserByEmailId(emailId: String): User? {
        val user = userRepoAggregator.findByEmailIdAndDataStatus(emailId)
        if (user.isEmpty())
            throw BadRequestException("User does not exists")
        return user[0]
    }

    fun findUserById(id: String): User {
        val user = userRepoAggregator.findUserById(id)
        if (user.isEmpty())
            throw BadRequestException("User does not exists")
        return user[0]
    }

    /*fun viewProduct(): PaginationResponse<ProductResponse> {
        try {
            return productClient.showAllProducts()
        } catch (e: FeignException) {
            throw RuntimeException("Error fetching products. ${e.message}")
        }
    }*/

    fun createUser(user: User): User {
        val savedUserId = user.id

        val cart = UserCart()
        val wishList = UserWishList()

        cart.userId = savedUserId
        wishList.userId = savedUserId

        userCartAggregator.save(cart)
        userWishListAggregator.save(wishList)

        user.userCartId = cart.id
        user.wishListId = wishList.id

        val userToSave = userRepoAggregator.save(user)
        return userToSave
    }

    fun convertUserToGetProfile(user: User): GetProfile {
        return GetProfile(
            userId = user.id,
            userName = user.name,
            userEmail = user.emailId,
            userPhone = user.phone,
            userAddress = user.address,
            userCountry = user.country,
            userCreationDate = user.createdDate
        )
    }

}