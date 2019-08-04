package bigbank.dragonsOfMugloar.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private String gameId;
    private Integer lives;
    private Integer gold;
    private Integer level;
    private Integer score;
    private Integer highScore;
    private Integer turn;
    private List<ShopItem> boughtItems = new ArrayList<>();
    private boolean lastTaskFailed = false;

    public User() {
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public Integer getLives() {
        return lives;
    }

    public void setLives(Integer lives) {
        this.lives = lives;
    }

    public Integer getGold() {
        return gold;
    }

    public void setGold(Integer gold) {
        this.gold = gold;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getHighScore() {
        return highScore;
    }

    public void setHighScore(Integer highScore) {
        this.highScore = highScore;
    }

    public Integer getTurn() {
        return turn;
    }

    public void setTurn(Integer turn) {
        this.turn = turn;
    }

    public List<ShopItem> getBoughtItems() {
        return boughtItems;
    }

    public void setBoughtItems(List<ShopItem> boughtItems) {
        this.boughtItems = boughtItems;
    }

    public boolean isLastTaskFailed() {
        return lastTaskFailed;
    }

    public void setLastTaskFailed(boolean lastTaskFailed) {
        this.lastTaskFailed = lastTaskFailed;
    }

    @Override
    public String toString() {
        return "User{" +
                "gameId='" + gameId + '\'' +
                ", lives=" + lives +
                ", gold=" + gold +
                ", level=" + level +
                ", score=" + score +
                ", highScore=" + highScore +
                ", turn=" + turn +
                '}';
    }
}
