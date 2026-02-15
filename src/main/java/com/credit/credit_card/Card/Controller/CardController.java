package com.credit.credit_card.Card.Controller;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.credit.credit_card.Card.DTO.CardDTO;
import com.credit.credit_card.Card.DTO.TransactionDTO;
import com.credit.credit_card.Card.Model.CardModel;
import com.credit.credit_card.Card.Service.CardService;
import com.credit.credit_card.Customers.Controller.CustomerController;

@RestController
@RequestMapping("/card")
public class CardController {
    //
    private CardService cardServ;

    //Builder
    public CardController(CardService cardServ){
        this.cardServ = cardServ;
    }

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CustomerController.class);

    //POST
    @PostMapping("/register")
    public ResponseEntity<?> register_card(@RequestBody CardDTO cardDTO){
        try{
            logger.info("Request sent to /card/register");
            CardModel cardMod = cardServ.registerCard(cardDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(cardMod);
        } catch(RuntimeException e){
            logger.warn("WARN: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception ex){
            logger.error("ERROR: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                "Internal server error: " + ex.getMessage());
        }
    }
    //GET
    @GetMapping("/{card_id}")
    public ResponseEntity<?> get_card_id(@PathVariable int card_id){
        return cardServ.get_card(card_id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    //PATCH
    @PatchMapping("/reload/{card_id}")
    public ResponseEntity<?> top_up_money(@PathVariable int card_id, @RequestBody TransactionDTO transDTO){
       try{
            logger.info("Request sent to /card/reload");
            logger.info("Amount: " + transDTO.amount());
            CardModel cardModel = cardServ.top_up_money(card_id, transDTO);
            return ResponseEntity.ok("Successful recharge");
        } catch (IndexOutOfBoundsException IOOBE){
            return ResponseEntity.badRequest().body("Error: " + IOOBE.getMessage());
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("INTERNAL SERVER ERRROR: " +ex.getMessage());
        }
    }
    // Code under construction
}
