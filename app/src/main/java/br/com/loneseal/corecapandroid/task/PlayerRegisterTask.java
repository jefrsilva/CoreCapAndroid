package br.com.loneseal.corecapandroid.task;

import android.os.AsyncTask;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import br.com.loneseal.corecapandroid.CoreHackerApplication;
import br.com.loneseal.corecapandroid.event.PlayerRegisterErrorEvent;
import br.com.loneseal.corecapandroid.event.PlayerRegisteredEvent;
import br.com.loneseal.corecapandroid.event.ProgramUploadedEvent;
import br.com.loneseal.corecapandroid.model.Program;
import br.com.loneseal.corecapandroid.model.ProgramLine;
import br.com.loneseal.corecapandroid.model.instruction.Instruction;

/**
 * Created by jefrsilva on 02/06/16.
 */
public class PlayerRegisterTask extends AsyncTask<Void, Void, String> {
    private CoreHackerApplication app;
    private final String playerName;
    private final String teamColor;

    public PlayerRegisterTask(CoreHackerApplication app) {
        this.app = app;
        this.playerName = app.getClientId();
        this.teamColor = app.getTeamColor();
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            Socket socket = new Socket(app.getServerIp(), app.getServerPort());

            PrintStream ps = new PrintStream(socket.getOutputStream());
            ps.println("REGISTER " + playerName + " " + teamColor);
            ps.flush();

            Log.i("FOCAMAROTA", "Waiting register response...");

            Scanner scanner = new Scanner(socket.getInputStream());
            String response = scanner.nextLine();

            response = response.trim();

            Log.i("FOCAMAROTA", "Register response received! " + response);

            socket.close();
            return response;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String response) {
        if (response != null) {
            String[] responseParts = response.split(" ");
            String errorCode = responseParts[0].trim();
            if (errorCode.equals("OK")) {
                String token = responseParts[1].trim();
                PlayerRegisteredEvent event = new PlayerRegisteredEvent(token);
                EventBus.getDefault().post(event);
            } else {
                PlayerRegisterErrorEvent event = new PlayerRegisterErrorEvent();
                EventBus.getDefault().post(event);
            }
        }
    }
}
