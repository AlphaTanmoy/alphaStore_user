package com.alphaStore.user.utils.emailTemplates

import com.alphaStore.user.contract.TemplateCreatorMaster
import com.alphaStore.user.utils.KeywordsAndConstants.FRONT_END_URL

class NewMerchantCreatedTemplateCreator(
    private val name: String,
    private val password: String
) : TemplateCreatorMaster {
    override fun getHeading(): String {
        return "Account Created"
    }

    override fun getGreetingMessage(): String {
        return "Hi $name"
    }


    override fun getMessageBlockOne(): String {
        return """
            Your account is created at Link. Your password is : $password
        """.trimIndent()
    }

    override fun getMessageBlockTwo(): String {
        return ""
    }

    override fun getMessageBlockThree(): String {
        return ""
    }

    override fun getMessageBlockFour(): String {
        return ""
    }

    override fun getMessageBlockFive(): String {
        return ""
    }

    override fun getMessageBlockSix(): String {
        return ""
    }

    override fun getMessageBlockSeven(): String {
        return ""
    }

    override fun getMessageBlockEight(): String {
        return ""
    }

    override fun getHyperLinkTextOne(): String {
        return "Click here to login"
    }

    override fun getHyperLinkValueOne(): String {
        return FRONT_END_URL + "login/email"
    }

    override fun getHyperLinkTextTwo(): String {
        return ""
    }

    override fun getHyperLinkValueTwo(): String {
        return ""
    }

    override fun getFrom(): String {
        return "Link"
    }

    override fun getFromLineTwo(): String {
        return ""
    }

}