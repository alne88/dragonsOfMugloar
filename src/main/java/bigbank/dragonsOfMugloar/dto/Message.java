package bigbank.dragonsOfMugloar.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {
    private String adId;
    private String message;
    private String reward;
    private Integer expiresIn;
    private String probability;
    private Integer encrypted;

    public Message() {
    }

    public String getAdId() {
        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getProbability() {
        return probability;
    }

    public void setProbability(String probability) {
        this.probability = probability;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public Integer getEncrypted() {
        return encrypted;
    }

    public void setEncrypted(Integer encrypted) {
        this.encrypted = encrypted;
    }

    @Override
    public String toString() {
        return "Message{" +
                "adId='" + adId + '\'' +
                ", message='" + message + '\'' +
                ", reward='" + reward + '\'' +
                ", expiresIn=" + expiresIn +
                ", probability='" + probability + '\'' +
                ", encrypted=" + encrypted +
                '}';
    }
}
