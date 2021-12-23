/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 *
 * Uso netamente estudiantil
 * Prohibida su Distribución
 */

package cl.ucn.disc.dsm.rcampillay.newsapi.services;


import cl.ucn.disc.dsm.rcampillay.newsapi.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The Repository of News
 *
 * @author Ronald Campillay-Pizarro
 */

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {



}
