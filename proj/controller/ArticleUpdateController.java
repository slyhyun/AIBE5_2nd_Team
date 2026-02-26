package proj.controller;

import proj.entity.Article;
import proj.repository.ArticleRepository;
import proj.util.Rq;

import java.util.Scanner;

public class ArticleUpdateController {

    public void execute(Scanner sc, ArticleRepository articleRepository, Rq rq) {
        // 게시글 번호 꺼내기
        int id = rq.getId();

        // 번호를 입력하지 않은 경우
        if (id == 0) {
            System.out.println("수정할 게시글 번호를 입력해주세요.");
            return;
        }

        // Repository를 통해 ID로 게시글 찾기
        Article article = articleRepository.findById(id);

        // 해당 번호의 게시글이 없는 경우
        if (article == null) {
            System.out.println(id + "번 게시글은 존재하지 않습니다.");
            return;
        }

        // 게시글이 존재하면 제목과 내용 수정 진행
        System.out.println("== " + id + "번 게시글 수정 ==");

        System.out.printf("기존 제목: %s\n", article.getTitle());
        System.out.print("새 제목: ");
        String newTitle = sc.nextLine().trim();

        System.out.printf("기존 내용: %s\n", article.getContent());
        System.out.print("새 내용: ");
        String newContent = sc.nextLine().trim();

        // 객체 값 변경하기
        article.setTitle(newTitle);
        article.setContent(newContent);

        // 변경된 내용을 JSON 파일에 덮어쓰기
        articleRepository.update();

        System.out.println(id + "번 게시글이 성공적으로 수정되었습니다.");
    }
}