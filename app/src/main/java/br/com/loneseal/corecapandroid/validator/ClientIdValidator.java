package br.com.loneseal.corecapandroid.validator;

/**
 * Created by jefrsilva on 02/06/16.
 */
public class ClientIdValidator {

    public static boolean validate(String id) {
        return !id.isEmpty();
    }
}
