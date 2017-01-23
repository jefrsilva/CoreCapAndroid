package br.com.loneseal.corecapandroid.model;

import android.graphics.Color;

import br.com.loneseal.corecapandroid.model.operand.Operand;

/**
 * Created by jefrsilva on 12/06/16.
 */
public class CpuUtil {
    public static int getColorFromTeamColor(String teamColor) {
        int backgroundColor = Color.parseColor("#707070");
        switch (teamColor) {
            case "RED":
                backgroundColor = Color.parseColor("#804040");
                break;
            case "GREEN":
                backgroundColor = Color.parseColor("#408040");
                break;
            case "BLUE":
                backgroundColor = Color.parseColor("#404080");
                break;
            case "YELLOW":
                backgroundColor = Color.parseColor("#808040");
                break;
        }
        return backgroundColor;
    }
}
