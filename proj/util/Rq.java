package proj.util;

public class Rq {
    private String action;
    private int id;

    public Rq(String cmd) {
        // 띄어쓰기 기준으로 구분
        String[] cmdBits = cmd.trim().split(" ");

        // 첫 번째 부분은 명령어
        this.action = cmdBits[0];

        // 두 번째 부분이 있다면 id
        this.id = 0;
        if (cmdBits.length > 1) {
            try {
                this.id = Integer.parseInt(cmdBits[1]);
            } catch (NumberFormatException e) {
            }
        }
    }

    // Getter
    public String getAction() { return action; }
    public int getId() { return id; }
}