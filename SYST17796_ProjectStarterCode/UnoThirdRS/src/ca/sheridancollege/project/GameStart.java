package ca.sheridancollege.project;

/**
 * @author rylei
 */
public class GameStart {

    private static UnoDeck deckOfCards;

    public void generateDeck() {
        System.out.println("Shuffling Deck...");
        deckOfCards = new UnoDeck();
        deckOfCards.reset();
        deckOfCards.shuffle();
        // for testing purposes to make sure deck was working
        // for (UnoCard card : deckOfCards.getCards()) {
        //    System.out.println(card);
    }

    // Method to generate initial hand 
    public void generatePlayerHand(Player player) {
        UnoCard[] hand = deckOfCards.drawCard(7);
        player.setPlayerHand(hand);
    }

}
