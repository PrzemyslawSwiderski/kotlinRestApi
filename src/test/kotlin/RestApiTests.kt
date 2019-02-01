import com.pswiderski.kotlin.Application
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*


@WebMvcTest
@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [Application::class])
class RestApiTests(@Autowired val mockMvc: MockMvc) {

    @Value("\${spring.security.user.name}")
    lateinit var userName: String

    @Value("\${spring.security.user.password}")
    lateinit var userPassword: String

    @Test
    fun contextLoads() {
    }

    @Test
    fun `Assert home page requires login`() {
        val entity = mockMvc.perform(get("/")).andReturn()
        assertThat(entity.response.status).isEqualTo(HttpStatus.UNAUTHORIZED.value())
    }

    @Test
    fun `Assert home page returns swagger UI`() {
        mockMvc.perform(get("/")
                .with(httpBasic(userName, userPassword)))
                .andExpect(status().isFound)
                .andExpect(redirectedUrl("swagger-ui.html"))
    }

    @Nested
    @DisplayName("Tests for validate endpoint")
    internal inner class ValidateTests {

        @BeforeEach
        fun beforeEach() {
            println("Before each test method of the ValidateTests class")
        }

        @AfterEach
        fun afterEach() {
            println("After each test method of the ValidateTests class")
        }

        @Test
        @DisplayName("Example test for method A")
        fun `Assert validate response is correct`() {
            mockMvc.perform(get("/validate/")
                    .with(httpBasic(userName, userPassword))
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(authenticated().withUsername(userName))
                    .andExpect(status().isOk)
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andExpect(jsonPath("\$.message").value("testMsg"))
                    .andExpect(jsonPath("\$.person.name").value("World"))
                    .andExpect(jsonPath("\$.person.age").value(23))
        }
    }

}