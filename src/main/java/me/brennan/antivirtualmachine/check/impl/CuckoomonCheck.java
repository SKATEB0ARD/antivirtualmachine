package me.brennan.antivirtualmachine.check.impl;

import com.github.jonatino.process.Module;
import me.brennan.antivirtualmachine.AntiVirtualMachine;
import me.brennan.antivirtualmachine.check.AbstractCheck;

/**
 * @author Brenann
 * @since 4/12/2020
 **/
public class CuckoomonCheck extends AbstractCheck {

    public CuckoomonCheck() {
        super(CheckType.SHUTDOWN);
    }

    @Override
    public boolean check() {
        final Module module = AntiVirtualMachine.INSTANCE.getCurrentProcess().findModule("cuckoomon.dll");
        return module != null;
    }
}
