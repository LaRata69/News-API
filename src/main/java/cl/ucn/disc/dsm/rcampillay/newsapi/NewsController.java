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
     * @return all the news in the backend.
     */

    @GetMapping("/v1/news")
    public List<News> all() {
        return new ArrayList<>();



    }

    /**
     *
     * @param id of News to retrieve.
     * @return the news.
     */
    @GetMapping("/v1/news/{id}")
    public News one (@PathVariable final Long id){
        //FIXME: Only for test.
        News news = new News(
                "Académico de Derecho en Jornadas Nacionales de Derecho penal y Cs. Penales",
                "El caballero de la esquina",
                "El Maldito Madafaca",
                "https://www.noticias.ucn.cl/destacado/academico-de-derecho-en-jornadas-nacionales-de-derecho-penal-y-cs-penales/",
                "https://www.noticias.ucn.cl/wp-content/uploads/2021/12/nt-Juan-Pablo-Jornadas-Penal.jpg",
                "Juan Pablo Castillo se refirió a un comentario del fallo de la Corte de Apelaciones de Antofagasta de 24 de julio de 2021.",
                "El académico de la Escuela de Derecho de Antofagasta de la Universidad Católica Norte (UCN), Juan Pablo Castillo, participó recientemente en las Jornadas Nacionales de Derecho Penal y Ciencias Penales, organizadas por la Universidad Arturo Prat y la Universidad de Chile.\n" +
                        "\n" +
                        "En la oportunidad, el docente se refirió a un comentario del fallo de la Corte de Apelaciones de Antofagasta del 24 de julio de 2021. Dicha mesa, donde además expusieron los docentes, Alejandra Olave (UAI) y Héctor Hernández (UDP), corresponde a una instancia al interior de las jornadas a cargo del Instituto de Ciencias Penales de Chile, entidad científica patrocinadora del encuentro y de la que el profesor Castillo es socio.\n" +
                        "\n" +
                        "El experto realizó una breve reseña crítica del fallo de julio de 2021, que absolvió a una mujer del delito de homicidio frustrado en contra de un hombre con quien hasta hace poco mantenía una relación sentimental. Según indicó, el dictamen constituye un hito en la jurisprudencia nacional, pues arriba a este resultado tras sostener que se configuraba la legítima defensa (art. 10 n. 4 CP), a pesar de haber mediado un lapso de tiempo no menor entre el cese de las agresiones físicas del hombre y la acción defensiva de la imputada.\n" +
                        "\n" +
                        "En ese sentido, invocando consideraciones vinculadas a la fenomenología propia de la violencia de género, el fallo consideró que el criterio de la actualidad o inminencia no debía leerse del modo irrestrictamente cronológico con que tradicionalmente la doctrina y jurisprudencia nacional lo ha concebido.",
                ZonedDateTime.now(ZoneId.of("-4"))
        );

        return news;



    }



}
