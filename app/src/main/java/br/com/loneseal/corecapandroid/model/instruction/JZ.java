package br.com.loneseal.corecapandroid.model.instruction;

import java.io.Serializable;

import br.com.loneseal.corecapandroid.model.operand.LabelOperand;
import br.com.loneseal.corecapandroid.model.operand.Operand;
import br.com.loneseal.corecapandroid.model.operand.VoidOperand;

/**
 * Created by jefrsilva on 20/05/2016.
 */

public class JZ extends Instruction implements Serializable {
    private Operand.Type[][] operandTypes =
            {{Operand.Type.LABEL}};

    public JZ() {
        super("JZ", new VoidOperand());
    }

    @Override
    public Operand.Type[] getOperandTypes(int position) {
        return operandTypes[position];
    }

    @Override
    public String getDescription() {
        return "JZ LABEL - Jumps to LABEL if the last stored value in a register was equal to 0 or if A was equal to B in the last CMP A B.";
    }
}
