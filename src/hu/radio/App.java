package hu.radio;

import hu.radio.controller.MessageService;
import hu.radio.domain.service.*;

import java.util.Scanner;

public class App {

    private final Console console;
    private final FileWriter fileWriter;
    private final MessageService messageService;

    private App() {
        console = new Console(new Scanner(System.in));
        fileWriter = new FileWriter("adaas.txt");
        DataApi dataApi = new DataApi(new FileReader(), new DataParser());
        messageService = new MessageService(dataApi.getData("veetel.txt"));
    }

    public static void main(String[] args) {
        new App().run();
    }

    private void run() {
        System.out.println("2. feladat:");
        System.out.println("Az első üzenet rögzítője: " + messageService.getFirstMessageReceiverId());
        System.out.println("Az utolsó üzenet rögzítője: " + messageService.getLastMessageReceiverId());
        System.out.println("3. feladat:");
        System.out.println(messageService.getMessageDetailsByContent("farkas"));
        System.out.println("4. feladat:");
        System.out.println(messageService.getDailyStatistic());
        fileWriter.writeAll(messageService.getMergedMessages());
        System.out.println("7. feladat:");
        System.out.print("Adja meg a nap sorszámát: ");
        int day = console.readInt();
        System.out.print("Adja meg a rádióamatőr sorszámát: ");
        int receiverId = console.readInt();
        System.out.println(messageService.getAccumulatedData(day, receiverId));
    }
}
