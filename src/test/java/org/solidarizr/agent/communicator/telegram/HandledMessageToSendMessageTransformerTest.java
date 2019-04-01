package org.solidarizr.agent.communicator.telegram;

import com.pengrad.telegrambot.request.SendMessage;
import org.junit.Test;
import org.solidarizr.agent.messageHandler.HandledMessage;

import static org.assertj.core.api.Assertions.assertThat;

public class HandledMessageToSendMessageTransformerTest {

    @Test
    public void return_send_message_with_equal_text_and_chat_id(){
        Long chatId = 1234434524L;
        String messageText = "Teste!";

        HandledMessage handledMessage = HandledMessage.builder().text(messageText).build();
        SendMessage expected = new SendMessage(chatId, messageText);

        SendMessage result = HandledMessageToSendMessageTransformer.transform(chatId, handledMessage);

        assertThat(result.getParameters().get("text")).isEqualTo(handledMessage.getText());
        assertThat(result.getParameters().get("chat_id")).isEqualTo(chatId);
    }

}