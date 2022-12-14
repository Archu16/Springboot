package com.archana.social_bank.demo.services;

import com.archana.social_bank.demo.util.JpaConn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

@Component("serviceDao")

public class ServiceDao {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    JpaConn conn;


    public List<Services_Table> getServices() {

        try {
            List<Services_Table> services =  conn.getEntityManager()
                    .createQuery("from Services_Table").getResultList();
            return services;
        }catch (NoResultException noResultException){
            return null;
        }

    }

    public List<UserServices> getServices(int user_id) {

        try {
            List<UserServices> services =  conn.getEntityManager()
                    .createQuery("from UserServices").getResultList();

            List<UserServices> servicesTables = new ArrayList<>();

            for (int i =0; i<services.size(); i++){
                if (services.get(i).getUser_id() == user_id){
                    servicesTables.add(services.get(i));
                }
            }
            return servicesTables;
        }catch (NoResultException noResultException){
            return null;
        }

    }

    public String createUserService(UserServices e) {
        EntityTransaction txn = conn.getEntityManager().getTransaction();
        txn.begin();
        conn.getEntityManager().persist(e);
        txn.commit();
        return "done";
    }

    public void updateServiceStatus(int id, UserServices e) throws Exception {
        EntityTransaction txn = conn.getEntityManager().getTransaction();
        txn.begin();
        e.setUser_id(id);
        conn.getEntityManager().merge(e);
        conn.getEntityManager().flush();
        txn.commit();
    }

    public String createService(Services_Table userServices) {
        EntityTransaction txn = conn.getEntityManager().getTransaction();
        txn.begin();
        conn.getEntityManager().merge(userServices);
        txn.commit();
        return "done";
    }
}
