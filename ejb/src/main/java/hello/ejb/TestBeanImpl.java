package hello.ejb;

import lombok.extern.slf4j.Slf4j;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Slf4j
@Stateless
public class TestBeanImpl implements TestBean {
    @PersistenceContext
    private EntityManager em;


    @Override
    public Motorcycle getMotorcycle(int id) {
        log.info("ejb - get");
        return em.find(Motorcycle.class, id);
    }

    @Override
    @Transactional
    public Motorcycle saveMotorcycle(Motorcycle motorcycle) {
        log.info("ejb - save moto");
        Motorcycle merge = em.merge(motorcycle);
        if (merge.getId() > 2) {
            throw new RuntimeException("no to dupa");
        }
        return merge;
    }
}
