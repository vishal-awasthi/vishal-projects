package com.netpay.SaveFileSystem.FormValidation.backend.dao;

import com.netpay.SaveFileSystem.FormValidation.backend.data.FileLocationsRequest;
import org.apache.lucene.search.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;


public class PersistDataDAO {
    private Logger logger = LoggerFactory.getLogger(PersistDataDAO.class);


    public boolean SaveFilePaths(List<FileLocationsRequest> fileLocationsList)
    {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session session = null;
        try {
            session = sf.openSession();

            session.beginTransaction();
            for (FileLocationsRequest fileLocation : fileLocationsList) {
                session.saveOrUpdate(fileLocation);
            }
            session.getTransaction().commit();

        } catch (Exception hibernateException) {
            hibernateException.printStackTrace();
            logger.error("Exception occured while persisting data: ", hibernateException);
        } finally {
            if (session == null)
                logger.error("Session was not created.");
            else session.close();
        }
        return true;
    }

    public List<FileLocationsRequest> GetFilePaths(String keyword)  {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
        EntityManager em = emf.createEntityManager();
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
        try {
            fullTextEntityManager.createIndexer().startAndWait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(FileLocationsRequest.class)
                .get();

        Query query = queryBuilder
                .keyword()
                .fuzzy()
                .onField("path")
                .matching(keyword)
                .createQuery();

        FullTextQuery jpaQuery
                = fullTextEntityManager.createFullTextQuery(query);

        return jpaQuery.getResultList();
    }
}
