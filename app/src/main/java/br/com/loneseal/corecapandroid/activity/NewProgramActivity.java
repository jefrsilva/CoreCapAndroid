package br.com.loneseal.corecapandroid.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import br.com.loneseal.corecapandroid.R;
import br.com.loneseal.corecapandroid.adapter.ProgramLineAdapter;
import br.com.loneseal.corecapandroid.model.Program;
import br.com.loneseal.corecapandroid.model.ProgramLine;
import br.com.loneseal.corecapandroid.model.instruction.NOP;
import br.com.loneseal.corecapandroid.validator.ProgramNameValidator;

public class NewProgramActivity extends AppCompatActivity {

    private Program program;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_program);

        final EditText nameEdit = (EditText) findViewById(R.id.new_program_nameEdit);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("program")) {
            program = (Program) intent.getSerializableExtra("program");
            nameEdit.setText(program.getName());
        } else {
            program = new Program();
        }

        final List<ProgramLine> programLines = program.getLines();
        if (programLines.isEmpty()) {
            programLines.add(new ProgramLine(new NOP()));
        }
        ProgramLineAdapter adapter = new ProgramLineAdapter(programLines);

        ListView programInstructionsList = (ListView) findViewById(R.id.new_program_instruction_list);
        programInstructionsList.setAdapter(adapter);

        Button saveButton = (Button) findViewById(R.id.new_program_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String programName = nameEdit.getText().toString();
                if (!ProgramNameValidator.validate(programName)) {
                    nameEdit.setError("Enter a valid program name (not empty)");
                    return;
                }
                program.setName(programName);

                Intent resultIntent = new Intent();
                resultIntent.putExtra("program", program);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
