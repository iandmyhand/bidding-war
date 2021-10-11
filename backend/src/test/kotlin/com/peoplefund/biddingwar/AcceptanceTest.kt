import com.peoplefund.biddingwar.utils.DatabaseCleanup
import io.restassured.RestAssured
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.ActiveProfiles


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class AcceptanceTest {
    @LocalServerPort
    var port = 0

    @Autowired
    private val databaseCleanup: DatabaseCleanup? = null

    @BeforeEach
    fun setUp() {
        RestAssured.port = port
        databaseCleanup!!.execute()
    }
}
