package ch.zuehlke.camp.batch;

import java.util.StringTokenizer;

import javax.batch.api.chunk.ItemProcessor;
import javax.inject.Named;

import ch.zuehlke.camp.jpa.Person;

@Named
public class CsvProcessor implements ItemProcessor {
    @Override
    public Person processItem(Object t) {
        System.out.println("processItem: " + t);
        
        StringTokenizer tokens = new StringTokenizer((String)t, ",");

        String firstName = tokens.nextToken();
        String lastName = tokens.nextToken();
        
        return new Person(firstName, lastName);
    }
}
