package ru.zk.echoBot.telegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.CopyMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.zk.echoBot.AnswerWriter;
import ru.zk.echoBot.MessageConverter;
import ru.zk.echoBot.MessageHandler;
import ru.zk.echoBot.logic.BotRequest;
import ru.zk.echoBot.logic.BotResponse;
import ru.zk.echoBot.logic.EchoMessageHandler;

public class Bot extends TelegramLongPollingBot implements AnswerWriter {
    private final String apiKey;
    private final String botName;
    private final MessageConverter converter = new TelegramMessageConverter();
    private final MessageHandler handler = new EchoMessageHandler();

    public Bot(String apiKey, String botName) {
        super();
        this.apiKey = apiKey;
        this.botName = botName;
    }

    public void onUpdateReceived(Update update) {
        BotRequest request = converter.convertMessage(update);
        handler.handle(request, this);
    }

    @Override
    public String getBotUsername() {
        return botName;
    }
    @Override
    public String getBotToken() {
        return apiKey;
    }

    @Override
    public void writeAnswer(BotResponse response) {
        long userId = response.getUserId();

        int messageId = response.getMessageId();

        CopyMessage cm = CopyMessage.builder()
                .fromChatId(String.valueOf(userId))
                .chatId(String.valueOf(userId))
                .messageId(messageId)
                .build();
        try {
            execute(cm);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
