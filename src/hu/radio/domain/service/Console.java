package hu.radio.domain.service;

import java.util.Scanner;

public class Console {

    private final Scanner scanner;

    public Console(Scanner scanner) {
        this.scanner = scanner;
    }

    public int readInt() {
        return scanner.nextInt();
    }
}
