package org.solidarizr.agent.communicator.telegram;

import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.solidarizr.agent.messageHandler.HandledMessage;

public class HandledMessageToSendMessageTransformer {

    public static SendMessage transform(Long chatId, HandledMessage handledMessage){
        SendMessage sendMessage = new SendMessage(chatId, handledMessage.getText());

        if(handledMessage.getKeyboard() != null){

            String[] options = handledMessage.getKeyboard().getOptions().toArray(new String[0]);

            ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup(options)
                    .oneTimeKeyboard(true)
                    .resizeKeyboard(true)
                    .selective(true);

            sendMessage.replyMarkup(keyboardMarkup);
        }

        return sendMessage;
    }
}
