package main.EasyProject.controller;

import com.example.EasyProject.model.Boat;
import com.example.EasyProject.repository.BoatRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BoatController {
    BoatRepository boatRepository = new BoatRepository();

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model){
        Boat boat = boatRepository.findById(id);
        model.addAttribute("boat", boat);
        return "details";
    }

    @GetMapping("/catalog")
    public String boatList(Model model){
        List<Boat> boats = boatRepository.findAll();
        model.addAttribute("boats",boats);
        return "catalog";
    }
}