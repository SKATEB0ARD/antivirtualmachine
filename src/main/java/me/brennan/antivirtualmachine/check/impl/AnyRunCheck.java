package me.brennan.antivirtualmachine.check.impl;

import me.brennan.antivirtualmachine.check.AbstractCheck;
import me.brennan.antivirtualmachine.utils.Utils;

import java.io.File;

/**
 * @author Brenann
 * @since 4/12/2020
 **/
public class AnyRunCheck extends AbstractCheck {
    private final byte[] anyRunx64 = {(byte)0x53, (byte)0x48, (byte)0x83,
            (byte)0xEC, (byte)0x20, (byte)0xE8, (byte)0x46, (byte)0xEA, (byte)0xFF,
            (byte)0xFF, (byte)0x48, (byte)0x8B, (byte)0x1D, (byte)0xAB, (byte)0x55, (byte)0x00,
            (byte)0x00, (byte)0xB9, (byte)0xF4, (byte)0x11, (byte)0x00, (byte)0x00, (byte)0xFF, (byte)0xD3, (byte)0xEB, (byte)0xF7};
    private final byte[] anyRunx86 = {(byte)0x8D, (byte)0x4C, (byte)0x24,
            (byte)0x04, (byte)0x83, (byte)0xE4,
            (byte)0xF0, (byte)0xFF, (byte)0x71, (byte)0xFC, (byte)0x55, (byte)0x89, (byte)0xE5, (byte)0x51, (byte)0x83, (byte)0xEC, (byte)0x14,
            (byte)0xE8, (byte)0xDA, (byte)0xFC, (byte)0xFF, (byte)0xFF, (byte)0x8D, (byte)0x76, (byte)0x00, (byte)0x8D, (byte)0xBC, (byte)0x27, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xC7, (byte)0x04, (byte)0x24, (byte)0xF4, (byte)0x01, (byte)0x00, (byte)0x00,
            (byte)0xE8, (byte)0x84, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0x83, (byte)0xEC, (byte)0x04, (byte)0xEB, (byte)0xEF};

    public AnyRunCheck() {
        super(CheckType.SHUTDOWN);
    }

    @Override
    public boolean check() {
        try {
            final String systemDir = System.getenv("WINDIR") + File.separator + "system32";
            final File f = new File(systemDir, "windanr.exe");
            if (!f.exists())
                return false;
            return Utils.searchSig(f, (System.getenv("ProgramFiles(x86)") != null) ? anyRunx64 : anyRunx86);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}
