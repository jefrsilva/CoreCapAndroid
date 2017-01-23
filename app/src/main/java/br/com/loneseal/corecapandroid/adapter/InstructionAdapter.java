package br.com.loneseal.corecapandroid.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.loneseal.corecapandroid.R;
import br.com.loneseal.corecapandroid.model.instruction.Instruction;

/**
 * Created by jefrsilva on 30/05/16.
 */
public class InstructionAdapter extends BaseAdapter {
    private List<Instruction> instructions;

    public InstructionAdapter(List<Instruction> instructions) {
        this.instructions = instructions;
    }

    @Override
    public int getCount() {
        return instructions.size();
    }

    @Override
    public Object getItem(int position) {
        return instructions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View itemView =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);

        Instruction instruction = instructions.get(position);

        TextView cardText = (TextView) itemView.findViewById(R.id.item_card_text);
        cardText.setText(instruction.getName());

        return itemView;
    }
}
