package org.example.repository;

import org.example.model.InternalPrice;

import java.util.List;

public interface PriceRepository {
    List<InternalPrice> getPrices();
}
