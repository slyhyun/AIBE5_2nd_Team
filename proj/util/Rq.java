package proj.util;

public class Rq {
    private String action;
    private int id;

    public Rq(String cmd) {
        String[] cmdBits = cmd.trim().split(" ");

        this.action = cmdBits[0];

        this.id = 0;
        if (cmdBits.length > 1) {
            try {
                this.id = Integer.parseInt(cmdBits[1]);
            } catch (NumberFormatException e) {
            }
        }
    }

    public String getAction() { return action; }
    public int getId() { return id; }
}