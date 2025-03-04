package tn.esprit.examen.SpeedyGo.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeEditor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.examen.SpeedyGo.Services.IAd;
import tn.esprit.examen.SpeedyGo.entities.Ad;
import tn.esprit.examen.SpeedyGo.entities.Product;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@RestController
@RequestMapping("/ad")
public class AdController {
    @Autowired
    IAd adImpl;


    @GetMapping(value = "/listAds")
    public List<Ad> getAllAds() {
        return adImpl.getAllAds();
    }


    @GetMapping(value = "getAd/{id}")
    public Ad getAdById(@PathVariable("id") String id) {
        return adImpl.getAdById(id);
    }

    @PostMapping(value="/createAd")
    public Ad createAd(@RequestBody Ad ad) throws IOException {
        return adImpl.createAd(ad);
    }
    @PutMapping("updateAd")
    public Ad updateAd(@RequestBody Ad ad) {
        return adImpl.updateAd(ad);
    }

    @DeleteMapping("deleteAd/{id}")
    public void deleteAd(@PathVariable("id") String id) {
        adImpl.deleteAd(id);
    }
}
