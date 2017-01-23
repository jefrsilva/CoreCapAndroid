package br.com.loneseal.corecapandroid.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jefrsilva on 02/06/16.
 */
public class Program implements Serializable {
    private static int nextId = 0;

    private int id;
    private String name;
    private List<ProgramLine> lines;

    public Program() {
        this.id = nextId++;
        this.lines = new ArrayList<>();
    }

    public Program(String name, List<ProgramLine> lines) {
        this.id = nextId++;
        this.name = name;
        this.lines = lines;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProgramLine> getLines() {
        return lines;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Program) {
            Program program = (Program) o;
            if (this.id == program.id) {
                return true;
            }
        }
        return false;
    }
}
