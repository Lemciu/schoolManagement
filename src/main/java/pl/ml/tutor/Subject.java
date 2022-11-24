package pl.ml.tutor;

public enum Subject {
    HISTORY("History"),
    ENGLISH("English"),
    FRENCH("French"),
    POLISH("Polish"),
    MATHEMATICS("Mathematics"),
    CHEMISTRY("Chemistry"),
    PHYSICS("Physics");

    private String name;

    Subject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
