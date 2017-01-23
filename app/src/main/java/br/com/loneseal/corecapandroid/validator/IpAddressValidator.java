package br.com.loneseal.corecapandroid.validator;

import java.util.regex.Pattern;

/**
 * Created by jefrsilva on 02/06/16.
 */
public class IpAddressValidator {
    private static final Pattern PATTERN = Pattern.compile(
            "^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");

    public static boolean validate(final String ip) {
        return PATTERN.matcher(ip).matches();
    }
}
