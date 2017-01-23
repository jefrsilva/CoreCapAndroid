package br.com.loneseal.corecapandroid.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.loneseal.corecapandroid.model.ProgramLine;
import br.com.loneseal.corecapandroid.R;
import br.com.loneseal.corecapandroid.model.instruction.ADD;
import br.com.loneseal.corecapandroid.model.instruction.CAP;
import br.com.loneseal.corecapandroid.model.instruction.CMP;
import br.com.loneseal.corecapandroid.model.instruction.DIV;
import br.com.loneseal.corecapandroid.model.instruction.Instruction;
import br.com.loneseal.corecapandroid.model.instruction.JG;
import br.com.loneseal.corecapandroid.model.instruction.JGE;
import br.com.loneseal.corecapandroid.model.instruction.JL;
import br.com.loneseal.corecapandroid.model.instruction.JLE;
import br.com.loneseal.corecapandroid.model.instruction.JMP;
import br.com.loneseal.corecapandroid.model.instruction.JNZ;
import br.com.loneseal.corecapandroid.model.instruction.JZ;
import br.com.loneseal.corecapandroid.model.instruction.POP;
import br.com.loneseal.corecapandroid.model.instruction.PUSH;
import br.com.loneseal.corecapandroid.model.instruction.RECV;
import br.com.loneseal.corecapandroid.model.instruction.SEND;
import br.com.loneseal.corecapandroid.model.instruction.SET;
import br.com.loneseal.corecapandroid.model.instruction.MUL;
import br.com.loneseal.corecapandroid.model.instruction.NOP;
import br.com.loneseal.corecapandroid.model.instruction.SUB;
import br.com.loneseal.corecapandroid.model.instruction.TRAP;
import br.com.loneseal.corecapandroid.model.operand.InstructionOperand;
import br.com.loneseal.corecapandroid.model.operand.LabelOperand;
import br.com.loneseal.corecapandroid.model.operand.NumberOperand;
import br.com.loneseal.corecapandroid.model.operand.Operand;
import br.com.loneseal.corecapandroid.model.operand.OperandUtil;
import br.com.loneseal.corecapandroid.model.operand.RegisterOperand;
import br.com.loneseal.corecapandroid.model.operand.VoidOperand;

/**
 * Created by jefrsilva on 20/05/2016.
 */

public class ProgramLineAdapter extends BaseAdapter {
    private List<ProgramLine> programLines;

    public ProgramLineAdapter(List<ProgramLine> programLines) {
        this.programLines = programLines;
    }

    @Override
    public int getCount() {
        return programLines.size();
    }

    @Override
    public Object getItem(int position) {
        return programLines.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        final ProgramLine programLine = programLines.get(position);
        final Instruction instruction = programLine.getInstruction();

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_program_line, parent, false);

