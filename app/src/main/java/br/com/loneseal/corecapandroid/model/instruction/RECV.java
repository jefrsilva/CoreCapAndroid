package br.com.loneseal.corecapandroid.model.instruction;

import java.io.Serializable;

import br.com.loneseal.corecapandroid.model.operand.Operand;
import br.com.loneseal.corecapandroid.model.operand.VoidOperand;

/**
 * Created by jefrsilva on 20/05/2016.
 */

public class RECV extends Instruction implements Serializable {
    private Operand.Type[][] operandTypes =
            {{Operand.Type.NUMBER, Operand.Type.REGISTER}, {Operand.Type.REGISTER}};

    public RECV() {
        super("RECV", new VoidOperand(), new VoidOperand());
    }

    @Override
    public Operand.Type[] getOperandTypes(int position) {
        return operandTypes[position];
    }

    @Override
    public String getDescription() {
        return "RECV A B - Wait until it receives on channel A and stores the received value on B.";
    }
}
