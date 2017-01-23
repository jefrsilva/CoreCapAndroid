package br.com.loneseal.corecapandroid.model.instruction;

import java.io.Serializable;

import br.com.loneseal.corecapandroid.model.operand.InstructionOperand;
import br.com.loneseal.corecapandroid.model.operand.LabelOperand;
import br.com.loneseal.corecapandroid.model.operand.NumberOperand;
import br.com.loneseal.corecapandroid.model.operand.Operand;
import br.com.loneseal.corecapandroid.model.operand.RegisterOperand;
import br.com.loneseal.corecapandroid.model.operand.VoidOperand;

/**
 * Created by jefrsilva on 20/05/2016.
 */

public class TRAP extends Instruction implements Serializable {
    private Operand.Type[][] operandTypes =
            {{Operand.Type.INSTRUCTION}, {Operand.Type.LABEL}};

    public TRAP() {
        super("TRAP", new VoidOperand(), new VoidOperand());
    }

    @Override
    public Operand.Type[] getOperandTypes(int position) {
        return operandTypes[position];
    }

    @Override
    public String getDescription() {
        return "TRAP I LABEL - Install a trap for instruction I in the current core. If instruction I is executed in this core then execute the instruction on LABEL instead of I.";
    }
}
