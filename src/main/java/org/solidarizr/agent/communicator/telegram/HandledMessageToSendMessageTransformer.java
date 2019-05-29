package org.solidarizr.agent.communicator.telegram;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.solidarizr.agent.messageHandler.HandledMessage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HandledMessageToSendMessageTransformer {

    public static SendMessage transform(Long chatId, HandledMessage handledMessage){
        SendMessage sendMessage = new SendMessage(chatId, handledMessage.getText());

        if(handledMessage.getKeyboard() != null){


            //String[] options = handledMessage.getKeyboard().getOptions().toArray(new String[0]);

            List<InlineKeyboardButton> buttons = new ArrayList<>();
            for(String option : handledMessage.getKeyboard().getOptions()){
                buttons.add(new InlineKeyboardButton(option).callbackData(option));
            }

//            ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup(options)
//                    .oneTimeKeyboard(true)
//                    .resizeKeyboard(true)
//                    .selective(true);


            InlineKeyboardButton[] options = buttons.toArray(new InlineKeyboardButton[0]);

            InlineKeyboardMarkup teste = new InlineKeyboardMarkup(options);

            sendMessage.replyMarkup(teste);
        }

        return sendMessage;
    }
}
