package br.com.loneseal.corecapandroid.model;

import java.io.Serializable;

/**
 * Created by jefrsilva on 02/06/16.
 */
public class Cpu implements Serializable {
    private String name;
    private Program program;

    public Cpu(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }
}
