package org.solidarizr.agent;

import lombok.extern.slf4j.Slf4j;
import org.solidarizr.agent.communicator.telegram.TelegramBotCommunicator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AgentApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgentApplication.class, args);

		TelegramBotCommunicator telegramBotCommunicator = new TelegramBotCommunicator();
	}

}
