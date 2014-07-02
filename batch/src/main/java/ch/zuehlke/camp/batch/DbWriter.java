package ch.zuehlke.camp.batch;

import java.util.List;

import javax.batch.api.chunk.AbstractItemWriter;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named
public class DbWriter extends AbstractItemWriter {
    
    @PersistenceContext
    EntityManager em;

    @Override
    public void writeItems(List list) {
        //System.out.println("writeItems: " + list.size());
        for (Object object : list) {
            em.persist(object);
            System.out.println("written: " + object);
        }
    }
}
