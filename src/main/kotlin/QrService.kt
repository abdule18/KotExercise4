package com.example

import com.google.zxing.BarcodeFormat
import com.google.zxing.client.j2se.MatrixToImageWriter
import com.google.zxing.qrcode.QRCodeWriter
import java.io.ByteArrayOutputStream
import java.nio.file.Paths

fun saveQRCode(content: String, fileName: String) {
    val bitMatrix = QRCodeWriter().encode(
        content,
        BarcodeFormat.QR_CODE,
        300,
        300
    )

    val path = Paths.get(fileName)

    MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path)
}

fun generateQRCodeStream(content: String): ByteArrayOutputStream {
    val bitMatrix = QRCodeWriter().encode(
        content,
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

    return outputStream
}