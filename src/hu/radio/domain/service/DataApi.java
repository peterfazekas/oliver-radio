package hu.radio.domain.service;

import hu.radio.domain.model.RadioMessage;

import java.util.List;

public class DataApi {

    private final FileReader fileReader;
    private final DataParser dataParser;

    public DataApi(FileReader fileReader, DataParser dataParser) {
        this.fileReader = fileReader;
        this.dataParser = dataParser;
    }

    public List<RadioMessage> getData(String input) {
        return dataParser.parse(fileReader.read(input));
    }
}
