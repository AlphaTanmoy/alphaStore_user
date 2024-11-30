package com.alphaStore.user.error

import com.alphaStore.user.enums.ResponseType
import com.alphaStore.user.reqres.GenericResponse
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
@RestController
class CustomResponseEntityHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(GenericException::class)
    fun handleGenericException(ex: GenericException, webRequest: WebRequest): ResponseEntity<Any> {
        return ResponseEntity(
            GenericResponse(
                code = ex.code,
                message = if (ex.errorMessage != "") ex.errorMessage else "bad request",
                responseType = ResponseType.FAIL
            ),
            HttpStatus.valueOf(ex.code)
        )
    }



    @ExceptionHandler(BadRequestException::class)
    fun handleBadRequestException(ex: BadRequestException, webRequest: WebRequest): ResponseEntity<Any> {
        ex.code?.let { code ->
            ex.type?.let { type ->
                return ResponseEntity(
                    GenericResponse(
                        code = code,
                        message = if (ex.errorMessage != "") ex.errorMessage else "bad request",
                        responseType = ResponseType.FAIL,
                        type = type
                    ),
                    HttpStatus.BAD_REQUEST
                )
            } ?: run {
                return ResponseEntity(
                    GenericResponse(
                        code = code,
                        message = if (ex.errorMessage != "") ex.errorMessage else "bad request",
                        responseType = ResponseType.FAIL
                    ),
                    HttpStatus.BAD_REQUEST
                )
            }
        }
        return ResponseEntity(
            GenericResponse(
                code = HttpStatus.BAD_REQUEST.value(),
                message = if (ex.errorMessage != "") ex.errorMessage else "bad request",
                responseType = ResponseType.FAIL
            ),
            HttpStatus.BAD_REQUEST
        )
    }

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any> {
        var errorMessage = "Bad Req"
        ex.bindingResult.fieldErrors.forEach {
            errorMessage = it.defaultMessage ?: "Bad Req"
        }
        return ResponseEntity(
            GenericResponse(
                code = HttpStatus.BAD_REQUEST.value(),
                message = errorMessage,
                responseType = ResponseType.FAIL
            ),
            HttpStatus.BAD_REQUEST
        )
    }

    override fun handleHttpMessageNotReadable(
        ex: HttpMessageNotReadableException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any> {
        val errorMessage = "Invalid arguments"
        return ResponseEntity(
            GenericResponse(
                code = HttpStatus.BAD_REQUEST.value(),
                message = errorMessage,
                responseType = ResponseType.FAIL
            ),
            HttpStatus.BAD_REQUEST
        )
    }

    override fun handleExceptionInternal(
        ex: java.lang.Exception,
        body: Any?,
        headers: HttpHeaders,
        statusCode: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any> {
        var errorMessage = "Invalid arguments"
        ex.cause?.let { cause ->
            cause.message?.let { message ->
                errorMessage = message
            }
        }
        return ResponseEntity(
            GenericResponse(
                code = HttpStatus.BAD_REQUEST.value(),
                message = errorMessage,
                responseType = ResponseType.FAIL
            ),
            HttpStatus.BAD_REQUEST
        )
    }

}