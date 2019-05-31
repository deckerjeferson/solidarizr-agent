package org.solidarizr.agent.communicator.telegram;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.request.EditMessageText;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.solidarizr.agent.messageHandler.HandledMessage;
import org.solidarizr.agent.messageHandler.MessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

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
         startListeningUpdatesAsync();
    }

    private void startListeningUpdatesAsync(){

        log.info("Started to Listen to Telegram :)");
        bot.setUpdatesListener(updates -> {
            updates.forEach(update -> {

                String message;
                Long chatId;
                Optional<Integer> messageId = Optional.empty();

                if (update.message() == null) {
                    message = update.callbackQuery().data();
                    chatId = update.callbackQuery().message().chat().id();
                    messageId = Optional.of(update.callbackQuery().message().messageId());
                } else {
                    chatId = update.message().chat().id();
                    message = update.message().text();
                }

                try {
                    log.info("Handling message");

                    HandledMessage response = messageHandler.handle(chatId, message);

                    log.info("Handled Message to SendMessage");

                    if(messageId.isPresent()){
                        EditMessageText editMessageText = HandledMessageToMessageTransformer.transform(chatId, response, messageId.get());
                        bot.execute(editMessageText);
                    } else {
                        SendMessage sendMessage = HandledMessageToMessageTransformer.transform(chatId, response);
                        bot.execute(sendMessage);
                    }


                } catch (Exception ex) {
                    log.error(ex.getMessage());
                    log.error(ex.getStackTrace().toString());

                    bot.execute(new SendMessage(update.message().chat().id(), "Ocorreu algum problema, tente novamente depois, plz! :)"));
                }

            });

            // return id of last processed update or confirm them all
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }
}
