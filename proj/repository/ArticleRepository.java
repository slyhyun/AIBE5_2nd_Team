package proj.repository;

import proj.entity.Article;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ArticleRepository {
    private List<Article> articles;
    private int lastId;
    private final String FILE_PATH = "data/article.json";
    private Gson gson;

    public ArticleRepository() {
        this.articles = new ArrayList<>();
        this.lastId = 0;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
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

    // ID로 게시글 찾기
    public Article findById(int id) {
        for (Article article : articles) {
            if (article.getId() == id) {
                return article;
            }
        }
        return null;
    }

    // 수정 후 저장
    public void update() {
        saveToFile();
    }

    // 게시글 삭제 후 파일 덮어쓰기
    public void delete(Article article) {
        articles.remove(article);
        update();
    }

    // 파일에 저장하는 내부 로직
    private void saveToFile() {
        try{
            File file =new File(FILE_PATH.substring(0,FILE_PATH.lastIndexOf("/")));
            if(!file.isDirectory())file.mkdirs();
        }catch (Exception e){
            System.out.println("폴더 만들기 실패");
        }
        try (FileWriter fw = new FileWriter(FILE_PATH, StandardCharsets.UTF_8)) {
            gson.toJson(articles, fw);
        } catch (IOException e) {
            System.out.println("파일 저장 중 오류가 발생했습니다.");
        }
    }

    // 파일에서 불러오는 내부 로직
    private void loadFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return;

        try (FileReader fr = new FileReader(file, StandardCharsets.UTF_8)) {
            Type listType = new TypeToken<ArrayList<Article>>(){}.getType();
            articles = gson.fromJson(fr, listType);

            if (articles == null) {
                articles = new ArrayList<>();
            }

            for (Article a : articles) {
                lastId = Math.max(lastId, a.getId());
            }
        } catch (IOException e) {
            System.out.println("파일 읽기 중 오류가 발생했습니다.");
        }
    }
}