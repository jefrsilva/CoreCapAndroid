package br.com.loneseal.corecapandroid.model.instruction;

import java.io.Serializable;

import br.com.loneseal.corecapandroid.model.operand.Operand;
import br.com.loneseal.corecapandroid.model.operand.VoidOperand;

/**
 * Created by jefrsilva on 20/05/2016.
 */

public class POP extends Instruction implements Serializable {
    private Operand.Type[][] operandTypes =
            {{Operand.Type.REGISTER}};

    public POP() {
        super("POP", new VoidOperand());
    }

    @Override
    public Operand.Type[] getOperandTypes(int position) {
        return operandTypes[position];
    }

    @Override
    public String getDescription() {
        return "POP A - Pops the value at the top of the stack and stores it in A.";
    }
}
