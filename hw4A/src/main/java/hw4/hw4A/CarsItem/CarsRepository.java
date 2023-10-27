
package hw4.hw4A.CarsItem;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author kenne
 */
public interface CarsRepository  extends JpaRepository<Cars, Long>{
    
    public List<Cars> findByName(String name);

    @Query("SELECT c FROM Car c WHERE CONCAT(c.name, c.type) LIKE %?1%")
    public List<Cars> search(String keyword);
}
