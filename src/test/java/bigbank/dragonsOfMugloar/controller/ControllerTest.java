package bigbank.dragonsOfMugloar.controller;

import bigbank.dragonsOfMugloar.dto.*;
import org.junit.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;

import java.util.List;

import static org.junit.Assert.*;

public class ControllerTest {

    private Controller controller = new Controller(new RestTemplateBuilder());

    @Test
    public void startGame() {
        User user = controller.startGame();
        assertNotNull(user);
    }

    @Test
    public void getReputation() {
        User user = controller.startGame();

        Reputation reputation = controller.getReputation(user.getGameId());
        assertNotNull(reputation);
    }

    @Test
    public void getMessages() {
        User user = controller.startGame();

        List<Message> messageList = controller.getMessages(user.getGameId());
        assertNotNull(messageList);
        assertFalse(messageList.isEmpty());
    }

    @Test
    public void solveMessage() {
        User user = controller.startGame();
        List<Message> messages = controller.getMessages(user.getGameId());

        BuyOrSolveResult result = controller.solveMessage(user.getGameId(), messages.get(0).getAdId());
        assertNotNull(result);
        assertTrue(result.isSuccess());
    }

    @Test
    public void getListOfShopItems() {
        User user = controller.startGame();

        List<ShopItem> listOfShopItems = controller.getListOfShopItems(user.getGameId());
        assertNotNull(listOfShopItems);
        assertFalse(listOfShopItems.isEmpty());
    }

    @Test
    public void buyItem() {
        User user = controller.startGame();
        user.setGold(100);
        List<ShopItem> items = controller.getListOfShopItems(user.getGameId());

        BuyOrSolveResult result = controller.buyItem(user.getGameId(), items.get(0).getId());
        assertNotNull(result);
        assertTrue(user.getGold() > result.getGold());
        assertTrue(result.isSuccess()); // buy was successful but boolean "shoppingSuccess" comes back as false - API bug
    }
}