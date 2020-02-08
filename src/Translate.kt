package com.salesforce.trailhead

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider
import com.amazonaws.regions.Regions
import com.amazonaws.services.translate.AmazonTranslateClient
import com.amazonaws.services.translate.model.TranslateTextRequest
import com.amazonaws.services.translate.model.TranslateTextResult
import io.ktor.application.call
import io.ktor.locations.Location
import io.ktor.locations.get
import io.ktor.response.respond
import io.ktor.routing.*


@Location("/translate/{inLocale}/{text}/{outLocale}")
data class Tranlate(val text: String, val inLocale: String, val outLocale: String)

var translateClient = AmazonTranslateClient.builder().withRegion(Regions.US_WEST_2)
    .withCredentials(EnvironmentVariableCredentialsProvider()).build()

fun Route.translate() {
    get<Tranlate> {
        val request = TranslateTextRequest()
            .withText(it.text)
            .withSourceLanguageCode(it.inLocale)
            .withTargetLanguageCode(it.outLocale)
        val result: TranslateTextResult = translateClient.translateText(request)

        call.respond(mapOf(
            "text" to result.translatedText,
            "from" to result.sourceLanguageCode,
            "to" to result.targetLanguageCode))
    }
}