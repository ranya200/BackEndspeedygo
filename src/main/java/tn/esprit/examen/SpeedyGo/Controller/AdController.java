package tn.esprit.examen.SpeedyGo.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.examen.SpeedyGo.Services.IAd;
import tn.esprit.examen.SpeedyGo.entities.Ad;
import tn.esprit.examen.SpeedyGo.entities.Product;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
//@RequiredArgsConstructor
@RestController
@RequestMapping("/ad")
public class AdController {
    @Autowired
    IAd adImpl;
    private final ObjectMapper objectMapper; // Add an ObjectMapper to handle JSON deserialization

    public AdController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    @PostMapping(value="/createAd", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Ad> createAd(@RequestPart("ad") String adJson, @RequestPart("image") MultipartFile imageFile) throws IOException {
        Ad ad = objectMapper.readValue(adJson, Ad.class); // Deserialize JSON to Ad object
        String imageBase64 = Base64.getEncoder().encodeToString(imageFile.getBytes());
        ad.setImage(imageBase64); // Set the image data as a base64 encoded string
        Ad createdAd = adImpl.createAd(ad);
        return ResponseEntity.ok(createdAd);
    }

    @GetMapping(value = "/listAds", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Ad> getAllAds() {
        return adImpl.getAllAds();
    }


    @GetMapping(value = "getAd/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Ad getAdById(@PathVariable("id") String id) {
        return adImpl.getAdById(id);
    }

    //@PostMapping(value = "/createAd", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    //public Ad createAd(@RequestPart("ad") Ad ad, @RequestPart("image") MultipartFile imageFile) throws IOException {

    //    // Convertir l’image en Base64
     //   String imageBase64 = Base64.getEncoder().encodeToString(imageFile.getBytes());
    //    ad.setImage(imageBase64);
    //    return adImpl.createAd(ad);
    //}


    @PutMapping("updateAd")
    public Ad updateAd(@RequestBody Ad ad) {
        return adImpl.updateAd(ad);
    }

    @DeleteMapping("deleteAd/{id}")
    public void deleteAd(@PathVariable("id") String id) {
        adImpl.deleteAd(id);
    }
}