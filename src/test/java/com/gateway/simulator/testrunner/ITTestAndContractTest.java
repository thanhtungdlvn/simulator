package com.gateway.simulator.testrunner;

import com.gateway.simulator.SimulatorApplication;
import com.intuit.karate.junit5.Karate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("local")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = {SimulatorApplication.class})
public class ITTestAndContractTest {
    @LocalServerPort
    int randomServerPort;

    @Karate.Test
    public Karate runTest() {
        String path = System.getProperty("user.dir") + "/src/test/java/com/gateway/simulator/interationtests";
        System.setProperty("application.URL", "http://localhost:" + 9091 + "/");
        return Karate.run(path + "/getCard.feature");
    }
}
