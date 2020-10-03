package me.brennan.antivirtualmachine;

import com.github.jonatino.process.Process;
import com.github.jonatino.process.Processes;
import com.sun.jna.platform.win32.Kernel32;
import me.brennan.antivirtualmachine.check.CheckManager;

/**
 * @author Brenann
 * @since 4/12/2020
 **/
public enum AntiVirtualMachine {
    INSTANCE;

    private Process currentProcess;

    public void start() {
        final int pid = Kernel32.INSTANCE.GetCurrentProcessId();
        currentProcess = Processes.byId(pid);
        currentProcess.initModules();

        final CheckManager checkManager = new CheckManager();
        checkManager.runChecks();
    }

    public Process getCurrentProcess() {
        return currentProcess;
    }

}
