package koh.core.config;

public enum SystemVariable {
    SYSTEM_VARIABLE;
    getVar(String name) {
        return System.getenv(name);
    }
    public static getSystemVariable(String name) {

    }
}
