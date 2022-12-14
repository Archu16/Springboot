package com.archana.social_bank.demo.comments;

import com.archana.social_bank.demo.util.JpaConn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import java.util.List;
@Component("commentDao")

public class CommentsDao {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    JpaConn conn;

    public String createComment(Comments e) {
        EntityTransaction txn = conn.getEntityManager().getTransaction();
        txn.begin();
        conn.getEntityManager().persist(e);
        txn.commit();
        return "done";
    }

    public List<Comments> getComments(int user_id) {

        try {
            List<Comments> comments = (List<Comments>) conn.getEntityManager()
                    .createQuery("from Comments where user_id=:user_id")
                    .setParameter("user_id", user_id).getSingleResult();
            return comments;
        }catch (NoResultException noResultException){
            return null;
        }

    }
    public List<Comments> getAllComments() {

        try {
            List<Comments> comments =  conn.getEntityManager()
                    .createQuery("from Comments").getResultList();
            return comments;
        }catch (NoResultException noResultException){
            return null;
        }

    }



}
