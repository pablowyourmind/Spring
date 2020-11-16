package tacos.data;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import tacos.entities.Order;
import tacos.entities.security.User;

import java.util.List;

/**
 * JPA interface for Order. Extends CrudRepository
 * ID in Order class has a type of Long.
 * @author pawel
 *
 */
public interface OrderRepository extends CrudRepository<Order, Long>{
	
	/**
	 * Custom method created using Spring Data parsing strategy.
	 * IT DOESN'T NEED ANY IMPLEMENTATION, because Spring Data parses the name of method
	 * and tries to assume which kind of operation should be done.
	 * Amazing!
	 */
	List<Order> getOrdersByStreetAndCityAllIgnoringCase(String street, String city);

	List<Order> findOrdersByModifiedByOrderByPlacedAtDesc(User user, Pageable pageable);
}