        CardView labelCardView = (CardView) view.findViewById(R.id.item_program_line_labelCard);
        labelCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLabelEditDialog(view.getContext(), ProgramLineAdapter.this, programLine);
            }
        });
        if (!programLine.getLabel().isEmpty()) {
            labelCardView.setCardBackgroundColor(Color.parseColor("#408040"));

            TextView labelView = (TextView) view.findViewById(R.id.item_program_line_label);
            labelView.setText(programLine.getLabel().toUpperCase());
        }

        CardView instructionCardView = (CardView) view.findViewById(R.id.item_program_line_instructionCard);
        instructionCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInstructionChooserDialog(view.getContext(), ProgramLineAdapter.this, programLine, position);
            }
        });

        TextView instructionView = (TextView) view.findViewById(R.id.item_program_line_instruction);
        instructionView.setText(instruction.getName());


        if (instruction.getOperandQty() >= 1) {
            Operand operand = instruction.getOperand(0);
            CardView operand1CardView = (CardView) view.findViewById(R.id.item_program_line_operand1Card);
            operand1CardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showOperandChooserDialog(view.getContext(), programLine, 0);
                }
            });

            int backgroundColor = OperandUtil.getColorFromOperandType(operand.getType());
            operand1CardView.setCardBackgroundColor(backgroundColor);

            TextView operand1View = (TextView) view.findViewById(R.id.item_program_line_operand1);
            operand1View.setText(instruction.getOperand(0).toString());
        }

        if (instruction.getOperandQty() >= 2) {
            Operand operand = instruction.getOperand(1);
            CardView operand2CardView = (CardView) view.findViewById(R.id.item_program_line_operand2Card);
            operand2CardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showOperandChooserDialog(view.getContext(), programLine, 1);
                }
            });

            int backgroundColor = OperandUtil.getColorFromOperandType(operand.getType());
            operand2CardView.setCardBackgroundColor(backgroundColor);

            TextView operand2View = (TextView) view.findViewById(R.id.item_program_line_operand2);
            operand2View.setText(instruction.getOperand(1).toString());
        }

        CardView beforeButtonView = (CardView) view.findViewById(R.id.item_program_line_beforeButton);
        beforeButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgramLine newLine = new ProgramLine("", new NOP());
                programLines.add(position, newLine);
                notifyDataSetChanged();
            }
        });
        beforeButtonView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (programLines.size() > 1) {
                    programLines.remove(position);
                    notifyDataSetChanged();
                }
                return true;
            }
        });

        CardView afterButtonView = (CardView) view.findViewById(R.id.item_program_line_afterButton);
        afterButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgramLine newLine = new ProgramLine("", new NOP());
                programLines.add(position + 1, newLine);
                notifyDataSetChanged();
            }
        });
        afterButtonView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (programLines.size() > 1) {
                    programLines.remove(position);
                    notifyDataSetChanged();
                }
                return true;
            }
        });

        return view;
    }

    private void showInstructionChooserDialog(Context context, final ProgramLineAdapter adapter, final ProgramLine programLine, final int lineNumber) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose an instruction");

        final View instructionChooserView =
                LayoutInflater.from(context).inflate(R.layout.dialog_instruction_chooser, null);
        builder.setView(instructionChooserView);

        List<Instruction> instructions = getInstructions();

        GridView instructionGrid = (GridView) instructionChooserView.findViewById(R.id.instruction_chooser_grid);
        InstructionAdapter instructionAdapter = new InstructionAdapter(instructions);
        instructionGrid.setAdapter(instructionAdapter);

        final AlertDialog dialog = builder.show();

        instructionGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                try {
                    Instruction instruction = (Instruction) adapterView.getItemAtPosition(position);
                    Instruction newInstruction = instruction.getClass().newInstance();
                    ProgramLine newLine = new ProgramLine(programLine.getLabel(), newInstruction);

                    programLines.remove(lineNumber);
                    programLines.add(lineNumber, newLine);

                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void showInstructionOperandChooserDialog(final Context context, final ProgramLineAdapter adapter, final ProgramLine programLine, final int opPosition) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose an instruction");

        final View instructionChooserView =
                LayoutInflater.from(context).inflate(R.layout.dialog_instruction_chooser, null);
        builder.setView(instructionChooserView);

        List<Instruction> instructions = getInstructions();

        GridView instructionGrid = (GridView) instructionChooserView.findViewById(R.id.instruction_chooser_grid);
        InstructionAdapter instructionAdapter = new InstructionAdapter(instructions);
        instructionGrid.setAdapter(instructionAdapter);

        final AlertDialog dialog = builder.show();

        instructionGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Instruction chosenInstruction = (Instruction) adapterView.getItemAtPosition(position);
                programLine.getInstruction().setOperand(opPosition, new InstructionOperand(chosenInstruction));
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
    }

    @NonNull
    private List<Instruction> getInstructions() {
        List<Instruction> instructions = new ArrayList<>();
        instructions.add(new ADD());
        instructions.add(new CAP());
        instructions.add(new CMP());
        instructions.add(new DIV());
        instructions.add(new JG());
        instructions.add(new JGE());
        instructions.add(new JL());
        instructions.add(new JLE());
        instructions.add(new JMP());
        instructions.add(new JNZ());
        instructions.add(new JZ());
        instructions.add(new MUL());
        instructions.add(new NOP());
        instructions.add(new POP());
        instructions.add(new PUSH());
        instructions.add(new RECV());
        instructions.add(new SEND());
        instructions.add(new SET());
        instructions.add(new SUB());
        instructions.add(new TRAP());
        return instructions;
    }

    private void showLabelEditDialog(Context context, final ProgramLineAdapter adapter, final ProgramLine programLine) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Editing label");

        final View labelEditView =
                LayoutInflater.from(context).inflate(R.layout.dialog_label_edit, null);
        builder.setView(labelEditView);

        String oldLabelName = "";
        final EditText labelName = (EditText) labelEditView.findViewById(R.id.label_edit_name);
        if (!programLine.getLabel().isEmpty()) {
            oldLabelName = programLine.getLabel();
            labelName.setText(oldLabelName);
        }

        final AlertDialog dialog = builder.show();

        Button okButton = (Button) labelEditView.findViewById(R.id.label_edit_ok);
        final String finalOldLabelName = oldLabelName;
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newLabelName = labelName.getText().toString().toUpperCase().trim();

                if (!finalOldLabelName.isEmpty()) {
                    for (ProgramLine line : programLines) {
                        Instruction instruction = line.getInstruction();
                        for (int index = 0; index < instruction.getOperandQty(); index++) {
                            Operand operand = instruction.getOperand(index);
                            if (operand.getType() == Operand.Type.LABEL &&
                                    operand.toString().equals(finalOldLabelName)) {
                                if (newLabelName.isEmpty()) {
                                    instruction.setOperand(index, new VoidOperand());
                                } else {
                                    instruction.setOperand(index, new LabelOperand(newLabelName));
                                }
                            }
                        }
                    }
                }

                programLine.setLabel(newLabelName);
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
    }

    private void showOperandChooserDialog(final Context context, final ProgramLine programLine, final int opPosition) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose an operand type");

        View operandChooserView =
                LayoutInflater.from(context).inflate(R.layout.dialog_operand_chooser, null);
        builder.setView(operandChooserView);

        Instruction instruction = programLine.getInstruction();
        OperandAdapter operandAdapter = new OperandAdapter(instruction.getOperandTypes(opPosition));
        GridView gridView = (GridView) operandChooserView.findViewById(R.id.operand_chooser_grid);
        gridView.setAdapter(operandAdapter);

        final AlertDialog dialog = builder.show();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Operand.Type type = (Operand.Type) adapterView.getItemAtPosition(position);

                switch (type) {
                    case NUMBER:
                        dialog.dismiss();
                        showNumberEditDialog(context, ProgramLineAdapter.this, programLine, opPosition);
                        break;

                    case REGISTER:
                        dialog.dismiss();
                        showRegisterChooserDialog(context, ProgramLineAdapter.this, programLine, opPosition);
                        break;

                    case LABEL:
                        dialog.dismiss();
                        showLabelChooserDialog(context, ProgramLineAdapter.this, programLine, opPosition);
                        break;

                    case INSTRUCTION:
                        dialog.dismiss();
                        showInstructionOperandChooserDialog(context, ProgramLineAdapter.this, programLine, opPosition);
                        break;
                }
            }
        });

    }

    private void showLabelChooserDialog(final Context context, final ProgramLineAdapter adapter, final ProgramLine programLine, final int opPosition) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose a label");

        View labelChooserView =
                LayoutInflater.from(context).inflate(R.layout.dialog_label_chooser, null);
        builder.setView(labelChooserView);

        List<String> labelNames = new ArrayList<>();
        for (ProgramLine line : programLines) {
            if (!line.getLabel().isEmpty()) {
                labelNames.add(line.getLabel());
            }
        }

        LabelAdapter labelAdapter = new LabelAdapter(labelNames);
        GridView gridView = (GridView) labelChooserView.findViewById(R.id.label_chooser_grid);
        gridView.setAdapter(labelAdapter);

        final AlertDialog dialog = builder.show();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String chosenLabel = (String) adapterView.getItemAtPosition(position);
                programLine.getInstruction().setOperand(opPosition, new LabelOperand(chosenLabel));
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });

    }

    private void showRegisterChooserDialog(final Context context, final ProgramLineAdapter adapter, final ProgramLine programLine, final int opPosition) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose a register");

        View registerChooserView =
                LayoutInflater.from(context).inflate(R.layout.dialog_register_chooser, null);
        builder.setView(registerChooserView);

        List<RegisterOperand> registers = new ArrayList<>();
        registers.add(new RegisterOperand("#A"));
        registers.add(new RegisterOperand("#B"));
        registers.add(new RegisterOperand("#C"));
        registers.add(new RegisterOperand("#D"));
        registers.add(new RegisterOperand("#POS"));
        registers.add(new RegisterOperand("#RES"));
        registers.add(new RegisterOperand("#TEAM"));

        RegisterAdapter registerAdapter = new RegisterAdapter(registers);
        GridView gridView = (GridView) registerChooserView.findViewById(R.id.register_chooser_grid);
        gridView.setAdapter(registerAdapter);

        final AlertDialog dialog = builder.show();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                RegisterOperand registerOperand = (RegisterOperand) adapterView.getItemAtPosition(position);
                programLine.getInstruction().setOperand(opPosition, registerOperand);
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });

    }

    private void showNumberEditDialog(Context context, final ProgramLineAdapter adapter, final ProgramLine programLine, final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Editing number");

        final View numberValueView =
                LayoutInflater.from(context).inflate(R.layout.dialog_number_edit, null);
        builder.setView(numberValueView);

        final EditText numberValue = (EditText) numberValueView.findViewById(R.id.number_edit_value);

        Operand operand = programLine.getInstruction().getOperand(position);
        if (operand.getType() == Operand.Type.NUMBER) {
            numberValue.setText(operand.toString());
        }

        final AlertDialog dialog = builder.show();

        Button okButton = (Button) numberValueView.findViewById(R.id.number_edit_ok);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Instruction instruction = programLine.getInstruction();
                String numberText = numberValue.getText().toString();
                if (numberText.isEmpty()) {
                    numberText = "0";
                }
                NumberOperand numberOperand = new NumberOperand(Integer.parseInt(numberText));
                instruction.setOperand(position, numberOperand);

                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
    }

}
