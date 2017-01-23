package br.com.loneseal.corecapandroid.adapter;

import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import br.com.loneseal.corecapandroid.R;
import br.com.loneseal.corecapandroid.model.operand.Operand;
import br.com.loneseal.corecapandroid.model.operand.OperandUtil;

/**
 * Created by jefrsilva on 29/05/16.
 */
public class OperandAdapter extends BaseAdapter {
    private Operand.Type[] operandTypes;

    public OperandAdapter(Operand.Type[] operandTypes) {
        this.operandTypes = operandTypes;
    }

    @Override
    public int getCount() {
        return operandTypes.length;
    }

    @Override
    public Object getItem(int position) {
        return operandTypes[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View itemView =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);

        Operand.Type type = operandTypes[position];

        CardView cardView = (CardView) itemView.findViewById(R.id.item_card_view);
        cardView.setCardBackgroundColor(OperandUtil.getColorFromOperandType(type));

        TextView cardText = (TextView) itemView.findViewById(R.id.item_card_text);
        cardText.setText(type.toString());

        return itemView;
    }
}
