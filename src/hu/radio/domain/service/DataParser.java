package hu.radio.domain.service;

import hu.radio.domain.model.RadioMessage;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DataParser {

    public List<RadioMessage> parse(List<String> lines) {
        return IntStream.iterate(0, i -> i + 2).limit(lines.size() / 2)
                .mapToObj(i -> lines.subList(i, i + 2))
                .map(this::createRadioMessage)
                .collect(Collectors.toList());
    }

    private RadioMessage createRadioMessage(List<String> lines) {
        String[] items = lines.get(0).split(" ");
        int day = Integer.parseInt(items[0]);
        int receiverId = Integer.parseInt(items[1]);
        String message = lines.get(1);
        return new RadioMessage(day, receiverId, message);
    }
}
