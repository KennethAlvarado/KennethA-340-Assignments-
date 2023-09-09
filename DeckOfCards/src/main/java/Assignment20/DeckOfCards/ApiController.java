package Assignment20.DeckOfCards;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Kenneth Alvarado
 */
public class ApiController {
    
    
     /**
     * Draw 2 cards from the shuffled card deck.
     *
     * @return The quote json response
     */
    @GetMapping("/draw2cards")
    public Object get2Cards() {
        try {
            //Has to have a deckID in the url
            String url = "https://www.deckofcardsapi.com/api/deck/" + DeckOfCardsApplication.getDeckID() + "/draw/?count=2";
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();

            String jSonQuote = restTemplate.getForObject(url, String.class);
            JsonNode root = mapper.readTree(jSonQuote);

            
            JsonNode cardsNode = root.get("cards");
            
            System.out.println("\n------------------------\nDeck ID: " + DeckOfCardsApplication.getDeckID());
            
            // The card value in suit are stored in an array called cards
            if (cardsNode != null && cardsNode.isArray()) {
                for (JsonNode cardNode : cardsNode) {
                    String cardValue = cardNode.get("value").asText();
                    String suit = cardNode.get("suit").asText();
                    
                    System.out.println("Value of Card: " + cardValue);
                    System.out.println("Suit: " + suit);
                    
                }
            }

          
            int numOfCardsLeft = root.get("remaining").asInt();
           
            System.out.println("Remaining Cards: " + numOfCardsLeft);

            return root;

        } catch (JsonProcessingException ex) {
            Logger.getLogger(ApiController.class.getName()).log(Level.SEVERE,
                    null, ex);
            return "error in /draw2cards";
        }
    }
}
