package proj.controller;

import proj.entity.Article;
import proj.repository.ArticleRepository;

import java.time.LocalDateTime;
import java.util.Scanner;

public class ArticleWriteController {
    public void execute(Scanner sc, ArticleRepository repository){
        System.out.print("제목 : ");
        String title = sc.nextLine();


        System.out.println("내용을입력하세요 작성이 완료후 ::finish를 입력하면 입력이 완료됨 :");
        StringBuilder sb =new StringBuilder();
        while(true){
            String content = sc.nextLine();
            if(content.equals("::finish"))break;
            sb.append(content+"\n");
        }

        String contents = sb.toString();
        System.out.println("제목 : "+title);
        System.out.println("내용 : \n"+contents);
        while(true){
            System.out.println("해당 게시글을 게시하시겠습니까? yes or no");
            String save = sc.nextLine().trim();
            if(save.toLowerCase().equals("yes")){
                break;
            }
            else if(save.toLowerCase().equals("no")){
                return;
            }
        }
        LocalDateTime current = LocalDateTime.now();
        Article article =new Article(0,title,contents,current.toString());
        repository.save(article);

    }

}
