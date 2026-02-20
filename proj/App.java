package proj;

import proj.repository.ArticleRepository;
import proj.controller.*;
import java.util.Scanner;

public class App {
    public void run() {
        Scanner sc = new Scanner(System.in);
        ArticleRepository articleRepository = new ArticleRepository();

        System.out.println("== 텍스트 게시판 ==");

        while (true) {
            System.out.print("명령어: ");
            String cmd = sc.nextLine().trim();

            if (cmd.equals("exit")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }
            else if (cmd.equals("write")) {
                // new ArticleWriteController().execute(sc, articleRepository);
                System.out.println("write 컨트롤러 호출 예정");
            }
            else if (cmd.equals("list")) {
                // new ArticleListController().execute(sc, articleRepository);
                System.out.println("list 컨트롤러 호출 예정");
            }
            else if (cmd.equals("detail")) {
                // new ArticleDetailController().execute(sc, articleRepository);
                System.out.println("detail 컨트롤러 호출 예정");
            }
            else if (cmd.equals("update")) {
                // new ArticleUpdateController().execute(sc, articleRepository);
                System.out.println("update 컨트롤러 호출 예정");
            }
            else if (cmd.equals("delete")) {
                // new ArticleDeleteController().execute(sc, articleRepository);
                System.out.println("delete 컨트롤러 호출 예정");
            }
            else {
                System.out.println("존재하지 않는 명령어입니다.");
            }
        }
        sc.close();
    }
}