package br.com.loneseal.corecapandroid.event;

import br.com.loneseal.corecapandroid.model.Program;

/**
 * Created by jefrsilva on 05/06/16.
 */
public class ProgramUploadedEvent {
    private int cpuIndex;
    private Program program;

    public ProgramUploadedEvent(int cpuIndex, Program program) {
        this.cpuIndex = cpuIndex;
        this.program = program;
    }

    public int getCpuIndex() {
        return cpuIndex;
    }

    public Program getProgram() {
        return program;
    }
}
