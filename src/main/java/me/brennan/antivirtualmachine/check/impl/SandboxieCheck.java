package me.brennan.antivirtualmachine.check.impl;

import com.github.jonatino.process.Module;
import me.brennan.antivirtualmachine.AntiVirtualMachine;
import me.brennan.antivirtualmachine.check.AbstractCheck;

/**
 * @author Brenann
 * @since 4/12/2020
 **/
public class SandboxieCheck extends AbstractCheck {

    public SandboxieCheck() {
        super(CheckType.SHUTDOWN);
    }

    @Override
    public boolean check() {
        final Module module = AntiVirtualMachine.INSTANCE.getCurrentProcess().findModule("SbieDll.dll");
        return module != null;
    }
}
