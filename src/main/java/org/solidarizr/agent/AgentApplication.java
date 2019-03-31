package org.solidarizr.agent;

import com.pengrad.telegrambot.Callback;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
@Slf4j
public class AgentApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgentApplication.class, args);

		TelegramBot bot = new TelegramBot("637972659:AAFycM5hyYw3A2tPTU4SHFimV5SYPWXZmGo");

		GetUpdates getUpdates = new GetUpdates().limit(100).offset(0).timeout(0);

		log
		bot.execute(getUpdates, new Callback<GetUpdates, GetUpdatesResponse>() {
			@Override
			public void onResponse(GetUpdates request, GetUpdatesResponse response) {
				List<Update> updates = response.updates();
			}

			@Override
			public void onFailure(GetUpdates request, IOException e) {

			}
		});
	}

}
