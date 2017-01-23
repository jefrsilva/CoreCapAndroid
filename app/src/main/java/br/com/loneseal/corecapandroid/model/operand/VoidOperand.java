package br.com.loneseal.corecapandroid.model.operand;

import java.io.Serializable;

/**
 * Created by jefrsilva on 30/05/16.
 */
public class VoidOperand extends Operand implements Serializable {
    public VoidOperand() {
        super(Type.VOID, "VOID");
    }
}
