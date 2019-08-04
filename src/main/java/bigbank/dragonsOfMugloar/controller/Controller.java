package bigbank.dragonsOfMugloar.controller;

import bigbank.dragonsOfMugloar.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private ApplicationContext context;

    private final RestTemplate restTemplate;
    private final String mainUrl = "https://dragonsofmugloar.com/api/v2";
    private final String startGame = "/game/start";
    private final String investigateReputation = "/{gameId}/investigate/reputation";
    private final String getMessages = "/{gameId}/messages";
    private final String solvePickedMessage = "/{gameId}/solve/{adId}";
    private final String getListOfShopItems = "/{gameId}/shop";
    private final String buyItem = "/{gameId}/shop/buy/{itemId}";

    Controller(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    //    Game - Start a new game
//    POST
    public User startGame() throws HttpClientErrorException {
        ResponseEntity<User> result = restTemplate.exchange(mainUrl + startGame, HttpMethod.POST, null, User.class);
        return getBodyIfOkElseNull(result);
    }

    //    Investigation - Run an investigation about your reputation
//    POST
    public Reputation getReputation(String gameId) throws HttpClientErrorException {
        ResponseEntity<Reputation> result = restTemplate.exchange(mainUrl + investigateReputation, HttpMethod.POST, null, Reputation.class, gameId);
        return getBodyIfOkElseNull(result);
    }

    //    Message - Get all messages (tasks) from messageboard
//    GET
    public List<Message> getMessages(String gameId) throws HttpClientErrorException {
        ResponseEntity<List<Message>> result = restTemplate.exchange(mainUrl + getMessages, HttpMethod.GET, null, new ParameterizedTypeReference<List<Message>>() {
        }, gameId);
        return getBodyIfOkElseNull(result);
    }

    //    Message - Try to solve one of the message
//    POST
    public BuyOrSolveResult solveMessage(String gameId, String adId) throws HttpClientErrorException {
        ResponseEntity<BuyOrSolveResult> result = restTemplate.exchange(mainUrl + solvePickedMessage, HttpMethod.POST, null, BuyOrSolveResult.class, gameId, adId);
        return getBodyIfOkElseNull(result);
    }

    //    Shop - Get the listing of items available in shop
//    GET
    public List<ShopItem> getListOfShopItems(String gameId) throws HttpClientErrorException {
        ResponseEntity<List<ShopItem>> result = restTemplate.exchange(mainUrl + getListOfShopItems, HttpMethod.GET, null, new ParameterizedTypeReference<List<ShopItem>>() {
        }, gameId);
        return getBodyIfOkElseNull(result);
    }

    //    Shop - Purchase an item
//    POST
    public BuyOrSolveResult buyItem(String gameId, String itemId) throws HttpClientErrorException {
        ResponseEntity<BuyOrSolveResult> result = restTemplate.exchange(mainUrl + buyItem, HttpMethod.POST, null, BuyOrSolveResult.class, gameId, itemId);
        return getBodyIfOkElseNull(result);
    }

    public <T> T getBodyIfOkElseNull(ResponseEntity<T> entity) {
        if (entity.getStatusCode().equals(HttpStatus.OK)) {
            return entity.getBody();
        }
        return null;
    }
}
