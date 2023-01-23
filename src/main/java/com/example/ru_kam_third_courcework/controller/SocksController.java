package com.example.ru_kam_third_courcework.controller;

import com.example.ru_kam_third_courcework.dto.SocksRequest;
import com.example.ru_kam_third_courcework.exception.DamageSocksRequestException;
import com.example.ru_kam_third_courcework.exception.LowSocksQuantityException;
import com.example.ru_kam_third_courcework.model.Color;
import com.example.ru_kam_third_courcework.model.Size;
import com.example.ru_kam_third_courcework.service.SocksService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/socks")
public class SocksController {

    private final SocksService socksService;

    public SocksController(SocksService socksService) {
        this.socksService = socksService;
    }

    @ExceptionHandler(DamageSocksRequestException.class)
    public ResponseEntity<String> handleDamageException(DamageSocksRequestException damageSocksRequestException) {
        return ResponseEntity.badRequest().body(damageSocksRequestException.getMessage());
    }

    @ExceptionHandler(LowSocksQuantityException.class)
    public ResponseEntity<String> handleLowException(LowSocksQuantityException lowSocksQuantityException) {
        return ResponseEntity.badRequest().body(lowSocksQuantityException.getMessage());
    }

    @PostMapping
    public void addSocks(@RequestBody SocksRequest socksRequest) {
        socksService.addSocks(socksRequest);
    }

    @PutMapping
    public void deliverySocks(@RequestBody SocksRequest socksRequest) {
        socksService.deliverySocks(socksRequest);
    }

    @GetMapping
    public int getSocksCount(@RequestParam(required = false, name = "color") Color color,
                             @RequestParam(required = false, name = "size") Size size,
                             @RequestParam(required = false, name = "cottonMin") Integer cottonMin,
                             @RequestParam(required = false, name = "cottonMax") Integer cottonMax) {
        return socksService.getSocksQuantity(color, size, cottonMin, cottonMax);
    }

    @DeleteMapping
    public void removeDefectCocks(@RequestBody SocksRequest socksRequest) {
        socksService.removeDefectSocks(socksRequest);
    }
}
