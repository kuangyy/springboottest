import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by kuangye on 2017/5/2.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Test1 {

    @Autowired
    private TestRestTemplate restTemplate;

    private JacksonTester json;

    @Before
    public void setup() {
        ObjectMapper objectMapper = new ObjectMapper();
        // Possibly configure the mapper
        JacksonTester.initFields(this, objectMapper);
    }


    @Test
    public void exampleTest() {
        String body = this.restTemplate.getForObject("/", String.class);

//        assertThat(body).isEqualTo("{\"a\":\"1\"}");
    }




}
