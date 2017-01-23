package br.com.loneseal.corecapandroid;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import br.com.loneseal.corecapandroid.model.Cpu;
import br.com.loneseal.corecapandroid.model.Program;

/**
 * Created by jefrsilva on 02/06/16.
 */
public class CoreHackerApplication extends Application {
    private String serverIp;
    private int serverPort;
    private String clientId;
    private List<Program> programs;
    private List<Cpu> cpus;
    private String teamColor;
    private String authorizationToken;

    @Override
    public void onCreate() {
        super.onCreate();
        programs = new ArrayList<>();

        cpus = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            cpus.add(new Cpu("CPU " + (i + 1)));
        }
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public List<Program> getPrograms() {
        return programs;
    }

    public List<Cpu> getCpus() {
        return cpus;
    }

    public void setTeamColor(String teamColor) {
        this.teamColor = teamColor;
    }

    public String getTeamColor() {
        return teamColor;
    }

    public void setAuthorizationToken(String authorizationToken) {
        this.authorizationToken = authorizationToken;
    }

    public String getAuthorizationToken() {
        return authorizationToken;
    }
}
