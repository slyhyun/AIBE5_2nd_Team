package proj;

import proj.repository.ArticleRepository;
import proj.controller.*;
import proj.util.Rq;

import java.util.Scanner;

public class App {
    public void run() {
        Scanner sc = new Scanner(System.in);
        ArticleRepository articleRepository = new ArticleRepository();

        System.out.println("== 텍스트 게시판 ==");

        while (true) {
            System.out.print("명령어: ");
            String cmd = sc.nextLine().trim();

            Rq rq = new Rq(cmd);

            if (rq.getAction().equals("exit")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            } else if (rq.getAction().equals("write")) {
                new ArticleWriteController().execute(sc, articleRepository);
            } else if (rq.getAction().equals("list")) {
                new ArticleListController().execute(sc, articleRepository);
            } else if (rq.getAction().equals("detail")) {
                new ArticleDetailController().execute(sc, articleRepository, rq);
            } else if (rq.getAction().equals("update")) {
                new ArticleUpdateController().execute(sc, articleRepository, rq);
            } else if (rq.getAction().equals("delete")) {
                new ArticleDeleteController().execute(sc, articleRepository, rq);
            } else {
                System.out.println("존재하지 않는 명령어입니다.");
            }
        }
        sc.close();
    }
}