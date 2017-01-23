package br.com.loneseal.corecapandroid.activity;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import br.com.loneseal.corecapandroid.CoreHackerApplication;
import br.com.loneseal.corecapandroid.R;
import br.com.loneseal.corecapandroid.event.PlayerRegisterErrorEvent;
import br.com.loneseal.corecapandroid.event.PlayerRegisteredEvent;
import br.com.loneseal.corecapandroid.task.PlayerRegisterTask;
import br.com.loneseal.corecapandroid.validator.ClientIdValidator;
import br.com.loneseal.corecapandroid.validator.IpAddressValidator;
import br.com.loneseal.corecapandroid.validator.PortNumberValidator;

public class ConnectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);

        final EditText serverIpEdit = (EditText) findViewById(R.id.connection_server_ip);
        final EditText serverPortEdit = (EditText) findViewById(R.id.connection_server_port);
        final EditText clientIdEdit = (EditText) findViewById(R.id.connection_client_id);
        final AppCompatSpinner teamSpinner = (AppCompatSpinner) findViewById(R.id.connection_team);

        String[] teams = {"Red Team", "Blue Team", "Green Team" ,"Yellow Team"};
        ArrayAdapter<String> teamAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, teams);
        teamSpinner.setAdapter(teamAdapter);

        Button connectButton = (Button) findViewById(R.id.connection_connect);
        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CoreHackerApplication app = (CoreHackerApplication) getApplication();
                boolean validated = true;

                String serverIp = serverIpEdit.getText().toString();
                if (!IpAddressValidator.validate(serverIp)) {
                    serverIpEdit.setError("Enter a valid IP address (xxx.xxx.xxx.xxx)");
                    validated = false;
                }

                String serverPort = serverPortEdit.getText().toString();
                if (!PortNumberValidator.validate(serverPort)) {
                    serverPortEdit.setError("Enter a valid port number (0-65535)");
                    validated = false;
                }

                String clientId = clientIdEdit.getText().toString();
                if (!ClientIdValidator.validate(clientId)) {
                    clientIdEdit.setError("Enter a valid client id (not empty)");
                    validated = false;
                }

                String teamColor = teamSpinner.getSelectedItem().toString();
                teamColor = teamColor.split(" ")[0].trim().toUpperCase();

                if (validated) {
                    app.setServerIp(serverIpEdit.getText().toString());
                    app.setServerPort(Integer.parseInt(serverPortEdit.getText().toString()));
                    app.setClientId(clientIdEdit.getText().toString());
                    app.setTeamColor(teamColor);

                    new PlayerRegisterTask(app).execute();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void notifyPlayerRegistered(PlayerRegisteredEvent event) {
        CoreHackerApplication app = (CoreHackerApplication) getApplication();
        app.setAuthorizationToken(event.getAuthorizationToken());

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Subscribe
    public void notifyPlayerRegisterError(PlayerRegisterErrorEvent event) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Unable to connect");
        builder.setMessage("Unable to connect to specified game.");
        builder.setCancelable(false);
        builder.show();
    }
}
