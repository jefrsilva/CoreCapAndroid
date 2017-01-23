package br.com.loneseal.corecapandroid.adapter;

import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.loneseal.corecapandroid.R;
import br.com.loneseal.corecapandroid.model.operand.Operand;
import br.com.loneseal.corecapandroid.model.operand.OperandUtil;
import br.com.loneseal.corecapandroid.model.operand.RegisterOperand;

/**
 * Created by jefrsilva on 30/05/16.
 */
public class RegisterAdapter extends BaseAdapter {
    private List<RegisterOperand> registers;

    public RegisterAdapter(List<RegisterOperand> registers) {
        this.registers = registers;
    }

    @Override
    public int getCount() {
        return registers.size();
    }

    @Override
    public Object getItem(int position) {
        return registers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View itemView =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);

        RegisterOperand registerOperand = registers.get(position);

        CardView cardView = (CardView) itemView.findViewById(R.id.item_card_view);
        cardView.setCardBackgroundColor(OperandUtil.getColorFromOperandType(Operand.Type.REGISTER));

        TextView cardText = (TextView) itemView.findViewById(R.id.item_card_text);
        cardText.setText(registerOperand.toString());

        return itemView;
    }
}
