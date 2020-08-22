package com.jeffrey;

import com.jeffrey.bean.Article;
import com.jeffrey.bean.Book;
import com.jeffrey.repository.BookRepository;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootElasticsearch03ApplicationTests {

    @Autowired
    JestClient jestClient;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void testBookRepository(){
        /*Book book = new Book();
        book.setId(2);
        book.setAuthor("洪泽飞");
        book.setBookName("Java设计思想");
        bookRepository.index(book);*/

        List<Book> nameLike = bookRepository.findByBookNameLike("思想");
        for (Book book : nameLike) {
            System.out.println(book);
        }
    }

    @Test
    public void contextLoads() {
        //1、给Es中索引（保存）一个文档；
        Article article = new Article();
        article.setId(1);
        article.setAuthor("洪泽飞");
        article.setTitle("Java设计思想");
        article.setContent("略略略");


        //构建一个索引功能
        Index index = new Index.Builder(article).index("jeffrey").type("news").build();

        try {
            //执行
            jestClient.execute(index);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 搜索
     */
    @Test
    public void search(){

        // 搜索表达式
        String json = "{\n" +
                "    \"query\" : {\n" +
                "        \"match\" : {\n" +
                "            \"content\" : \"hello\"\n" +
                "        }\n" +
                "    }\n" +
                "}";

        // 构建搜索功能
        Search search = new Search.Builder(json).addIndex("jeffrey").addType("news").build();

        // 执行
        try {
            SearchResult searchResult = jestClient.execute(search);
            System.out.println(searchResult.getJsonString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
