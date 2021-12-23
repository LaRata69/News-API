/*
 *   Copyright (c) 2021 Ronald Campillay Pizarro
 *
 *   Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 *   associated documentation files (the "Software"), to deal in the Software without restriction, including
 *   without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the
 *   following conditions: The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software. THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,   EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF ERCHANTABILITY, FITNESS FOR A  PARTICULAR PURPOSE AND NON INFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,  ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALING IN THE SOFTWARE.
 *
 */

package cl.ucn.disc.dsm.rcampillay.newsapi;

import cl.ucn.disc.dsm.rcampillay.newsapi.model.News;
import com.kwabenaberko.newsapilib.models.Article;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;
import com.kwabenaberko.newsapilib.network.APIClient;
import com.kwabenaberko.newsapilib.network.APIService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Controller of News
 * @author Ronald Campillay-Pizarro
 */
@Slf4j
@RestController
public class NewsController {

    /**
     * The repository of News.
     */

    private final NewsRepository newsRepository;

    /**
     * The constructor of NewsController.
     * @param newsRepository to use.
     */

    public NewsController(NewsRepository newsRepository){

        this.newsRepository = newsRepository;
    }



    /**
     * @return all the news in the backend.
     */

    @GetMapping("/v1/news")
    public List<News> all(@RequestParam(required = false, defaultValue = "false") Boolean reload) {

        //http://servidor.dominio:8080/v1/news?reload=true
        // If reload -> get news from NewsAPI.org
        if (reload){
            this.reloadNewsFromNewsApi();
        }

        log.info("Request all with reload: {}.", reload);

        // Equals to Select *from News
        final List<News> theNews = this.newsRepository.findAll();
        //TODO: Show the news
        return theNews;




    }

    /**
     * Get the news from NewsAPI and save into the database.
     */
    private void reloadNewsFromNewsApi() {

        //WARNING: Just for test.
        final String API_KEY ="85c8754b88ce47e482c29147b9742172";
        final int pageSize = 100;

        // 1. Create the APIClient.
        final APIService apiService = APIClient.getAPIService();

        // 2. Build the request params
        final Map<String, String> reqParams = new HashMap<>();
        reqParams.put("apiKey", API_KEY);
        // Filter by category
        reqParams.put("category", "technology");
        // The number of results to return perp age (request). 20 min - 100 max.
        reqParams.put("pageSize", String.valueOf(pageSize));

        // 3. Call the request
        try {
            Response<ArticleResponse> articleResponse = apiService.getTopHeadlines(reqParams).execute();

            // Sucesss!

            if (articleResponse.isSuccessful()){
                //TODO: Check for getArticles -/> Null
                List<Article> articles = articleResponse.body().getArticles();

                // List <Articles> to List <News>
                List <News> news = new ArrayList<>();
                for (Article article : articles) {
                    news.add(toNews(article));

                }

                // 4. Save into the local database.
                this.newsRepository.saveAll(news);


        }


        } catch (IOException e) {
            log.error("Getting the Articles from News API", e);
        }

    }

    /**
     * Convert a Article to news.
     * @param article to convert.
     * @return the News.
     */
    private static News toNews(final Article article){

        // Protection: author
        if(article.getAuthor() == null || article.getAuthor().length() < 3){
            article.setAuthor("NO AUTOR");
        }

        // Protection: title
        if (article.getTitle()==null || article.getTitle().length()<3) {
            article.setTitle("NO TITLE");
        }

        // Protection: description
        if (article.getDescription() == null || article.getDescription().length() < 3) {
            article.setDescription("No Description*");
        }

        // Correct from UTC to localTime (Chile)
        ZonedDateTime publishedAt = ZonedDateTime.parse(article.getPublishedAt()).withZoneSameInstant(ZoneId.of("-3"));

        return new News(
                article.getTitle(), article.getSource().getName(), article.getAuthor(), article.getUrl(), article.getUrlToImage(), article.getDescription(), article.getDescription(), publishedAt

        );





    }

    /**
     *
     * @param id of News to retrieve.
     * @return the news.
     */
    @GetMapping("/v1/news/{id}")
    public News one (@PathVariable final Long id){
        //FIXME: Change RUntimne to 404.
       return this.newsRepository.findById(id).orElseThrow(()-> new RuntimeException("News not found!!! ") );





    }



}
