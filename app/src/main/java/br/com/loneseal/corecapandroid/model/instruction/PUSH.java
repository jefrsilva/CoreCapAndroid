package br.com.loneseal.corecapandroid.model.instruction;

import java.io.Serializable;

import br.com.loneseal.corecapandroid.model.operand.Operand;
import br.com.loneseal.corecapandroid.model.operand.VoidOperand;

/**
 * Created by jefrsilva on 20/05/2016.
 */

public class PUSH extends Instruction implements Serializable {
    private Operand.Type[][] operandTypes =
            {{Operand.Type.NUMBER, Operand.Type.REGISTER}};

    public PUSH() {
        super("PUSH", new VoidOperand());
    }

    @Override
    public Operand.Type[] getOperandTypes(int position) {
        return operandTypes[position];
    }

    @Override
    public String getDescription() {
        return "PUSH A - Pushes value A into the top of the stack.";
    }
}
