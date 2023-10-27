
package hw4.hw4A.CarsItem;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author Kenneth
 */
@Service
public class CarsService {
    @Autowired
    private CarsRepository repo;

    /**
     * Get all cars.
     *
     * @return the list of cars.
     */
    public List<Cars> getAllCars() {
        return repo.findAll();
    }

    /**
     * Get all cars that match the keyword.
     *
     * @param keyword the search term.
     * @return the list of cars.
     */
    public List<Cars> getAllCars(String keyword) {
        if (keyword != null) {
            return repo.search(keyword);
        }
        return repo.findAll();
    }

    /**
     * Get a single car by ID
     *
     * @param carId
     * @return the car
     */
    public Cars getCars(long carId) {
        return repo.getReferenceById(carId);
    }

    /**
     * Delete a single car by ID
     *
     * @param carId
     */
    public void deleteCar(long carId) {
        repo.deleteById(carId);
    }

    /**
     * Save a car entry.
     *
     * @param car
     */
    void saveCar(Cars car) {
        repo.save(car);
    }

}
