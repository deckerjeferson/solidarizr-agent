package org.solidarizr.agent.communicator.telegram;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.EditMessageText;
import com.pengrad.telegrambot.request.SendMessage;
import org.solidarizr.agent.messageHandler.HandledMessage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HandledMessageToMessageTransformer {

    public static SendMessage transform(Long chatId, HandledMessage handledMessage){
        SendMessage sendMessage = new SendMessage(chatId, handledMessage.getText());

        if(handledMessage.getKeyboard() != null){
            sendMessage.replyMarkup(createKeyboardBasedOnMessageOptions(handledMessage));
        }

        return sendMessage;
    }

    public static EditMessageText transform(Long chatId, HandledMessage handledMessage, Integer messageId){
        EditMessageText editMessageText = new EditMessageText(chatId, messageId, handledMessage.getText());

        if(handledMessage.getKeyboard() != null){
            editMessageText.replyMarkup(createKeyboardBasedOnMessageOptions(handledMessage));
        }

        return editMessageText;
    }

    private static InlineKeyboardMarkup createKeyboardBasedOnMessageOptions(HandledMessage handledMessage) {
        List<InlineKeyboardButton[]> buttons = new ArrayList<>();
        handledMessage.getKeyboard().getOptions().forEach(option -> {
            InlineKeyboardButton buttonToAdd = new InlineKeyboardButton(option.getOption()).callbackData(option.getId());
            InlineKeyboardButton[] rowOfButtons = {buttonToAdd};
            buttons.add(rowOfButtons);
        });

        InlineKeyboardButton[][] options = buttons.toArray(new InlineKeyboardButton[0][0]);

        return new InlineKeyboardMarkup(options);
    }
}
