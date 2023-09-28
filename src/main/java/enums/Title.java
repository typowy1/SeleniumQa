package enums;


public enum Title {
    MR("Mr"),
    MRS("Mrs");

    Title(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }
}
