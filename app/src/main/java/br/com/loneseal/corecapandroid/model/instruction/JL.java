package br.com.loneseal.corecapandroid.model.instruction;

import java.io.Serializable;

import br.com.loneseal.corecapandroid.model.operand.LabelOperand;
import br.com.loneseal.corecapandroid.model.operand.Operand;
import br.com.loneseal.corecapandroid.model.operand.VoidOperand;

/**
 * Created by jefrsilva on 20/05/2016.
 */

public class JL extends Instruction implements Serializable {
    private Operand.Type[][] operandTypes =
            {{Operand.Type.LABEL}};

    public JL() {
        super("JL", new VoidOperand());
    }

    @Override
    public Operand.Type[] getOperandTypes(int position) {
        return operandTypes[position];
    }

    @Override
    public String getDescription() {
        return "JL LABEL - Jumps to LABEL if the last stored value in a register was less than 0 or if A < B in the last CMP A B.";
    }
}
