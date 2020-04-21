package hu.radio.controller;

import hu.radio.domain.model.RadioMessage;
import hu.radio.domain.service.MessageHelper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MessageService {

    private final List<RadioMessage> messages;

    public MessageService(List<RadioMessage> messages) {
        this.messages = messages;
    }

    /**
     * 2. feladat
     */
    public int getFirstMessageReceiverId() {
        int first = 0;
        return messages.get(first).getReceiverId();
    }

    public int getLastMessageReceiverId() {
        int last = messages.size() - 1;
        return messages.get(last).getReceiverId();
    }

    /**
     * 3. feladat
     */
    public String getMessageDetailsByContent(String text) {
        return messages.stream()
                .filter(message -> message.contains(text))
                .map(message -> String.format("%d. nap %d. rádióamatőr", message.getDay(), message.getReceiverId()))
                .collect(Collectors.joining("\r\n"));
    }

    /**
     * 4. feladat
     */
    public String getDailyStatistic() {
        return IntStream.range(1, 12)
                .mapToObj(day -> String.format("%d. nap: %d rádióamatőr", day, countDailyMessages(day)))
                .collect(Collectors.joining("\r\n"));
    }

    private long countDailyMessages(int day) {
        return messages.stream()
                .filter(messages -> messages.getDay() == day)
                .count();
    }

    /**
     * 5. feladat
     */

    public List<String> getMergedMessages() {
        return IntStream.range(1, 12)
                .mapToObj(this::getMessagesByDay)
                .map(MessageHelper::mergeMessages)
                .collect(Collectors.toList());
    }

    private List<String> getMessagesByDay(int day) {
        return messages.stream()
                .filter(messages -> messages.getDay() == day)
                .map(RadioMessage::getMessage)
                .collect(Collectors.toList());
    }

    /**
     * 7. feladat
     */

    public String getAccumulatedData(int day, int receiverId) {
        return getMessageByDayAndReceiverId(day, receiverId)
                .map(MessageHelper::getAccumulatedData)
                .orElse("Nincs ilyen feljegyzés");
    }

    private Optional<String> getMessageByDayAndReceiverId(int day, int receiverId) {
        return messages.stream()
                .filter(messages -> messages.getDay() == day && messages.getReceiverId() == receiverId)
                .map(RadioMessage::getMessage)
                .findFirst();
    }
}
