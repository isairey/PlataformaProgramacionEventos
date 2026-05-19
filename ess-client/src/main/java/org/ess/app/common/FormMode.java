package org.ess.app.common;

public enum FormMode {
    CREATE("CREATE"), EDIT("EDIT");

    final String name;

    FormMode(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
