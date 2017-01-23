package br.com.loneseal.corecapandroid.model.instruction;

import java.io.Serializable;

import br.com.loneseal.corecapandroid.model.operand.Operand;
import br.com.loneseal.corecapandroid.model.operand.VoidOperand;

/**
 * Created by jefrsilva on 20/05/2016.
 */

public class SEND extends Instruction implements Serializable {
    private Operand.Type[][] operandTypes =
            {{Operand.Type.NUMBER, Operand.Type.REGISTER}, {Operand.Type.NUMBER, Operand.Type.REGISTER}};

    public SEND() {
        super("SEND", new VoidOperand(), new VoidOperand());
    }

    @Override
    public Operand.Type[] getOperandTypes(int position) {
        return operandTypes[position];
    }

    @Override
    public String getDescription() {
        return "SEND A B - Sends the value in B to any program receiving on channel A.";
    }
}
