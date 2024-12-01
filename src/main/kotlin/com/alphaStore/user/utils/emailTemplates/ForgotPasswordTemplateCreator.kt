package com.alphaStore.user.utils.emailTemplates

import com.alphaStore.user.contract.TemplateCreatorMaster

class ForgotPasswordTemplateCreator() : TemplateCreatorMaster {
    override fun getHeading(): String {
        return "Forgot Password"
    }

    override fun getGreetingMessage(): String {
        return "Hi"
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

    override fun getMessageBlockOne(): String {
        return "Your password has been reset."
    }

    override fun getHyperLinkTextOne(): String {
        return ""
    }

    override fun getHyperLinkValueOne(): String {
        return ""
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