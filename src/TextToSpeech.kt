package com.salesforce.trailhead

import io.ktor.application.*
import io.ktor.http.content.*
import io.ktor.locations.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.http.*

import com.amazonaws.services.polly.AmazonPollyClientBuilder
import com.amazonaws.services.polly.model.OutputFormat
import com.amazonaws.services.polly.model.SynthesizeSpeechRequest
import com.amazonaws.services.polly.model.VoiceId
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider
import com.amazonaws.regions.Regions
import java.io.File
import java.io.FileOutputStream

@Location("/textToSpeech/{name}")
data class TextSpeech(val name: String)

var client = AmazonPollyClientBuilder.standard().withRegion(Regions.US_WEST_2)
    .withCredentials(EnvironmentVariableCredentialsProvider()).build()

fun Route.textToSpeech() {
    get<TextSpeech> {
        val outputFileName = "/tmp/speech.mp3"
        val synthesizeSpeechRequest = SynthesizeSpeechRequest()
            .withOutputFormat(OutputFormat.Mp3)
            .withVoiceId(VoiceId.Joanna)
            .withText("This is a sample text to be synthesized for ${it.name}.")
        try {
            FileOutputStream(File(outputFileName)).use { outputStream ->
                val synthesizeSpeechResult = client.synthesizeSpeech(synthesizeSpeechRequest)
                val buffer = ByteArray(2 * 1024)
                var readBytes: Int
                synthesizeSpeechResult.audioStream.use { `in` ->
                    while (`in`.read(buffer).also { readBytes = it } > 0) {
                        outputStream.write(buffer, 0, readBytes)
                    }
                }
            }
        } catch (e: Exception) {
            System.err.println("Exception caught: $e")
        }
        val type = ContentType.fromFilePath(outputFileName).first { it.contentType == "audio" }
        call.respond(LocalFileContent(File(outputFileName), contentType = type))
    }
}