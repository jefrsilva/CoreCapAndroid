package br.com.loneseal.corecapandroid.model.operand;

import java.io.Serializable;

/**
 * Created by jefrsilva on 20/05/2016.
 */
public class Operand implements Serializable {
    public enum Type {
        NUMBER, REGISTER, LABEL, INSTRUCTION, VOID
    }

    private Type type;
    private Object value;

    public Operand(Type type, Object value) {
        this.type = type;
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
