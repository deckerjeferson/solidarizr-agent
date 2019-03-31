package org.solidarizr.agent.communicator.telegram;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.solidarizr.agent.messageHandler.MessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@NoArgsConstructor
public class TelegramBotCommunicator {

    TelegramConfigs configs;
    TelegramBot bot;
    MessageHandler messageHandler;

    @Autowired
    public TelegramBotCommunicator(TelegramConfigs configs, MessageHandler messageHandler) {
        this.configs = configs;
        this.messageHandler = messageHandler;

         this.bot = new TelegramBot(configs.getToken());
    }

    public void startListeningUpdatesAsync(){

        log.info("Started to Listen to Telegram :)");
        bot.setUpdatesListener(updates -> {
            updates.forEach(update -> {
                String response = messageHandler.handle(update.message().text());
                bot.execute(new SendMessage(update.message().chat().id(), response));
            });

            // return id of last processed update or confirm them all
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }
}
