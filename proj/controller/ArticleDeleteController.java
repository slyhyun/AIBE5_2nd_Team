package proj.controller;

import proj.entity.Article;
import proj.repository.ArticleRepository;
import proj.util.Rq;

import java.util.Scanner;

public class ArticleDeleteController {

    public void execute(Scanner sc, ArticleRepository articleRepository, Rq rq) {
        // Rq에서 ID 받아오기
        int id = rq.getId();

        if (id == 0) {
            System.out.println("삭제할 게시글 번호를 입력해주세요.");
            return;
        }

        Article target = articleRepository.findById(id);

        if (target == null) {
            System.out.println("해당 번호의 게시글이 없습니다.");
            return;
        }

        // 찾은 객체를 Repository에 넘겨서 삭제 요청
        articleRepository.delete(target);
        System.out.println("[" + target.getTitle() + "] 게시글이 삭제되었습니다.");
    }
}