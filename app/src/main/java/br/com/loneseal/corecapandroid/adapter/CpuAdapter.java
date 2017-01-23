package br.com.loneseal.corecapandroid.adapter;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

import br.com.loneseal.corecapandroid.CoreHackerApplication;
import br.com.loneseal.corecapandroid.R;
import br.com.loneseal.corecapandroid.model.Cpu;
import br.com.loneseal.corecapandroid.model.CpuUtil;

/**
 * Created by jefrsilva on 02/06/16.
 */
public class CpuAdapter extends BaseAdapter {
    private CoreHackerApplication app;
    private List<Cpu> cpus;

    public CpuAdapter(CoreHackerApplication app) {
        this.app = app;
        this.cpus = app.getCpus();
    }

    @Override
    public int getCount() {
        return cpus.size();
    }

    @Override
    public Object getItem(int position) {
        return cpus.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cpu, viewGroup, false);

        Cpu cpu = cpus.get(position);

        CardView cpuCard = (CardView) view.findViewById(R.id.item_cpu_view);

        TextView cpuNameText = (TextView) view.findViewById(R.id.item_cpu_name);
        cpuNameText.setText(cpu.getName());

        TextView cpuTaskText = (TextView) view.findViewById(R.id.item_cpu_task);
        if (cpu.getProgram() != null) {
            cpuCard.setCardBackgroundColor(CpuUtil.getColorFromTeamColor(app.getTeamColor()));
            cpuTaskText.setText(cpu.getProgram().getName());
        } else {
            cpuCard.setCardBackgroundColor(Color.parseColor("#404040"));
            cpuTaskText.setText("Idle");
        }

        return view;
    }
}
