package br.com.loneseal.corecapandroid.validator;

/**
 * Created by jefrsilva on 02/06/16.
 */
public class PortNumberValidator {

    public static boolean validate(String port) {
        try {
            int portNumber = Integer.parseInt(port);
            if (portNumber < 0 || portNumber > 65535) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

}
