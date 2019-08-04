package bigbank.dragonsOfMugloar.utils;

import bigbank.dragonsOfMugloar.dto.BuyOrSolveResult;
import bigbank.dragonsOfMugloar.dto.Message;
import bigbank.dragonsOfMugloar.dto.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Helper {

    public static void updateUserStats(User user, BuyOrSolveResult result) {
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


    public static Message pickAdToSolve(List<Message> messages) {
        //no pain no gain
        return messages
                .stream()
                .max(Comparator.comparing(v -> Integer.parseInt(v.getReward())))
                .get();
    }

    public static List<Message> getNonEncryptedMessages(List<Message> messages) {
        List<Message> nonEncryptedMessages = new ArrayList<>();
        for (Message message : messages) {
            if (message.getEncrypted() == null) {
                nonEncryptedMessages.add(message);
            }
        }
        return nonEncryptedMessages;
    }

}
