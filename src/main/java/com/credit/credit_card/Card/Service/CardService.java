package com.credit.credit_card.Card.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Random;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.credit.credit_card.Card.DTO.CardDTO;
import com.credit.credit_card.Card.DTO.TransactionDTO;
import com.credit.credit_card.Card.Model.CardModel;
import com.credit.credit_card.Card.Repository.CardRepository;

@Service
public class CardService {

    private final CardRepository cardRep;
    private final Random random = new Random();

    public CardService(CardRepository cardRep) {
        this.cardRep = cardRep;
    }

    // CREATE CARD ---------------------------------------------------
    public CardModel registerCard(CardDTO cardDTO) {
        try {
            CardModel card = new CardModel(
                cardDTO.customer_id(),
                generateCardNumber(),
                generateCvv(),
                generateExpirationDate(),
                "ACTIVE",
                2000000000,
                0
            );

            if(cardRep.findByCustomerId(cardDTO.customer_id()).isPresent() && card.getStatus() == "ACTIVE"){
                throw new IllegalArgumentException("The current has active a card");
            }

            return this.cardRep.save(card);

        } catch (DataAccessException  e) {
            throw new RuntimeException("Error generating card: " + e.getMessage());
        }
    }
    //GET ---------------------------------------------------------
    public Optional<CardModel> get_card(int customer_id){
        update_card(customer_id);
        return this.cardRep.findById(customer_id);
    }

    //PATCH -------------------------------------------------------
    public CardModel top_up_money(int card_id, TransactionDTO transDTO){
        CardModel cardMod = cardRep.findById(card_id)
            .orElseThrow(() -> new IllegalArgumentException("The card does not exist"));
        if(transDTO.amount() > cardMod.getCredit_limit()){
            throw new IndexOutOfBoundsException("The limit to store money is $20,000,000");
        }
        cardMod.setCurrentBalance(cardMod.getCurrentBalance() + transDTO.amount());

        return this.cardRep.save(cardMod);
    }
    // Verify if the amount in acount > Requested amount
    // public CardModel pay_amoun(int card_id, transactionDTO transDTO){
    //     CardModel cardMod2 = cardRep.findById(transDTO.receiver())
    //         .orElseThrow(() -> new IllegalArgumentException("The number card does not exist"));

    //     CardModel cardMod = cardRep.findById(card_id)
    //         .orElseThrow(() -> new IllegalArgumentException("The card does not exist"));

    //     if(transDTO.amount() > cardMod.getCurrentBalance()){
    //         throw new IndexOutOfBoundsException("You don't have enough balance");
    //     }
    //     if(cardMod.getStatus() != "ACTIVE"){
    //         throw new IllegalAccessError("The card does not activate");
    //     }
        

    //     cardMod.setCurrentBalance(cardMod.getCurrentBalance() - transDTO.amount());
    //     return this.cardRep.save(cardMod);
    // }

    //PATCH ONLY DE CVV WHEN USE METHOD GET  
    public CardModel update_card(int id_card){
        CardModel cardModel = cardRep.findById(id_card)
            .orElseThrow(() -> new IllegalArgumentException("The card does not exist"));

        //Update the value
        cardModel.setCvv(generateCvv());
        return this.cardRep.save(cardModel);
    }
    // CVV -- Reusable :D
    private int generateCvv() {
        return random.nextInt(900) + 100;
    }

    // EXPIRATION DATE 
    private String generateExpirationDate() {
        LocalDate expiration = LocalDate.now().plusYears(5);    
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/yy");
        return expiration.format(format);
    }

    // CARD NUMBER
    private String generateCardNumber() {
        String prefix = "4"; // Visa-like
        String base = generateBaseNumber(15);
        return generateNumberLuhn(prefix + base);
    }

    // GENERATE BASE NUMBER
    private String generateBaseNumber(int length) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length - 1; i++) {
            sb.append(random.nextInt(10));
        }

        return sb.toString();
    }

    // Luhn Algorithm
    private String generateNumberLuhn(String number) {
        int sum = 0;
        boolean alternate = true;

        for (int i = number.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(number.charAt(i));

            if (alternate) {
                digit *= 2;
                if (digit > 9) digit -= 9;
            }

            sum += digit;
            alternate = !alternate;
        }

        int checkDigit = (10 - (sum % 10)) % 10;
        return number + checkDigit;
    }
}