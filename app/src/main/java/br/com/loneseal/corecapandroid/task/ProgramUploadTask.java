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
import br.com.loneseal.corecapandroid.event.ProgramUploadedEvent;
import br.com.loneseal.corecapandroid.model.Program;
import br.com.loneseal.corecapandroid.model.ProgramLine;
import br.com.loneseal.corecapandroid.model.instruction.Instruction;

/**
 * Created by jefrsilva on 02/06/16.
 */
public class ProgramUploadTask extends AsyncTask<Void, Void, String> {
    private CoreHackerApplication app;
    private int cpuIndex;
    private int address;
    private Program program;

    public ProgramUploadTask(CoreHackerApplication app, int cpuIndex, int address, Program program) {
        this.app = app;
        this.cpuIndex = cpuIndex;
        this.address = address;
        this.program = program;
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            Socket socket = new Socket(app.getServerIp(), app.getServerPort());
            PrintStream ps = new PrintStream(socket.getOutputStream());

            Log.i("FOCAMAROTA", "Sendind token...");
            ps.println("ID " + app.getAuthorizationToken());
            ps.flush();

            Scanner scanner = new Scanner(socket.getInputStream());
            String response = scanner.nextLine().trim();
            Log.i("FOCAMAROTA", "Token response... " + response);

            if (!response.equals("OK")) {
                Log.e("FOCAMAROTA", "Não deu prá autenticar");
                socket.close();
                return null;
            }

            ps.println("UPLOAD " + cpuIndex + " " + address);

            for (ProgramLine programLine : program.getLines()) {
                String command = "";
                if (!programLine.getLabel().isEmpty()) {
                    command += programLine.getLabel() + ": ";
                }

                Instruction instruction = programLine.getInstruction();
                command += instruction.getName() + " ";
                for (int i = 0; i < instruction.getOperandQty(); i++) {
                    if (i > 0) {
                        command += ",";
                    }
                    command += instruction.getOperand(i).toString();
                }

                ps.println(command);
            }
            ps.println("END");

            Log.i("FOCAMAROTA", "Waiting response...");

            String newResponse = scanner.nextLine();
            while (newResponse.isEmpty()) {
                newResponse = scanner.nextLine();
            }
            Log.i("FOCAMAROTA", "Response received! " + newResponse);

            socket.close();
            return newResponse.trim();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String response) {
        if (response != null && response.equals("OK")) {
            ProgramUploadedEvent event = new ProgramUploadedEvent(cpuIndex, program);
            EventBus.getDefault().post(event);
        }
    }
}
