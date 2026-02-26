package proj.controller;

import proj.entity.Article;
import proj.repository.ArticleRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ArticleListController {

    public void execute(Scanner sc, ArticleRepository articleRepository) {
        // 전체 게시글 목록 가져오기
        List<Article> articles = articleRepository.findAll();

        // 최신순으로 뒤집기 (나중에 추가된 글이 위에 오도록)
        Collections.reverse(articles);

        System.out.println("== 게시글 목록 ==");

        // 게시글이 하나도 없을 때
        if (articles.isEmpty()) {
            System.out.println("게시글이 없습니다.");
            return;
        }

        // 검색어 입력받기 (그냥 엔터 치면 전체 보기)
        System.out.print("검색어 입력 (전체 보기: 엔터): ");
        String keyword = sc.nextLine().trim();

        // 검색 결과 필터링
        List<Article> result = new ArrayList<>();
        for (Article article : articles) {
            // 검색어가 없으면 전체, 있으면 제목에 포함된 것만
            if (keyword.isEmpty() || article.getTitle().contains(keyword)) {
                result.add(article);
            }
        }

        // 결과 출력
        if (result.isEmpty()) {
            System.out.println("'" + keyword + "' 검색 결과가 없습니다.");
        } else {
            if (!keyword.isEmpty()) {
                System.out.println("'" + keyword + "' 검색 결과: " + result.size() + "개");
            }
            System.out.println("번호 | 제목 | 등록일");
            System.out.println("-------------------");
            for (Article article : result) {
                System.out.println(article.getId() + " | " + article.getTitle() + " | " + article.getRegDate());
            }
        }
    }
}