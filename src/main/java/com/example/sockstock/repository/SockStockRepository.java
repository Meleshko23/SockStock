package com.example.sockstock.repository;

import com.example.sockstock.model.Socks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SockStockRepository extends JpaRepository<Socks, Integer> {

    Socks findByColorAndCottonPart(String color, Long cottonPart);

    List<Socks> findSockByColor(String color);

//    SockStock findByColorAndCottonPart(String color, Byte cottonPart);
//
//    List<SockStock> findAllByColorAndCottonPart(String color, Byte cottonPart);
//    List<SockStock> findAllByColorAndCottonPartGreaterThan(String color, Byte cottonPart);
//    List<SockStock> findAllByColorAndCottonPartLessThan(String color, Byte cottonPart);
}
