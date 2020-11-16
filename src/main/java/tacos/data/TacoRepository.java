package tacos.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import tacos.entities.Taco;

import java.util.List;

/**
 * JPA interface for Taco. Extends CrudRepository
 * ID in Taco class has a type of Long.
 * @author pawel
 *
 */
public interface TacoRepository extends CrudRepository<Taco, Long> {
	
	/**
	 * Custom method created using Spring Data and
	 * Query annotation to specify the HQL query
	 */
	@Query("FROM Taco t where t.tacoName = 'Test'")
	public List<Taco> readTacosWithnameTest();
}
