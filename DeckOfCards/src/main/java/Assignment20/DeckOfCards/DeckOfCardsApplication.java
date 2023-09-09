package Assignment20.DeckOfCards;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DeckOfCardsApplication {

    private static String deckID;
    /**
     * runs the spring app, prints new lines and calls the shuffleCards method
     * @param args 
     */
    public static void main(String[] args) {
        SpringApplication.run(DeckOfCardsApplication.class, args);

        System.out.println("\n\n------------------------");

        shuffleCards();
        ApiController draw = new ApiController();

        draw.get2Cards();
    }

    /**
     * Sends a get request to the url and retrieves the deck ID and the 
     * remaining amount of cards
     * 
     */
    public static void shuffleCards() {
        try {
            String url = "https://www.deckofcardsapi.com/api/deck/new/shuffle/?deck_count=1";
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();

            String jSonPrice = restTemplate.getForObject(url, String.class);
            JsonNode root = mapper.readTree(jSonPrice);

            //gets deck ID
            deckID = root.findValue("deck_id").asText();
            //gets the amount of cards
            int numCards = root.findValue("remaining").asInt();

            System.out.println("Deck ID: " + deckID);
            System.out.println("Card Count: " + numCards);

        } catch (JsonProcessingException ex) {
            System.out.println("error in Card Deck");
        }
    }

    public static String getDeckID() {
        return deckID;
    }

}
