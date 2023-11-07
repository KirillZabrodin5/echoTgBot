package ru.zk.echoBot.telegram;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.zk.echoBot.MessageConverter;
import ru.zk.echoBot.logic.BotRequest;

public class TelegramMessageConverter implements MessageConverter {
    @Override
    public BotRequest convertMessage(Update update) {
        var message = update.getMessage();
        long userId = message.getFrom().getId();
        long chatId = message.getChatId();
        int messageId = message.getMessageId();

        return new BotRequest(userId, chatId, messageId);
    }
}
