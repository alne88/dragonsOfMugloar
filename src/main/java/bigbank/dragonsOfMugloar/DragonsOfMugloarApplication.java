package bigbank.dragonsOfMugloar;

import bigbank.dragonsOfMugloar.controller.Controller;
import bigbank.dragonsOfMugloar.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@SpringBootApplication
public class DragonsOfMugloarApplication implements CommandLineRunner {

    @Autowired
    private ApplicationContext context;

    @Autowired
    Controller controller;

    public static void main(String[] args) {
        SpringApplication.run(DragonsOfMugloarApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("########################################################");
        System.out.println("Welcome to the Kingdom of Mugloar, starting new journey");
        System.out.println("########################################################");
        int roundCount = 1;

        User user = controller.startGame();

        if (user == null) {
            System.out.println("Couldn't start game, quitting");
            return;
        }
        System.out.println("Greetings user " + user.getGameId());

        while (user.getLives() > 0) {
            System.out.println("########################################################");
            try {
                Message messageToSolve = getAdToSolve(user);
                solveAd(user, messageToSolve);
                if (user.getLives() == 0) {
                    // if last life was lost on solving then no need for Controller to catch "410" Exception
                    break;
                }
                doShopping(user);
                Reputation reputation = controller.getReputation(user.getGameId());
                System.out.println(reputation.toString());
            } catch (HttpClientErrorException e) {
                System.out.println("Error during HTTP call: " + e.getMessage());
            }

            if (roundCount % 10 == 0) {
                //clear shopping history every 10 rounds, so we can buy previously bought items again
                user.getBoughtItems().clear();
            }

            System.out.println("--------------------------------------------------------");
            System.out.println("Gold: " + user.getGold());
            System.out.println("Score: " + user.getScore());
            System.out.println("Lives left: " + user.getLives());
            System.out.println("End of round " + roundCount);
            roundCount++;
        }

        if (user.getLives() == 0) {
            System.out.println("########################################################");
            System.out.println("Out of lives, game over");
            System.out.println(user.toString());
            shutdownApp();
        }
    }

    private Message getAdToSolve(User user) throws HttpClientErrorException {
        List<Message> messages = controller.getMessages(user.getGameId());
        List<Message> nonEncryptedMessages = getNonEncryptedMessages(messages);
        return pickAdToSolve(nonEncryptedMessages);
    }

    private void solveAd(User user, Message messageToSolve) {
        BuyOrSolveResult result = controller.solveMessage(user.getGameId(), messageToSolve.getAdId());

        System.out.println(messageToSolve.getMessage() + " (id:" + messageToSolve.getAdId() + ") - " + result.getMessage());
        updateUserStats(user, result);
        user.setLastTaskFailed(result.isSuccess());
    }

    public void doShopping(User user) throws HttpClientErrorException {
        if (user.getLives() == 1 || user.getGold() > 300) {  // collect gold a bit before buying, higher chance of buying high grade stuff, except when lives are low

            List<ShopItem> shopItems = controller.getListOfShopItems(user.getGameId());
            for (ShopItem shopItem : shopItems) {
                boolean isAlreadyBought = user.getBoughtItems().stream().filter(item -> item.getId().equals(shopItem.getId())).findFirst().isPresent();
                if (isAlreadyBought) {
                    continue;
                }
                if (user.getGold() > shopItem.getCost()) {
                    buyItem(user, shopItem);
                }
            }
        }
    }

    private void buyItem(User user, ShopItem shopItem) throws HttpClientErrorException {
        BuyOrSolveResult result = controller.buyItem(user.getGameId(), shopItem.getId());

        if (user.getGold() > result.getGold()) { // transaction was successful when result shows less gold (user is not yet updated at this point)
//        if (result.isSuccess()){  // getting false on "success" even though reduction of gold
            System.out.println("Purchased - " + shopItem.getName());
            user.getBoughtItems().add(shopItem);
        }
        updateUserStats(user, result);
    }

    public void updateUserStats(User user, BuyOrSolveResult result) {
        if (result.getLives() != null) {
            user.setLives(result.getLives());
        }
        if (result.getGold() != null) {
            user.setGold(result.getGold());
        }
        if (result.getLevel() != null) {
            user.setLevel(result.getLevel());
        }
        if (result.getTurn() != null) {
            user.setTurn(result.getTurn());
        }
        if (result.getScore() != null) {
            user.setScore(result.getScore());
        }
        if (result.getHighScore() != null) {
            user.setHighScore(result.getHighScore());
        }
    }


    public Message pickAdToSolve(List<Message> messages) {
        //no pain no gain
        return messages
                .stream()
                .max(Comparator.comparing(v -> Integer.parseInt(v.getReward())))
                .get();
    }

    public List<Message> getNonEncryptedMessages(List<Message> messages) {
        List<Message> nonEncryptedMessages = new ArrayList<>();
        for (Message message : messages) {
            if (message.getEncrypted() == null) {
                nonEncryptedMessages.add(message);
            }
        }
        return nonEncryptedMessages;
    }

    public void shutdownApp() {

        int exitCode = SpringApplication.exit(context, (ExitCodeGenerator) () -> 0);
        System.exit(exitCode);
    }
}