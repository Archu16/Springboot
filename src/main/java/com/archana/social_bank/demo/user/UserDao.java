package com.archana.social_bank.demo.user;

import com.archana.social_bank.demo.util.JpaConn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import java.util.List;

@Component("userDao")

public class UserDao {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    JpaConn conn;

    @Autowired
    @Qualifier("bCryptEncoder")
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public String createAdmin(Users e) {
        e.setPassword(bCryptPasswordEncoder.encode(e.getPassword()).toString());
        EntityTransaction txn = conn.getEntityManager().getTransaction();
        try {
            txn.begin();
            conn.getEntityManager().persist(e);
            txn.commit();
        }catch (Throwable t){
            return t.getLocalizedMessage();
        }
        return "done";
    }

    public Users login(String email, String password) {
        try {
            logger.info(email);
            Users userMatch = (Users) conn.getEntityManager()
                    .createQuery("from Users where email=:email AND password IS NOT NULL")
                    .setParameter("email", email).getSingleResult();
            if (bCryptPasswordEncoder.matches(password, userMatch.getPassword())) {
                return userMatch;
            } else {
                logger.info("wrong password");
                return null;
            }
        } catch (NoResultException e) {
            logger.info("email not found");
            return null;
        }
    }

    public Users getUser(int user_id) {

        try {
            List<Users> users = (List<Users>) conn.getEntityManager()
                    .createQuery("from Users").getResultList();

            Users users1 = null;
            for (int i =0;i<users.size();i++){
                if (users.get(i).getId() == user_id){
                    users1 = users.get(i);
                    break;
                }
            }
            return users1;
        }catch (NoResultException noResultException){
            return null;
        }

    }


    public Users getUser(String email) {
        try {
            logger.info(email);
            Users userMatch = (Users) conn.getEntityManager()
                    .createQuery("from Users where email=:email AND password IS NOT NULL")
                    .setParameter("email", email).getSingleResult();
            return userMatch;
        } catch (NoResultException e) {
            logger.info("email not found");
            return null;
        }
    }
}
