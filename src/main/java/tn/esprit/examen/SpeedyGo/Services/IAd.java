package tn.esprit.examen.SpeedyGo.Services;

import tn.esprit.examen.SpeedyGo.entities.Ad;

import java.util.List;

public interface IAd {

    Ad createAd(Ad ad);
    List<Ad> getAllAds();
    Ad getAdById(String id);

    Ad updateAd(Ad ad);
    void deleteAd(String id);
}
