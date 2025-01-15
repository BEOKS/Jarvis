import com.beoks.jarvis.TextGenerator

import okhttp3.*
import java.io.IOException
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

/**
 * ProxyOpenAiTextGenerator is a class that generates text using the OpenAI API.
 * It uses a proxy server to bypass the OpenAI API's rate limiting.
 * It cans use only in gabia office network.
 */
class GabiaProxyOpenAiTextGenerator : TextGenerator {

    private val client = OkHttpClient()
    private val url = "https://dev-openai-proxy.gabia.app/v1/chat/completions"
    private val json = Json { ignoreUnknownKeys = true }

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
        val logprobs: JsonElement? = null
    )

    @Serializable
    data class MessageContent(
        val role: String,
        val content: String,
        val refusal: String? = null
    )

    override fun generate(ask: String): String {
        val requestBody = ChatRequest(
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

        val jsonString = json.encodeToString(requestBody)

        val body = jsonString.toRequestBody("application/json".toMediaTypeOrNull())

        val request = Request.Builder()
            .url(url)
            .post(body)
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")

            val responseData = response.body?.string() ?: ""
            val chatResponse = json.decodeFromString<ChatResponse>(responseData)

            return chatResponse.choices.firstOrNull()?.message?.content ?: ""
        }
    }
}



