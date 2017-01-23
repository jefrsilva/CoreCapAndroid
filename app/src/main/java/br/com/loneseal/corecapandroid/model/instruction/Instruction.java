package br.com.loneseal.corecapandroid.model.instruction;

import java.io.Serializable;

import br.com.loneseal.corecapandroid.model.operand.Operand;

/**
 * Created by jefrsilva on 20/05/2016.
 */
public abstract class Instruction implements Serializable {
    private String name;
    private Operand[] operands;

    public Instruction(String name) {
        this.name = name;
        this.operands = new Operand[0];
    }

    public Instruction(String name, Operand... operands) {
        this(name);
        this.operands = new Operand[operands.length];
        for (int i = 0; i < operands.length; i++) {
            this.operands[i] = operands[i];
        }
    }

    public String getName() {
        return name;
    }

    public int getOperandQty() {
        return operands.length;
    }
    public void setOperand(int index, Operand operand) {
        if (index < operands.length) {
            this.operands[index] = operand;
        }
    }

    public Operand getOperand(int index) {
        return operands[index];
    }

    public abstract Operand.Type[] getOperandTypes(int position);

    @Override
    public String toString() {
        return "@" + getName();
    }

    public abstract String getDescription();
}
