package tn.esprit.examen.SpeedyGo.Controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.examen.SpeedyGo.Services.IFastPostService;
import tn.esprit.examen.SpeedyGo.Services.IPromotionService;
import tn.esprit.examen.SpeedyGo.entities.FastPost;
import tn.esprit.examen.SpeedyGo.entities.Status;
import tn.esprit.examen.SpeedyGo.entities.Vehicle;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/fastPost")
@CrossOrigin(origins = "http://localhost:4200")

public class FastPostController {
    IFastPostService fastPostService;
    @Autowired
    public FastPostController( IFastPostService fastPostService) {
        this.fastPostService = fastPostService;
    }
    @GetMapping("/retrieve-all-fastPosts")
    public List<FastPost> getFastPosts() {
        List<FastPost> listfastposts = fastPostService.getAllFastPosts();
        return listfastposts;
    }
    @GetMapping("/retrieve-fastPost/{fastPost-id}")
    public FastPost getFastPost(@PathVariable("fastPost-id") String FPId) {
        FastPost fastPost = fastPostService.getFastPost(FPId);
        return fastPost;
    }
    @PostMapping("/add-fastPost")
    public FastPost addFastPost(@RequestBody FastPost fp) {
        FastPost fastPost = fastPostService.addFastPost(fp);
        return fastPost;
    }
    @DeleteMapping("/remove-fastPost/{fastPost-id}")
    public void removeFastPost(@PathVariable("fastPost-id") String fpId) {
        fastPostService.deleteFastPost(fpId);
    }

    @PutMapping("/modify-fastPost/{fastPost-id}")
    public FastPost modifyFastPost(@PathVariable("fastPost-id") String fpId, @RequestBody FastPost fp) {
        return fastPostService.modifyFastPost(fpId, fp);
    }
    @PutMapping("/approve/{id}")
    public void approveFastPost(@PathVariable String id) {
        fastPostService.approveFastPost(id);
    }

    @PutMapping("/reject/{id}")
    public void rejectFastPost(@PathVariable String id) {
        fastPostService.rejectFastPost(id);
    }



}
