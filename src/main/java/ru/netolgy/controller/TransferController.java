package ru.netolgy.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.netolgy.request.ConfirmOperationRequest;
import ru.netolgy.request.TransferRequest;
import ru.netolgy.service.TransferService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
public class TransferController {
    // Создаем статическую переменную логгера
    private static final Logger logger = LoggerFactory.getLogger(TransferController.class);

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(@Valid @RequestBody TransferRequest request) {
        // Логируем получение запроса
        logger.info("Received transfer request: {}", request);

        try {
            String operationId = transferService.processTransfer(request);

            // Логируем успешную обработку
            logger.info("Transfer processed successfully. Operation ID: {}", operationId);

            return ResponseEntity.ok().body("{\"operationId\":\"" + operationId + "\"}");
        } catch (Exception e) {
            // Логируем ошибку
            logger.error("Transfer failed: {}", e.getMessage(), e);

            return ResponseEntity.badRequest().body("{\"message\":\"" + e.getMessage() + "\"}");
        }
    }

    @PostMapping("/confirmOperation")
    public ResponseEntity<?> confirmOperation(@Valid @RequestBody ConfirmOperationRequest request) {
        // Логируем получение запроса подтверждения
        logger.info("Received confirmation request: {}", request);

        try {
            String operationId = transferService.confirmOperation(request);

            // Логируем успешное подтверждение
            logger.info("Confirmation successful. Operation ID: {}", operationId);

            return ResponseEntity.ok().body("{\"operationId\":\"" + operationId + "\"}");
        } catch (Exception e) {
            // Логируем ошибку подтверждения
            logger.error("Confirmation failed: {}", e.getMessage(), e);

            return ResponseEntity.badRequest().body("{\"message\":\"" + e.getMessage() + "\"}");
        }
    }
}

