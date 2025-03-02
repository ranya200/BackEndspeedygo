package tn.esprit.examen.SpeedyGo.Services;
import tn.esprit.examen.SpeedyGo.entities.FastPost;

import java.util.List;

public interface IFastPostService {
    FastPost addFastPost(FastPost fastPost);
    void deleteFastPost(String idF);
    FastPost modifyFastPost(String idF, FastPost updatedfastPost);
    List<FastPost> getAllFastPosts();
    FastPost getFastPost(String FPId);
    void approveFastPost(String FPId);
    void rejectFastPost(String FPId);
}
