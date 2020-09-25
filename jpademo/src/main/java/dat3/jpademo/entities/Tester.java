package dat3.jpademo.entities;

import DTO.PersonStyleDTO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author claes
 */
public class Tester {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");

        EntityManager em = emf.createEntityManager();

        Person p1 = new Person("Claes", 1966);

        Person p2 = new Person("Hans", 1975);

        Address a1 = new Address("Kildeskovsvej 53", 2820, "Gentofte");
        Address a2 = new Address("Holmens Kanal 12", 1012, "København K");

        p1.setAddress(a1);
        p2.setAddress(a2);
        
        Fee f1 = new Fee(100);
        Fee f2 = new Fee(200);
        Fee f3 = new Fee(888);
        
        p1.addFee(f1);
        p2.addFee(f3);
        p2.addFee(f2);
        
        SwimStyle s1 = new SwimStyle("Crawl");
        SwimStyle s2 = new SwimStyle("Butterfly");
        SwimStyle s3 = new SwimStyle("Breast");
        
        p1.addSwimStyle(s1);
        p1.addSwimStyle(s3);
        p2.addSwimStyle(s2);
        
        
        

        em.getTransaction().begin();
        em.persist(p1);
        em.persist(p2);
        em.getTransaction().commit();
        
        em.getTransaction().begin();
        p1.removeStyle(s3);
        em.getTransaction().commit();
        

        //System.out.println("p1: " + p1.getP_id() + ", " + p1.getName());
        //System.out.println("p2: " + p2.getP_id() + ", " + p2.getName());

        //System.out.println("Claes gade er: " + p1.getAddress().getStreet());
        
        //System.out.println("Virker to-vejs? "+a1.getPerson().getName());
        
        //System.out.println("Hvem har betalt F2: "+f2.getPerson().getName());
        
        System.out.println("Hvem har betalt hvad:");
        
        TypedQuery<Fee> q1 = em.createQuery("SELECT f FROM Fee f", Fee.class);
        List<Fee> fees= q1.getResultList();
        
        fees.forEach(f -> {
            System.out.println(f.getPerson().getName()+". "+f.getAmount() +"kr, den: "+f.getPaid() + f.getPerson().getAddress().getCity());
        });
        
        Query q4 = em.createQuery("SELECT new dto.PersonStyleDTO(p.name, p.born, p.styleName) FROM Person p JOIN p.styles s ");
        List <PersonStyleDTO> personList = q4.getResultList();
        
        for (PersonStyleDTO p : personList) {
            System.out.println(p.getName() + ", " + p.getBorn() + ", " + p.getSwimStyle() );
            
        }
        
        

    }

}
