package com.example.sockstock.service.impl;

import com.example.sockstock.exception.ParametersNotFoundException;
import com.example.sockstock.model.Socks;
import com.example.sockstock.operation.ComparisonOperator;
import com.example.sockstock.repository.SockStockRepository;
import com.example.sockstock.service.SockStockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.function.IntPredicate;

@Slf4j
@RequiredArgsConstructor
@Service
public class SockStockServiceImpl implements SockStockService {

    private final SockStockRepository sockStockRepository;

    @Override
    public IntPredicate incomeSocks(Socks income) {
        Socks socks = sockStockRepository.findByColorAndCottonPart(
                income.getColor().toLowerCase(Locale.ROOT),
                income.getCottonPart());
        if (socks == null) {
            sockStockRepository.save(income);
        } else {
            socks.setQuantity(socks.getQuantity() + income.getQuantity());
            sockStockRepository.save(socks);
        }
        return null;
    }

    @Override
    public void outcomeSocks(Socks outcome) {
        Socks socks = sockStockRepository.findByColorAndCottonPart(
                outcome.getColor().toLowerCase(Locale.ROOT),
                outcome.getCottonPart());
        if (socks == null) {
            throw new ParametersNotFoundException("Позиция не найдена");
        } else {
            socks.setQuantity(socks.getQuantity() - outcome.getQuantity());
            sockStockRepository.save(socks);
        }
    }

    @Override
    public Long allSocks(String color, ComparisonOperator operation, Long cottonPart) {
        return sockStockRepository.findSockByColor(color.toLowerCase(Locale.ROOT))
                .stream()
                .filter(socks -> {
                    if (operation.equals(ComparisonOperator.moreThan)) {
                        return socks.getCottonPart() > cottonPart;
                    } else if (operation.equals(ComparisonOperator.lessThan)) {
                        return socks.getCottonPart() < cottonPart;
                    } else if (operation.equals(ComparisonOperator.equals)) {
                        return socks.getCottonPart().equals(cottonPart);
                    }
                    return false;
                })
                .mapToLong(Socks::getQuantity)
                .sum();
    }

}
