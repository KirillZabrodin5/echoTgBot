package ru.zk.echoBot.logic;

public class BotRequest {
    public final long userId;
    private final long chatId;
    private final int messageId;

    public BotRequest(long userId, long chatId, int messageId) {
        this.userId = userId;
        this.chatId = chatId;
        this.messageId = messageId;
    }

    public long getUserId() {
        return userId;
    }

    public long getChatId() {
        return chatId;
    }

    public int getMessageId() {
        return messageId;
    }
}