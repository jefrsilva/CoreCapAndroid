package br.com.loneseal.corecapandroid.event;

/**
 * Created by jefrsilva on 05/06/16.
 */
public class PlayerRegisteredEvent {
    private String authorizationToken;

    public PlayerRegisteredEvent(String authorizationToken) {
        this.authorizationToken = authorizationToken;
    }

    public String getAuthorizationToken() {
        return authorizationToken;
    }
}
