package br.com.loneseal.corecapandroid.model.instruction;

import java.io.Serializable;

import br.com.loneseal.corecapandroid.model.operand.NumberOperand;
import br.com.loneseal.corecapandroid.model.operand.Operand;
import br.com.loneseal.corecapandroid.model.operand.RegisterOperand;
import br.com.loneseal.corecapandroid.model.operand.VoidOperand;

/**
 * Created by jefrsilva on 20/05/2016.
 */

public class MUL extends Instruction implements Serializable {
    private Operand.Type[][] operandTypes =
            {{Operand.Type.REGISTER}, {Operand.Type.NUMBER, Operand.Type.REGISTER}};

    public MUL() {
        super("MUL", new VoidOperand(), new VoidOperand());
    }

    @Override
    public Operand.Type[] getOperandTypes(int position) {
        return operandTypes[position];
    }

    @Override
    public String getDescription() {
        return "MUL A B - Multiplies A by B and stores the result in A. (A = A * B)";
    }
}
