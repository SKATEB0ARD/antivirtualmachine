package me.brennan.antivirtualmachine.check.impl;

import me.brennan.antivirtualmachine.check.AbstractCheck;
import me.brennan.antivirtualmachine.utils.WinRegistry;

/**
 * @author Brenann
 * @since 4/12/2020
 **/
public class RegistryCheck extends AbstractCheck {

    public RegistryCheck() {
        super(CheckType.SHUTDOWN);
    }

    @Override
    public boolean check() {
        try {
            return checkVMWare() || checkVMBox();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    private boolean checkVMBox() throws Exception {
        final String videoBiosVersion = WinRegistry
                .readString(WinRegistry.HKEY_LOCAL_MACHINE, "HARDWARE\\DESCRIPTION\\System", "VideoBiosVersion", WinRegistry.KEY_WOW64_64KEY);

        return videoBiosVersion.contains("VirtualBox");
    }

    private boolean checkVMWare() throws Exception {
        final String bios = WinRegistry
                .readString(WinRegistry.HKEY_LOCAL_MACHINE, "HARDWARE\\DESCRIPTION\\System\\BIOS\\", "SystemManufacturer", WinRegistry.KEY_WOW64_64KEY);

        return bios.equalsIgnoreCase("VMWARE");
    }
}
