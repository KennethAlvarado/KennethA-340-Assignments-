
package hw4.hw4A.CarsItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Kenneth
 */
@Controller
@RequestMapping("/cars")
public class CarsController {
     @Autowired
    CarsService carsService;

    @GetMapping("/all")
    public String getAllCars(Model model) {
        model.addAttribute("carList",
                carsService.getAllCars());
        return "cars/car-page";
    }
    
    @GetMapping("/search")
    public String getProducts(Model model, @Param("keyword") String keyword) {
        model.addAttribute("carList",
                carsService.getAllCars(keyword));
        model.addAttribute("keyword", keyword);
        return "cars/car-page";
    }

    @GetMapping("/id={carId}")
    public String getCar(@PathVariable int carId, Model model) {
        model.addAttribute("car",
                carsService.getCars(carId));
        return "cars/car-detail";
    }

    @GetMapping("/delete/id={carId}")
    public String deleteCar(@PathVariable long carId, Model model) {
        carsService.deleteCar(carId);
        return "redirect:/cars/all";
    }

    @PostMapping("/create")
    public String createCar(Cars car) {

        carsService.saveCar(car);
        return "redirect:/cars/all";
    }

    @PostMapping("/update")
    public String upateCar(Cars car) {
        carsService.saveCar(car);
        return "redirect:/cars/all";
    }

    @GetMapping("/new-car")
    public String newCarForm(Model model) {
        return "cars/new-car";
    }

    @GetMapping("/update/id={carId}")
    public String updateCarForm(@PathVariable long carId, Model model) {
        model.addAttribute("cars",
                carsService.getCars(carId));
        return "cars/update-car";
    }
}
