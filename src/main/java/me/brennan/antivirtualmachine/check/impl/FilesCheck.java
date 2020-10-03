package me.brennan.antivirtualmachine.check.impl;

import me.brennan.antivirtualmachine.check.AbstractCheck;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author Brenann
 * @since 4/12/2020
 **/
public class FilesCheck extends AbstractCheck {
    private String winDir = System.getenv("windir") + File.separator;
    private String separator = File.separator;

    private String[] files = new String[]{
            //VMBOX
            winDir + "System32" + separator + "drivers" + separator + "VBoxMouse.sys",
            winDir + "System32" + separator + "drivers" + separator + "VBoxGuest.sys",
            winDir + "System32" + separator + "drivers" + separator + "VBoxSF.sys",
            winDir + "System32" + separator + "drivers" + separator + "VBoxVideo.sys",
            winDir + "System32" + separator + "vboxdisp.dll",
            winDir + "System32" + separator + "vboxhook.dll",
            winDir + "System32" + separator + "vboxmrxnp.dll",
            winDir + "System32" + separator + "vboxogl.dll",
            winDir + "System32" + separator + "vboxoglarrayspu.dll",
            winDir + "System32" + separator + "vboxoglcrutil.dll",
            winDir + "System32" + separator + "vboxoglerrorspu.dll",
            winDir + "System32" + separator + "vboxoglfeedbackspu.dll",
            winDir + "System32" + separator + "vboxoglpackspu.dll",
            winDir + "System32" + separator + "vboxoglpassthroughspu.dll",
            winDir + "System32" + separator + "vboxservice.exe",
            winDir + "System32" + separator + "vboxtray.exe",
            winDir + "System32" + separator + "VBoxControl.exe",
            //VMWARE
            winDir + "System32" + separator + "Drivers" + separator + "Vmmouse.sys",
            winDir + "System32" + separator + "Drivers" + separator + "vm3dgl.dll",
            winDir + "System32" + separator + "Drivers" + separator + "vmdum.dll",
            winDir + "System32" + separator + "Drivers" + separator + "vm3dver.dll",
            winDir + "System32" + separator + "Drivers" + separator + "vmtray.dll",
            winDir + "System32" + separator + "Drivers" + separator + "VMToolsHook.dll",
            winDir + "System32" + separator + "Drivers" + separator + "vmmousever.dll",
            winDir + "System32" + separator + "Drivers" + separator + "vmhgfs.dll",
            winDir + "System32" + separator + "Drivers" + separator + "vmGuestLib.dll",
            winDir + "System32" + separator + "Drivers" + separator + "VmGuestLibJava.dll",
            winDir + "System32" + separator + "Driversvmhgfs.dll"
    };

    public FilesCheck() {
        super(CheckType.FLAG);
    }

    @Override
    public boolean check() {
        return checkVMFiles() || checkVirtualBoxFiles() || checkFiles();
    }

    private boolean checkFiles() {
        for(String file : files) {
            return Files.exists(Paths.get(file));
        }

        return false;
    }

    private boolean checkVMFiles() {
        final String osNameMatch = System.getProperty("os.name").toLowerCase();
        if(osNameMatch.contains("linux")) {
            return new File("/etc/vmware-tools").exists();
        } else if(osNameMatch.contains("windows")) {
            String path = !System.getProperty("os.arch").equalsIgnoreCase("x86") ?
                    System.getenv("ProgramFiles(X86)") : System.getenv("ProgramFiles");
            return new File(path + "\\VMware\\VMware Tools").exists();
        } else if(osNameMatch.contains("mac os") || osNameMatch.contains("macos") || osNameMatch.contains("darwin")) {
            return new File("/Library/Application Support/VMware Tools").exists();
        }

        return false;
    }

    private boolean checkVirtualBoxFiles() {
        final String osNameMatch = System.getProperty("os.name").toLowerCase();
        if(osNameMatch.contains("linux")) {
            return new File("/etc/init.d/vboxadd").exists();
        }else if(osNameMatch.contains("windows")) {
            final String path = !System.getProperty("os.arch").equalsIgnoreCase("x86") ?
                    System.getenv("ProgramFiles(X86)") : System.getenv("ProgramFiles");
            return new File(path + "\\Oracle\\VirtualBox Guest Additions").exists();
        }
        return false;
    }
}
