package com.example.sockstock.controller;

import com.example.sockstock.exception.ParametersNotFoundException;
import com.example.sockstock.model.Socks;
import com.example.sockstock.operation.ComparisonOperator;
import com.example.sockstock.service.SockStockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/socks")
public class SockStockController {

    private final SockStockService sockStockService;

//    public SockStockController(SockStockService sockStockService) {
//        this.sockStockService = sockStockService;
//    }

    @Operation(summary = "Приход носков", description = "Регистрирует приход носков на склад")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Запрос выполнен",
                    content = @Content(mediaType = "*/*",
                            schema = @Schema(implementation = Socks.class))),
            @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат"),
            @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")})
    @PostMapping("/income")
    public ResponseEntity<Void> incomeSocks(@RequestBody(required = true) Socks socks) {
        log.info("Was invoked incomeSocks method SockStockController");
        if (socks.getColor() == null || socks.getCottonPart() == null || socks.getQuantity() == null) {
            throw new ParametersNotFoundException("Параметры переданы неправильно");
        }
        sockStockService.incomeSocks(socks);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Отпуск носков", description = "Регистрирует отпуск носков со склада")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Запрос выполнен",
                    content = @Content(mediaType = "*/*",
                            schema = @Schema(implementation = Socks.class))),
            @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат"),
            @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")})
    @PostMapping("/outcome")
    public ResponseEntity<Void> outcomeSocks(@RequestBody(required = true) Socks socks) {
        log.info("Was invoked outcomeSocks method SockStockController");
        if (socks.getColor() == null || socks.getCottonPart() == null || socks.getQuantity() == null) {
            throw new ParametersNotFoundException("Параметры переданы неправильно");
        }
        sockStockService.outcomeSocks(socks);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Общее количество носков", description = "Возвращает общее количество носков на складе, по критериям запроса")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Запрос выполнен",
                    content = @Content(mediaType = "*/*",
                            schema = @Schema(implementation = Socks.class))),
            @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат"),
            @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")})
    @GetMapping
    public ResponseEntity<Long> allSocks(@RequestParam("color") String color,
                                         @RequestParam("operation") ComparisonOperator operation,
                                         @RequestParam("cottonPart") Long cottonPart) {
        log.info("Was invoked allSocks method SockStockController");
        if (color == null || operation == null || cottonPart == null) {
            throw new ParametersNotFoundException("Параметры переданы неправильно");
        }
        return ResponseEntity.ok(sockStockService.allSocks(color, operation, cottonPart));
    }

}
