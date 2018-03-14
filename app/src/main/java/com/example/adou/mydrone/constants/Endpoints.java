package com.example.adou.mydrone.enums;

/**
 * Created by hakima on 3/12/18.
 */

public enum Endpoints {
    REGISTER_ACCOUNT("/api/reigster");

    private final String name;

    private Endpoints(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}
