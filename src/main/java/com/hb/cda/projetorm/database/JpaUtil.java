package com.hb.cda.projetorm.database;


import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {
    private static final EntityManagerFactory EMF =
            Persistence.createEntityManagerFactory("projetorm");
    public static EntityManagerFactory getEntityManagerFactory() {
        return EMF;
    }
}
