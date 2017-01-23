package br.com.loneseal.corecapandroid.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.ListViewCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SpinnerAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import br.com.loneseal.corecapandroid.CoreHackerApplication;
import br.com.loneseal.corecapandroid.R;
import br.com.loneseal.corecapandroid.adapter.CpuAdapter;
import br.com.loneseal.corecapandroid.event.ProgramUploadedEvent;
import br.com.loneseal.corecapandroid.model.Cpu;
import br.com.loneseal.corecapandroid.model.Program;
import br.com.loneseal.corecapandroid.model.ProgramLine;
import br.com.loneseal.corecapandroid.model.instruction.Instruction;
import br.com.loneseal.corecapandroid.model.operand.LabelOperand;
import br.com.loneseal.corecapandroid.model.operand.Operand;
import br.com.loneseal.corecapandroid.model.operand.VoidOperand;
import br.com.loneseal.corecapandroid.task.ProgramUploadTask;
import br.com.loneseal.corecapandroid.validator.CoreAddressValidator;

public class MainActivity extends AppCompatActivity {

    private static final int NEW_PROGRAM = 1;
    private ArrayAdapter<Program> programAdapter;
    private CpuAdapter cpuAdapter;
    private List<Program> programs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CoreHackerApplication app = (CoreHackerApplication) getApplication();
        programs = app.getPrograms();

        GridView cpuGridView = (GridView) findViewById(R.id.main_cpu_grid);
        cpuAdapter = new CpuAdapter(app);
        cpuGridView.setAdapter(cpuAdapter);

        cpuGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Cpu cpu = (Cpu) adapterView.getItemAtPosition(position);
                showProgramUploadDialog(MainActivity.this, position);
            }
        });

        ListView programListView = (ListView) findViewById(R.id.main_program_list);

        programAdapter = new ArrayAdapter<Program>(this, android.R.layout.simple_list_item_1, programs);
        programListView.setAdapter(programAdapter);

        programListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Program program = (Program) adapterView.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, NewProgramActivity.class);
                intent.putExtra("program", program);
                startActivityForResult(intent, NEW_PROGRAM);
            }
        });

        FloatingActionButton newProgramButton = (FloatingActionButton) findViewById(R.id.main_new_program);
        newProgramButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), NewProgramActivity.class);
                startActivityForResult(intent, NEW_PROGRAM);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == NEW_PROGRAM) {
            Program program = (Program) data.getSerializableExtra("program");
            programs.remove(program);
            programs.add(program);
            programAdapter.notifyDataSetChanged();
        }
    }

    private void showProgramUploadDialog(Context context, final int cpuIndex) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Uploading program");

        final View programUploadView =
                LayoutInflater.from(context).inflate(R.layout.dialog_program_upload, null);
        builder.setView(programUploadView);

        final AppCompatSpinner programSpinner = (AppCompatSpinner) programUploadView.findViewById(R.id.program_spinner);
        programSpinner.setAdapter(programAdapter);

        final EditText addressEdit = (EditText) programUploadView.findViewById(R.id.program_address);

        final AlertDialog dialog = builder.show();

        Button uploadButton = (Button) programUploadView.findViewById(R.id.program_upload);
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CoreHackerApplication app = (CoreHackerApplication) getApplication();
                Program program = (Program) programSpinner.getSelectedItem();

                String addressText = addressEdit.getText().toString();
                if (!CoreAddressValidator.validate(addressText)) {
                    addressEdit.setError("Enter a valid core address (0-399)");
                    return; 
                }
                int address = Integer.parseInt(addressText);

                new ProgramUploadTask(app, cpuIndex, address, program).execute();
                dialog.dismiss();
            }
        });
    }

    @Subscribe
    public void updateCpuCard(ProgramUploadedEvent event) {
        Cpu cpu = (Cpu) cpuAdapter.getItem(event.getCpuIndex());
        cpu.setProgram(event.getProgram());
        cpuAdapter.notifyDataSetChanged();
    }
}
