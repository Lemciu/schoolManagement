package pl.ml.student;

public enum FieldOfStudy {
    PSYCHOLOGY("Psychology"),
    LOGISTICS("Logistics"),
    LAW("Law"),
    MEDICINE("Medicine"),
    SOCIOLOGY("Sociology");

    private String name;

    FieldOfStudy(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
