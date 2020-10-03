package me.brennan.antivirtualmachine.check.impl;

import me.brennan.antivirtualmachine.check.AbstractCheck;
import me.brennan.antivirtualmachine.utils.Utils;

/**
 * @author Brenann
 * @since 4/12/2020
 **/
public class ProcessCheck extends AbstractCheck {
    private static String[] processNames = new String[]{
            "vboxservice.exe",
            "vboxtray.exe",
            "xenservice.exe",
            "vmtoolsd.exe",
            "vmwaretray.exe",
            "vmwareuser.exe",
            "VGAuthService.exe",
            "vmacthlp.exe",
            "VMSrvc.exe",
            "VMUSrvc.exe",
            "prl_cc.exe",
            "prl_tools.exe",
            "qemu-ga.exe",
            "Sandboxie",
            "Program Manager",
            "SbieSvc",
            "VMDragDetectWndClass"
    };

    public ProcessCheck() {
        super(CheckType.FLAG);
    }

    @Override
    public boolean check() {
        try {
            for(String process : processNames)
                return Utils.isProcessRunning(process);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
