package com.alphaStore.user.utils.email

import com.alphaStore.user.contract.TemplateCreatorMaster
import com.alphaStore.user.enums.SendEmailFrom
import com.alphaStore.user.error.GenericException
import com.alphaStore.user.utils.KeywordsAndConstants
import com.alphaStore.user.utils.emailTemplates.*
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSenderImpl
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Component
import org.thymeleaf.TemplateEngine
import java.io.ByteArrayOutputStream
import java.io.File
import java.util.*
import org.thymeleaf.context.Context
import org.thymeleaf.spring6.SpringTemplateEngine
import org.thymeleaf.templatemode.TemplateMode
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver

@Component
class EmailMaster {


    fun sendMail(
        emails: ArrayList<String>,
        emailsForCc: ArrayList<String> = arrayListOf(),
        content: String = "Hi, there.",
        useTemplate: Boolean = true,
        templateCreatorMaster: TemplateCreatorMaster,
        filesToSend: ArrayList<File> = ArrayList(),
        sendEmailFrom: SendEmailFrom = SendEmailFrom.SUPPORT
    ) {
        try {
            val mailSender = JavaMailSenderImpl()
            mailSender.host = KeywordsAndConstants.SMTP_HOST
            mailSender.port = KeywordsAndConstants.SMTP_PORT
            when (sendEmailFrom) {
                SendEmailFrom.SUPPORT -> {
                    mailSender.username = KeywordsAndConstants.SMTP_USERNAME_SUPPORT
                    mailSender.password = KeywordsAndConstants.SMTP_PASSWORD_SUPPORT
                }

                SendEmailFrom.SYSTEM -> {
                    mailSender.username = KeywordsAndConstants.SMTP_USERNAME_SYSTEM
                    mailSender.password = KeywordsAndConstants.SMTP_PASSWORD_SYSTEM
                }

                SendEmailFrom.NOTIFICATIONS -> {
                    mailSender.username = KeywordsAndConstants.SMTP_USERNAME_NOTIFICATIONS
                    mailSender.password = KeywordsAndConstants.SMTP_PASSWORD_NOTIFICATIONS
                }
            }

            val props: Properties = mailSender.javaMailProperties
            props["mail.transport.protocol"] = "smtp"
            props["mail.smtp.auth"] = "true"
            props["mail.smtp.starttls.enable"] = "true"
            props["mail.debug"] = "false"
            props["mail.smtp.ssl.enable"] = "true"

            if (!useTemplate) {
                val simpleMailMessage = SimpleMailMessage()
                simpleMailMessage.apply {
                    setFrom(findFromEmail(sendEmailFrom))
                    setCc(*emailsForCc.toTypedArray())
                    setTo(*emails.toTypedArray())
                    setSubject(templateCreatorMaster.getHeading())
                    setText(content)
                    mailSender.send(simpleMailMessage)
                }
                return
            } else {
                val templateEngine = getTemplateEngine()
                val contentString = templateEngine.process(
                    findTemplateName(templateCreatorMaster),
                    findContext(templateCreatorMaster)
                )
                val outputStream = ByteArrayOutputStream()
                val mimeMessage = mailSender.createMimeMessage()
                val mimeMessageHelper = MimeMessageHelper(mimeMessage, true)
                val emailsList: Array<String> = Array(emails.size, init = { position -> emails[position] })
                val emailsForCcList: Array<String> =
                    Array(emailsForCc.size, init = { position -> emailsForCc[position] })
                mimeMessageHelper.apply {
                    setSubject(templateCreatorMaster.getHeading())
                    setFrom(findFromEmail(sendEmailFrom))
                    setCc(emailsForCcList)
                    setTo(emailsList)
                    setText(contentString, true)
                    filesToSend.forEach { file ->
                        addAttachment(file.name, file)
                    }

                    mailSender.send(mimeMessage)
                }
                return
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            if (
                ex.message?.contains("No recipient addresses") == true ||
                ex.message?.contains("Invalid Addresses") == true ||
                ex.message?.contains("Domain not found") == true
            ) else {
                throw GenericException("Failed to send email")
            }
        }
    }

    private fun getTemplateEngine(): TemplateEngine {
        val templateEngine = SpringTemplateEngine()
        val templateResolver = ClassLoaderTemplateResolver()
        templateResolver.prefix = "templates/"
        templateResolver.suffix = ".html"
        templateResolver.templateMode = TemplateMode.HTML
        templateResolver.characterEncoding = "UTF-8"
        templateResolver.order = 0
        templateEngine.setTemplateResolver(templateResolver)
        return templateEngine
    }

    private fun findContext(templateCreatorMaster: TemplateCreatorMaster): Context {
        val context = Context()
        when (templateCreatorMaster) {
            is PasswordResetDueToMultipleLoginFailsTemplateCreator,
            is ForgotPasswordTemplateCreator,
            is ChangePasswordTemplateCreator,
            is SendOtpTemplateCreator,
            is EmailForSystemAdminTemplateCreator,
            is NewMerchantCreatedTemplateCreator
                -> {
                context.setVariable("heading", templateCreatorMaster.getHeading())
                context.setVariable("greetingMessage", templateCreatorMaster.getGreetingMessage())
                context.setVariable("messageBlockOne", templateCreatorMaster.getMessageBlockOne())
                context.setVariable("messageBlockTwo", templateCreatorMaster.getMessageBlockTwo())
                context.setVariable("messageBlockThree", templateCreatorMaster.getMessageBlockThree())
                context.setVariable("messageBlockFour", templateCreatorMaster.getMessageBlockFour())
                context.setVariable("messageBlockFive", templateCreatorMaster.getMessageBlockFive())
                context.setVariable("messageBlockSix", templateCreatorMaster.getMessageBlockSix())
                context.setVariable("messageBlockSeven", templateCreatorMaster.getMessageBlockSeven())
                context.setVariable("messageBlockEight", templateCreatorMaster.getMessageBlockEight())
                context.setVariable("hyperLinkTextOne", templateCreatorMaster.getHyperLinkTextOne())
                context.setVariable("hyperLinkValueOne", templateCreatorMaster.getHyperLinkValueOne())
                context.setVariable("hyperLinkTextTwo", templateCreatorMaster.getHyperLinkTextTwo())
                context.setVariable("hyperLinkValueTwo", templateCreatorMaster.getHyperLinkValueTwo())
                context.setVariable("from", templateCreatorMaster.getFrom())
                context.setVariable("fromLineTwo", templateCreatorMaster.getFromLineTwo())
            }

            else -> {
                throw GenericException("Failed to send email.")
            }
        }
        return context
    }

    private fun findTemplateName(templateCreatorMaster: TemplateCreatorMaster): String {
        return when (templateCreatorMaster) {
            is PasswordResetDueToMultipleLoginFailsTemplateCreator,
            is ForgotPasswordTemplateCreator,
            is ChangePasswordTemplateCreator,
            is SendOtpTemplateCreator,
            is EmailForSystemAdminTemplateCreator,
            is NewMerchantCreatedTemplateCreator
                -> {
                "email-template"
            }

            else -> {
                "email-template"
            }
        }
    }

    private fun findFromEmail(sendEmailFrom: SendEmailFrom): String {
        return when (sendEmailFrom) {
            SendEmailFrom.SUPPORT -> KeywordsAndConstants.SMTP_FROM_EMAIL_SUPPORT
            SendEmailFrom.NOTIFICATIONS -> KeywordsAndConstants.SMTP_FROM_EMAIL_NOTIFICATIONS
            else -> KeywordsAndConstants.SMTP_FROM_EMAIL_SYSTEM
        }
    }
    
}