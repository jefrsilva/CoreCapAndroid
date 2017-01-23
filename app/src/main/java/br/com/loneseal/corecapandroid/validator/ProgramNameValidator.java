package br.com.loneseal.corecapandroid.validator;

/**
 * Created by jefrsilva on 02/06/16.
 */
public class ProgramNameValidator {

    public static boolean validate(String programName) {
        return !programName.isEmpty();
    }
}
