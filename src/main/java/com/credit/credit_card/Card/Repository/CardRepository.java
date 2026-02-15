package com.credit.credit_card.Card.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.credit.credit_card.Card.Model.CardModel;

public interface CardRepository extends JpaRepository <CardModel, Integer>{
    Optional<CardModel> findByCardId(int cardId);
    Optional <CardModel> findByCustomerId(int idCustomer);
}
