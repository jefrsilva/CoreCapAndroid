package br.com.loneseal.corecapandroid.model.instruction;

import java.io.Serializable;

import br.com.loneseal.corecapandroid.model.operand.Operand;

/**
 * Created by jefrsilva on 20/05/2016.
 */

public class CAP extends Instruction implements Serializable {
    public CAP() {
        super("CAP");
    }

    @Override
    public Operand.Type[] getOperandTypes(int position) {
        return null;
    }

    @Override
    public String getDescription() {
        return "CAP - Attempts to capture the core in the current program location.";
    }
}
