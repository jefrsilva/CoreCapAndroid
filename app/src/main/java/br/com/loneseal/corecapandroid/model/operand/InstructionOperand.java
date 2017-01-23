package br.com.loneseal.corecapandroid.model.operand;

import java.io.Serializable;

import br.com.loneseal.corecapandroid.model.instruction.Instruction;

/**
 * Created by jefrsilva on 20/05/2016.
 */

public class InstructionOperand extends Operand implements Serializable {
    public InstructionOperand(Instruction instruction) {
        super(Type.INSTRUCTION, instruction);
    }
}
