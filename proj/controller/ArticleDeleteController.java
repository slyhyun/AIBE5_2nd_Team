package proj.controller;

import proj.entity.Article;
import proj.repository.ArticleRepository;

import java.util.List;
import java.util.Scanner;

public class ArticleDeleteController {
    public void execute(Scanner sc, ArticleRepository articleRepository) {
        // 1. 목록 역순 출력 (최신글이 위로)
        List<Article> articles = articleRepository.findAll();

        if (articles.isEmpty()) {
            System.out.println("삭제할 게시글이 없습니다.");
            return;
        }

        System.out.println("== 게시글 목록 ==");
        for (int i = articles.size() - 1; i >= 0; i--) {
            Article a = articles.get(i);
            System.out.println("[" + a.getId() + "] " + a.getTitle() + " (" + a.getRegDate() + ")");
        }

        // 2. Scanner로 삭제할 ID 입력받기
        System.out.print("삭제할 게시글 번호 입력: ");
        int id;
        try {
            id = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("올바른 번호를 입력해주세요.");
            return;
        }

        // 3. ArrayList에서 해당 게시글 찾기
        Article target = null;
        for (Article a : articles) {
            if (a.getId() == id) {
                target = a;
                break;
            }
        }

        if (target == null) {
            System.out.println("해당 번호의 게시글이 없습니다.");
            return;
        }

        // 4. 삭제
        articleRepository.delete(id);
        System.out.println("[" + target.getTitle() + "] 게시글이 삭제되었습니다.");
    }
}