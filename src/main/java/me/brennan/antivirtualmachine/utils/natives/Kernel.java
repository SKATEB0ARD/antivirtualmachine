package me.brennan.antivirtualmachine.utils.natives;

import com.sun.jna.Native;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;

public interface Kernel extends StdCallLibrary {
    Kernel INSTANCE = Native.loadLibrary("kernel32", Kernel.class, W32APIOptions.DEFAULT_OPTIONS);


    boolean IsDebuggerPresent();

}
