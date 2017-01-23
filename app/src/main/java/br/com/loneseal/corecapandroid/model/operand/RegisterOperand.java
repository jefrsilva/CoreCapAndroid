package br.com.loneseal.corecapandroid.model.operand;

import java.io.Serializable;

/**
 * Created by jefrsilva on 20/05/2016.
 */

public class RegisterOperand extends Operand implements Serializable {
    public RegisterOperand(String register) {
        super(Type.REGISTER, register);
    }
}
