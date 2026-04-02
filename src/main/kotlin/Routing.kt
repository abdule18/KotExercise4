package com.example

import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.ByteArrayOutputStream
import com.google.zxing.BarcodeFormat
import com.google.zxing.client.j2se.MatrixToImageWriter
import com.google.zxing.qrcode.QRCodeWriter

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        get("/qr") {

            val text = call.request.queryParameters["text"]
                ?: return@get call.respondText("Missing text")

            val bitMatrix = QRCodeWriter().encode(
                text,
                BarcodeFormat.QR_CODE,
                300,
                300
            )

            val outputStream = ByteArrayOutputStream()

            MatrixToImageWriter.writeToStream(
                bitMatrix,
                "PNG",
                outputStream
            )

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
