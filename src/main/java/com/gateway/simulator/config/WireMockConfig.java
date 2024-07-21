package com.gateway.simulator.config;


import com.gateway.simulator.util.ResponseFillUsingParamTransformer;
import com.gateway.simulator.util.ResponseFillUsingRequestTransformer;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

@Configuration
@Slf4j
public class WireMockConfig {

    @Value("${wiremock.port}")
    private int wireMockPort;

    @Value("${wiremock.usingFilesUnderClasspath}")
    private String filesClasspath;

    @Value("${wiremock.verbose}")
    private boolean wireMockVerbose;

    @Bean
    public WireMockServer wireMockServer() {
        log.info("creating wiremock server in port:{}", wireMockPort);

        WireMockServer server = new WireMockServer(wireMockConfig()
                .port(wireMockPort)
                .usingFilesUnderClasspath(filesClasspath)
                .notifier(new ConsoleNotifier(wireMockVerbose))
                .extensions(
                        new ResponseFillUsingRequestTransformer(),
                        new ResponseFillUsingParamTransformer()
                )
                .jettyAcceptors(4)
                .jettyAcceptQueueSize(100)
                .asynchronousResponseEnabled(true)
                .asynchronousResponseThreads(10)
                .maxRequestJournalEntries(100)
        );

        log.info("starting wiremock server in port:{}", wireMockPort);
        server.start();
        log.info("Wiremock server up & running...");
        return server;
    }
}
