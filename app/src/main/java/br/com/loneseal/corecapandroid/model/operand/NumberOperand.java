package br.com.loneseal.corecapandroid.model.operand;

import java.io.Serializable;

/**
 * Created by jefrsilva on 20/05/2016.
 */

public class NumberOperand extends Operand implements Serializable {
    public NumberOperand(int number) {
        super(Type.NUMBER, number);
    }
}
