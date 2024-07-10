package challlenge.forohub.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hola")
public class pruebaController {

    @GetMapping
    public String hola(){
        return "hola que tal!";
    }
}
