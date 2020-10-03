package me.brennan.antivirtualmachine.check;

/**
 * @author Brenann
 * @since 4/12/2020
 **/
public abstract class AbstractCheck implements ICheck {
    private final CheckType type;

    public AbstractCheck(CheckType type) {
        this.type = type;
    }

    public CheckType getType() {
        return type;
    }

    public enum CheckType {
        FLAG, SHUTDOWN
    }

}
