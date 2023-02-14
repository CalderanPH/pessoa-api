package paulocalderan.avaliacaodesenvolvedorbackend;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import paulocalderan.avaliacaodesenvolvedorbackend.utils.DataBaseCleaner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = AvaliacaoDesenvolvedorBackendApplication.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class IntegrationTestConfig {

    protected static final Pattern UUID_PATTERN = Pattern.compile("[^/]+$");

    @Autowired
    private DataBaseCleaner dataBaseCleaner;

    @LocalServerPort
    private int port;

    public void setUp() {
//        dataBaseCleaner.clearTables();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
//        RestAssured.port = port;
    }

    protected String getIdHeaderLocation(Response response) {
        String body = response.body().asString();
        Matcher matcher = UUID_PATTERN.matcher(body);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

}
