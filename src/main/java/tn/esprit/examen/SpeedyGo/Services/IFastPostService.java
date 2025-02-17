package tn.esprit.examen.SpeedyGo.Services;
import tn.esprit.examen.SpeedyGo.entities.FastPost;

import java.util.List;

public interface IFastPostService {
    FastPost addFastPost(FastPost fastPost);
    void deleteFastPost(String idF);
    FastPost modifyFastPost(FastPost fastPost);
    List<FastPost> getAllFastPosts();
    FastPost getFastPost(String FPId);
}
