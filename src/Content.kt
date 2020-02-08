package com.salesforce.trailhead

import io.ktor.application.*
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.features.json.*
import io.ktor.client.request.get
import io.ktor.locations.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.http.*

import java.util.*

@Location("/content/{moduleUuid}/unit/{unitUuid}")
data class Content(val moduleUuid: String, val unitUuid: String)

data class ModuleUnit(val Id: UUID, val Body: String)

fun Route.content() {
    get<Content> {
        val client = HttpClient(CIO) {
            install(JsonFeature) {
                serializer = GsonSerializer() {
                    serializeNulls()
                    disableHtmlEscaping()
                }
            }
        }

        val uri = "https://trailhead.salesforce.com/services/odata/v1/Content/Trailhead.ModuleContent(${it.moduleUuid})/Units(${it.unitUuid})?\$select=Body"
        val unit = client.get<ModuleUnit>(uri) {
            contentType(ContentType.Application.Json)
        }

        val regex = "<(\"[^\"]*\"|'[^']*'|[^'\">])*>".toRegex()
        val returnUnit = ModuleUnit(unit.Id,
            unit.Body.replace(regex, "")
                .replace("\n", "").replace("\t",""))

        call.respond(returnUnit)
    }
}