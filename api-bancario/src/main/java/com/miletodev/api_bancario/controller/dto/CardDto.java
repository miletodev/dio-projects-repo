package com.miletodev.api_bancario.controller.dto;

import com.miletodev.api_bancario.domain.model.Card;

import java.math.BigDecimal;

public record CardDto(Long id, String number, BigDecimal limit) {

    public CardDto(Card model) {
        this(model.getId(), model.getNumber(), model.getAvailableLimit());
    }

    public Card toModel() {
        Card model = new Card();
        model.setId(this.id);
        model.setNumber(this.number);
        model.setAvailableLimit(this.limit);
        return model;
    }
}