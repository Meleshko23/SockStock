package com.example.sockstock.service;

import com.example.sockstock.model.Socks;
import com.example.sockstock.operation.ComparisonOperator;

import java.util.function.IntPredicate;

/**
 * Класс определяет логику работы склада:
 * Приход носков;
 * Отпуск носков;
 * Получение количества носков по параметрам
 */
public interface SockStockService {

    /**
     * Регистрирует приход носков на склад
     *
     * @param socks
     * @return
     */
    IntPredicate incomeSocks(Socks socks);

    /**
     * Регистрирует отпуск носков со склада
     *
     * @param socks
     */
    void outcomeSocks(Socks socks);

    /**
     * Возвращает общее количество носков на складе, по критериям запроса
     *
     * @param color
     * @param operation
     * @param cottonPart
     * @return
     */
    Long allSocks(String color, ComparisonOperator operation, Long cottonPart);

}
