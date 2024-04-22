package com.flesk.messageriee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;


@EnableWebSocketMessageBroker
@SpringBootApplication
		(exclude = {SecurityAutoConfiguration.class})
public class MessagerieeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessagerieeApplication.class, args);
	}

}
