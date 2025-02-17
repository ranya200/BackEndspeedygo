package tn.esprit.examen.SpeedyGo.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.examen.SpeedyGo.Repository.FastPostRepository;
import tn.esprit.examen.SpeedyGo.entities.Delivery;
import tn.esprit.examen.SpeedyGo.entities.FastPost;

import java.util.List;

@Service
@AllArgsConstructor
public class FastPostService implements IFastPostService{
    FastPostRepository fastPostRepository;
    public FastPost addFastPost(FastPost fastPost) {
        return fastPostRepository.save(fastPost);
    }

    public void deleteFastPost(String idF) {
        fastPostRepository.deleteById(idF);
    }

    public FastPost modifyFastPost(FastPost fastPost) {
        return fastPostRepository.save(fastPost);
    }

    public List<FastPost> getAllFastPosts() {
        List<FastPost> fastPosts = fastPostRepository.findAll();
        return fastPosts;
    }
    public FastPost getFastPost(String FPId) {
        return fastPostRepository.findById(FPId).get();
    }
}
