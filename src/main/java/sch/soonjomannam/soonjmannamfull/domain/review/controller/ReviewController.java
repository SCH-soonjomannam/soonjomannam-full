package sch.soonjomannam.soonjmannamfull.domain.review.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ReviewController {
    @GetMapping("/")
    public String index() {
        return "home/index";
    }
}
