package com.hb.cda.projetorm.repository.interfaces;


import java.util.List;
import java.util.Optional;

/**
 * Interface repository générique qui regroupe toutes les méthodes partagées par tous
 * les repositories. On utilise les générique T (type) et K (key) pour typé les entrées
 * et sorties de chaque méthode, ce qui fait que lorsqu'on utilisera cette interface, il
 * faudra lui spécifier à la place de T l'entité ciblé et à la place de K le type
 * de la Primary Key de cette entité. (exemple: GenericRepository<Student, Integer>)
 */
public interface GenericRepository<T, K> {

    /**
     * Méthode renvoyant toutes les entrées pour une entité dans la base de données
     * @return Une liste d'instance de l'entité ciblée
     */
    List<T> findAll();
    /**
     * Méthode renvoyant une entité spécifique de la base de données en se basant sur son id
     * @param id L'id de l'entité recherché
     * @return Une instance de l'entité ou null si pas d'entité correspondant à l'id
     */
    Optional<T> findById(K id);
    /**
     * Méthode permettant de faire persister une entité dans la base de données
     * @param entity L'instance de l'entité qu'on souhaite faire persister.
     * @return true si la requête a fonctionné, sinon false
     */
    boolean persist(T entity);

    /**
     * Méthode permettant de faire mettre à jour une entité dans la base de données
     * @param entity L'instance de l'entité modifiée qu'on souhaite faire synchroniser avec la database. Il est impératif qu'elle ait un id
     * @return true si la requête a fonctionné, sinon false
     */
    boolean update(T entity);

    /**
     * Méthode permettant de faire supprimer une entité dans la base de données
     * @param entity L'entité à supprimer
     * @return true si la requête a fonctionné, sinon false
     */
    boolean delete(T entity);
}


