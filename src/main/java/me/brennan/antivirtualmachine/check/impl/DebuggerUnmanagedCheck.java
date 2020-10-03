package me.brennan.antivirtualmachine.check.impl;

import me.brennan.antivirtualmachine.check.AbstractCheck;
import me.brennan.antivirtualmachine.utils.natives.Kernel;

public class DebuggerUnmanagedCheck extends AbstractCheck {

    public DebuggerUnmanagedCheck() {
        super(CheckType.SHUTDOWN);
    }

    @Override
    public boolean check() {
        return Kernel.INSTANCE.IsDebuggerPresent();
    }
}
