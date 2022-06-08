package tokyo.ramune.util;

public enum ConsoleColor {
    RED,
    GREEN,
    YELLOW,
    PURPLE,
    PINK,
    CYAN,
    END;

    public String toString() {
        switch (super.name()) {
            case "RED": return "\u001b[00;31m";
            case "GREEN": return "\u001b[00;32m";
            case "YELLOW": return "\u001b[00;33m";
            case "PURPLE": return "\u001b[00;34m";
            case "PINK": return "\u001b[00;35m";
            case "CYAN": return "\u001b[00;36m";
            case "END": return "\u001b[00m";
            default: return "\u001b[00;30m";
        }
    }
}