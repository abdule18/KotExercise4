package com.example

import io.ktor.http.HttpHeaders
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {

        get("/") {
            call.respondText("Hello World!")
        }

        get("/qr") {

            val text = call.request.queryParameters["text"]
                ?: return@get call.respondText("Missing text")

            val outputStream = generateQRCodeStream(text)

            call.response.header(
                HttpHeaders.ContentType,
                "image/png"
            )

            call.respondBytes(
                outputStream.toByteArray()
            )
        }
    }
}