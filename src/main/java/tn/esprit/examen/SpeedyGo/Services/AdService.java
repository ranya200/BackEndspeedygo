package tn.esprit.examen.SpeedyGo.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.examen.SpeedyGo.Repository.AdRepo;
import tn.esprit.examen.SpeedyGo.entities.Ad;
import tn.esprit.examen.SpeedyGo.entities.AdCategory;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Slf4j
@Service
public class AdService implements IAd {

    @Autowired
    private AdRepo adRepo;



    @Override
    public List<Ad> getAllAds() {
        return adRepo.findAll();
    }
    @Override
    public Ad createAd(Ad ad) {
        if (ad.getCategory() != null && !Arrays.asList(AdCategory.values()).contains(ad.getCategory())) {
            throw new IllegalArgumentException("Invalid category value");
        }
        return adRepo.save(ad);
    }
    @Override
    public Ad getAdById(String id) {
        return adRepo.findById(id).orElse(null);
    }

    @Override
    public Ad updateAd(Ad ad) {
        return adRepo.save(ad);
    }

    @Override
    public void deleteAd(String id) {
        adRepo.deleteById(id);
    }
}