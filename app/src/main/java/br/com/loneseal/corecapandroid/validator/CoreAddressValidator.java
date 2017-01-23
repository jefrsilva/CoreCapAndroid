package br.com.loneseal.corecapandroid.validator;

/**
 * Created by jefrsilva on 02/06/16.
 */
public class CoreAddressValidator {

    public static boolean validate(String addressText) {
        if (addressText.isEmpty()) {
            return false;
        }

        int address = Integer.parseInt(addressText);
        if (address < 0 || address > 399) {
            return false;
        }

        return true;
    }
}
