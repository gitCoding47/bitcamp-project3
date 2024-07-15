package bitcamp.project3.vo;

public enum AuthGrade {
    GUEST(1,"게스트"),
    ADMIN(102030,"관리자");

    private final int pwd;

    public int getPwd() {
        return pwd;
    }

    public int pwd() {
        return pwd;
    }
    public String getDescription() {
        return description;
    }
    private final String description;

    AuthGrade(int pwd, String description) {
        this.pwd = pwd;
        this.description = description;
    }
}
