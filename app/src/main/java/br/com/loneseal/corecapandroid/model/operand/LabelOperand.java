package br.com.loneseal.corecapandroid.model.operand;

import java.io.Serializable;

/**
 * Created by jefrsilva on 20/05/2016.
 */

public class LabelOperand extends Operand implements Serializable {
    public LabelOperand(String label) {
        super(Type.LABEL, label);
    }
}
