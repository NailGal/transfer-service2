package ru.netolgy.service;

import org.springframework.stereotype.Service;
import ru.netolgy.logger.TransferLogger;
import ru.netolgy.request.ConfirmOperationRequest;
import ru.netolgy.request.TransferRequest;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TransferService {
    private final Map<String, TransferRequest> operations = new ConcurrentHashMap<>();
    private final TransferLogger transferLogger;

    public TransferService(TransferLogger transferLogger) {
        this.transferLogger = transferLogger;
    }

    public String processTransfer(TransferRequest request) {
        String operationId = UUID.randomUUID().toString();
        operations.put(operationId, request);
        transferLogger.logOperation(operationId, request, "PENDING");
        return operationId;
    }

    public String confirmOperation(ConfirmOperationRequest request) {
        TransferRequest transferRequest = operations.get(request.getOperationId());
        if (transferRequest == null) {
            throw new RuntimeException("Operation not found");
        }

        if (!"0000".equals(request.getCode())) {
            transferLogger.logOperation(
                    request.getOperationId(),
                    transferRequest,
                    "FAILED: Invalid code"
            );
            throw new RuntimeException("Invalid confirmation code");
        }

        transferLogger.logOperation(
                request.getOperationId(),
                transferRequest,
                "SUCCESS"
        );
        return request.getOperationId();
    }
}
