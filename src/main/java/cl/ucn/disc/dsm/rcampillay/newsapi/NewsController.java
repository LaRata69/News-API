package cl.ucn.disc.dsm.rcampillay.newsapi;

import cl.ucn.disc.dsm.rcampillay.newsapi.model.News;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

import java.util.ArrayList;
import java.util.List;

/**
 * The Controller of News
 * @author Ronald Campillay-Pizarro
 */
@RestController
public class NewsController {

    /**
     * The repository of News
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
    public List<News> all() {
        final List<News> theNews = this.newsRepository.findAll();
        //TODO: Show the news
        return theNews;




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
