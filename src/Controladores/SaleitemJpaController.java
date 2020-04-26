/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.exceptions.NonexistentEntityException;
import Entidades.Saleitem;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author George
 */
public class SaleitemJpaController implements Serializable {

    public SaleitemJpaController() {
        this.emf = Persistence.createEntityManagerFactory("Proyecto_2.0PU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Saleitem saleitem) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(saleitem);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Saleitem saleitem) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            saleitem = em.merge(saleitem);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = saleitem.getIdSaleItem();
                if (findSaleitem(id) == null) {
                    throw new NonexistentEntityException("The saleitem with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Saleitem saleitem;
            try {
                saleitem = em.getReference(Saleitem.class, id);
                saleitem.getIdSaleItem();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The saleitem with id " + id + " no longer exists.", enfe);
            }
            em.remove(saleitem);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Saleitem> findSaleitemEntities() {
        return findSaleitemEntities(true, -1, -1);
    }

    public List<Saleitem> findSaleitemEntities(int maxResults, int firstResult) {
        return findSaleitemEntities(false, maxResults, firstResult);
    }

    private List<Saleitem> findSaleitemEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Saleitem.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Saleitem findSaleitem(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Saleitem.class, id);
        } finally {
            em.close();
        }
    }

    public int getSaleitemCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Saleitem> rt = cq.from(Saleitem.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
