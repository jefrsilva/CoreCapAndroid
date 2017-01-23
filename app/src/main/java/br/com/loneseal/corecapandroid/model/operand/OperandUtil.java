package br.com.loneseal.corecapandroid.model.operand;

import android.graphics.Color;

/**
 * Created by jefrsilva on 30/05/16.
 */
public class OperandUtil {
    public static int getColorFromOperandType(Operand.Type type) {
        int backgroundColor = Color.parseColor("#707070");
        switch (type) {
            case VOID:
                backgroundColor = Color.parseColor("#804040");
                break;
            case LABEL:
                backgroundColor = Color.parseColor("#408040");
                break;
            case NUMBER:
                backgroundColor = Color.parseColor("#404080");
                break;
            case REGISTER:
                backgroundColor = Color.parseColor("#808040");
                break;
            case INSTRUCTION:
                backgroundColor = Color.parseColor("#804080");
                break;
        }
        return backgroundColor;
    }
}
