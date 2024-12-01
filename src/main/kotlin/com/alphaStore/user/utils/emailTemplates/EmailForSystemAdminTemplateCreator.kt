package com.alphaStore.user.utils.emailTemplates

import com.alphaStore.user.contract.TemplateCreatorMaster


class EmailForSystemAdminTemplateCreator(
    private var messageBody: String,
    private var messageBodyTwo: String,
    private var subject: String,
    private var hyperlink:String = ""
): TemplateCreatorMaster {

    override fun getHeading(): String {
        return subject.trimIndent()
    }

    override fun getGreetingMessage(): String {
        return "Hi"
    }

    override fun getMessageBlockTwo(): String {
        return messageBodyTwo.trimIndent()
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
        return messageBody.trimIndent()
    }

    override fun getHyperLinkTextOne(): String {
        return "view"
    }

    override fun getHyperLinkValueOne(): String {
        return hyperlink
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