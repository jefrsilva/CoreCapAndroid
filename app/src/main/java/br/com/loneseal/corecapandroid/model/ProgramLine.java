package br.com.loneseal.corecapandroid.model;


import java.io.Serializable;

import br.com.loneseal.corecapandroid.model.instruction.Instruction;

/**
 * Created by jefrsilva on 20/05/2016.
 */

public class ProgramLine implements Serializable {
    private String label;
    private Instruction instruction;

    public ProgramLine(String label, Instruction instruction) {
        this.label = label;
        this.instruction = instruction;
    }

    public ProgramLine(Instruction nop) {
        this("", nop);
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Instruction getInstruction() {
        return instruction;
    }

    public void setInstruction(Instruction instruction) {
        this.instruction = instruction;
    }
}
