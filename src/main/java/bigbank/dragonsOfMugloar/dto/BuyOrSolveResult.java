package bigbank.dragonsOfMugloar.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BuyOrSolveResult {
    private boolean success;
    private Integer lives;
    private Integer gold;
    private Integer score;
    private Integer level;
    private Integer highScore;
    private Integer turn;
    private String message;

    public BuyOrSolveResult() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "BuyOrSolveResult{" +
                "success=" + success +
                ", lives=" + lives +
                ", gold=" + gold +
                ", score=" + score +
                ", level=" + level +
                ", highScore=" + highScore +
                ", turn=" + turn +
                ", message='" + message + '\'' +
                '}';
    }
}
