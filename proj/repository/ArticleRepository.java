package proj.repository;

import proj.entity.Article;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleRepository {
    private List<Article> articles;
    private int lastId;
    private final String FILE_PATH = "data/article.txt";

    public ArticleRepository() {
        this.articles = new ArrayList<>();
        this.lastId = 0;
        loadFromFile();
    }

    // 게시글 저장
    public void save(Article article) {
        lastId++;
        article.setId(lastId);
        articles.add(article);

        saveToFile();
    }

    // 전체 게시글 조회
    public List<Article> findAll() {
        return articles;
    }

    // 파일에 저장하는 내부 로직
    private void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            // 예: "1||자바공부||재밌다||2026-02-06" 형태로 저장
            for (Article a : articles) {
                String line = a.getId() + "||" + a.getTitle() + "||" + a.getContent() + "||" + a.getRegDate();
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("파일 저장 중 오류가 발생했습니다.");
        }
    }

    // 파일에서 불러오는 내부 로직
    private void loadFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|\\|");
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0]);
                    Article article = new Article(id, parts[1], parts[2], parts[3]);
                    articles.add(article);
                    lastId = Math.max(lastId, id);
                }
            }
        } catch (IOException e) {
            System.out.println("파일 읽기 중 오류가 발생했습니다.");
        }
    }
}