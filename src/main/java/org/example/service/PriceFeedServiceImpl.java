package org.example.service;

import org.example.repository.PriceRepository;

public class PriceFeedServiceImpl implements PriceFeedService{
    PriceRepository priceRepository;

    public PriceFeedServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public void start() {
        priceRepository.loadData();
    }

    @Override
    public void stop() {
        priceRepository.stopLoading();
    }
}
