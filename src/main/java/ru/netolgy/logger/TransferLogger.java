package ru.netolgy.logger;

import org.springframework.stereotype.Component;
import ru.netolgy.request.TransferRequest;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class TransferLogger {
    private static final String LOG_FILE = "logs/transfer.log";
    private static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void logOperation(
            String operationId,
            TransferRequest request,
            String status
    ) {
        String logEntry = String.format(
                "[%s] Operation: %s | From: %s | To: %s | Amount: %d %s | Status: %s",
                LocalDateTime.now().format(DATE_FORMAT),
                operationId,
                request.getCardFromNumber(),
                request.getCardToNumber(),
                request.getAmount().getValue(),
                request.getAmount().getCurrency(),
                status
        );

        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
            writer.write(logEntry + "\n");
        } catch (IOException e) {
            System.err.println("Failed to write log: " + e.getMessage());
        }
    }
}
