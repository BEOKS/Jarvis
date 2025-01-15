

import com.beoks.jarvis.TextGenerator

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*

import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.*
import kotlinx.serialization.json.*

/**
 * ProxyOpenAiTextGenerator is a class that generates text using the OpenAI API.
 * It uses a proxy server to bypass the OpenAI API's rate limiting.
 * It can be used only in Gabia office network.
 */
class GabiaProxyOpenAiTextGenerator : TextGenerator {

    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(
                Json{
                    ignoreUnknownKeys = true
                }
            )
        }
    }
    private val url = "https://dev-openai-proxy.gabia.app/v1/chat/completions"

    @Serializable
    data class Message(
        val role: String,
        val content: String
    )

    @Serializable
    data class ChatRequest(
        val model: String,
        val messages: List<Message>
    )

    @Serializable
    data class ChatResponse(
        val choices: List<Choice>
    )

    @Serializable
    data class Choice(
        val index: Int,
        val message: MessageContent,
        @SerialName("finish_reason") val finishReason: String? = null,
    )

    @Serializable
    data class MessageContent(
        val role: String,
        val content: String,
        val refusal: String? = null
    )

    override suspend fun generate(ask: String): String {

        val chatResponse: ChatResponse = client.post(url) {
            contentType(ContentType.Application.Json)
            setBody(
                ChatRequest(
                    model = "gpt-4",
                    messages = listOf(
                        Message(
                            role = "system",
                            content = "You are a helpful assistant."
                        ),
                        Message(
                            role = "user",
                            content = ask
                        )
                    )
                )
            )
        }.body()

        return chatResponse.choices.firstOrNull()?.message?.content ?: ""
    }
}




