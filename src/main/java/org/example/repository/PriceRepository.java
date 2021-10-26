package org.example.repository;

import org.example.model.Price;

import java.util.List;

public interface PriceRepository {
    List<Price> getPrices();
}
