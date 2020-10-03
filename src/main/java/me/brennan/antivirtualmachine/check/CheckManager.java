package me.brennan.antivirtualmachine.check;

import me.brennan.antivirtualmachine.check.impl.*;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Brenann
 * @since 4/12/2020
 **/
public class CheckManager {
    private final List<ICheck> checks = new LinkedList<>();

    public CheckManager() {
        checks.add(new AnyRunCheck());
        checks.add(new CuckoomonCheck());
        checks.add(new FilesCheck());
        checks.add(new ProcessCheck());
        checks.add(new SandboxieCheck());
        checks.add(new MacAddressCheck());
        checks.add(new RegistryCheck());
    }

    public void runChecks() {
        checks.stream()
                .filter(check -> check instanceof AbstractCheck)
                .filter(ICheck::check).forEach(check -> {
            switch (((AbstractCheck) check).getType()) {
                case FLAG:
                    System.out.printf("[AntiVM] %s flagged%n", check.getClass().getSimpleName());
                    break;
                case SHUTDOWN:
                    System.out.printf("[AntiVM] %s shutdown flag%n", check.getClass().getSimpleName());
                    System.exit(-1);
                    break;
            }
        });
    }

}
