package br.com.loneseal.corecapandroid.model.instruction;

import java.io.Serializable;

import br.com.loneseal.corecapandroid.model.operand.Operand;

/**
 * Created by jefrsilva on 20/05/2016.
 */

public class NOP extends Instruction implements Serializable {
    public NOP() {
        super("NOP");
    }

    @Override
    public Operand.Type[] getOperandTypes(int position) {
        return null;
    }

    @Override
    public String getDescription() {
        return "NOP - Do nothing.";
    }
}
