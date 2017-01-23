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

/**
 * Created by jefrsilva on 30/05/16.
 */
public class LabelAdapter extends BaseAdapter {
    private List<String> labels;

    public LabelAdapter(List<String> labels) {
        this.labels = labels;
    }

    @Override
    public int getCount() {
        return labels.size();
    }

    @Override
    public Object getItem(int position) {
        return labels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View itemView =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);

        String labelName = labels.get(position);

        CardView cardView = (CardView) itemView.findViewById(R.id.item_card_view);
        cardView.setCardBackgroundColor(OperandUtil.getColorFromOperandType(Operand.Type.LABEL));

        TextView cardText = (TextView) itemView.findViewById(R.id.item_card_text);
        cardText.setText(labelName);

        return itemView;
    }
}
