package mybean;

public class MyBean {
    private String field = "Welcome to TIMETRACKER, ";
    private String who = "dear client ";

    public MyBean() {
    }

    public MyBean(String field, String who) {
        this.field = field;
        this.who = who;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }
}
