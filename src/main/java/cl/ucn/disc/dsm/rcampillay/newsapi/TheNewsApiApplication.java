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
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

/**
 * The News Api Application.
 *
 * @author Ronald Campillay-Pizarro.
 */

@SpringBootApplication
public class TheNewsApiApplication {


	/**
	 * 	The {@link NewsRepository} used to initialize the database.
	 */
	@Autowired
	private NewsRepository newsRepository;
	/**
	 * The Main starting point.
	 * @param args to use.
	 */

	public static void main(String[] args) {
		SpringApplication.run(TheNewsApiApplication.class, args);
	}


	/**
	 * Initialize the data inside the Database.
	 * @return the data to use.
	 */
	@Bean
	protected InitializingBean initializingDatabase(){
		return() -> {
			final News news = new News(
					"Acad??mico de Derecho en Jornadas Nacionales de Derecho penal y Cs. Penales",
					"El caballero de la esquina",
					"El Maldito Madafaca",
					"https://www.noticias.ucn.cl/destacado/academico-de-derecho-en-jornadas-nacionales-de-derecho-penal-y-cs-penales/",
					"https://www.noticias.ucn.cl/wp-content/uploads/2021/12/nt-Juan-Pablo-Jornadas-Penal.jpg",
					"Juan Pablo Castillo se refiri?? a un comentario del fallo de la Corte de Apelaciones de Antofagasta de 24 de julio de 2021.",
					"El acad??mico de la Escuela de Derecho de Antofagasta de la Universidad Cat??lica Norte (UCN), Juan Pablo Castillo, particip?? recientemente en las Jornadas Nacionales de Derecho Penal y Ciencias Penales, organizadas por la Universidad Arturo Prat y la Universidad de Chile.\n" +
							"\n" +
							"En la oportunidad, el docente se refiri?? a un comentario del fallo de la Corte de Apelaciones de Antofagasta del 24 de julio de 2021. Dicha mesa, donde adem??s expusieron los docentes, Alejandra Olave (UAI) y H??ctor Hern??ndez (UDP), corresponde a una instancia al interior de las jornadas a cargo del Instituto de Ciencias Penales de Chile, entidad cient??fica patrocinadora del encuentro y de la que el profesor Castillo es socio.\n" +
							"\n" +
							"El experto realiz?? una breve rese??a cr??tica del fallo de julio de 2021, que absolvi?? a una mujer del delito de homicidio frustrado en contra de un hombre con quien hasta hace poco manten??a una relaci??n sentimental. Seg??n indic??, el dictamen constituye un hito en la jurisprudencia nacional, pues arriba a este resultado tras sostener que se configuraba la leg??tima defensa (art. 10 n. 4 CP), a pesar de haber mediado un lapso de tiempo no menor entre el cese de las agresiones f??sicas del hombre y la acci??n defensiva de la imputada.\n" +
							"\n" +
							"En ese sentido, invocando consideraciones vinculadas a la fenomenolog??a propia de la violencia de g??nero, el fallo consider?? que el criterio de la actualidad o inminencia no deb??a leerse del modo irrestrictamente cronol??gico con que tradicionalmente la doctrina y jurisprudencia nacional lo ha concebido.",
					ZonedDateTime.now(ZoneId.of("-4"))
			);
			//Save the News
			this.newsRepository.save(news);

		};

	}
}
