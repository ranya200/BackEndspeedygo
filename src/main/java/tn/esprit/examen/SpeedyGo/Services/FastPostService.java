package tn.esprit.examen.SpeedyGo.Services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.examen.SpeedyGo.Repository.FastPostRepository;
import tn.esprit.examen.SpeedyGo.entities.Delivery;
import tn.esprit.examen.SpeedyGo.entities.FastPost;
import tn.esprit.examen.SpeedyGo.entities.Status;
import tn.esprit.examen.SpeedyGo.entities.Vehicle;

import java.util.List;
import java.util.Optional;

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

    public FastPost modifyFastPost(String idF, FastPost updatedfastPost) {
        return fastPostRepository.findById(idF).map(fastPost -> {
            fastPost.setReceiverName(updatedfastPost.getReceiverName());
            fastPost.setReceiverAddress(updatedfastPost.getReceiverAddress());
            fastPost.setReceiverTelNbr(updatedfastPost.getReceiverTelNbr());
            fastPost.setPackageWeight(updatedfastPost.getPackageWeight());
            fastPost.setFastPostStatus(updatedfastPost.getFastPostStatus());
            return fastPostRepository.save(fastPost);
        }).orElseThrow(() -> new RuntimeException("❌ Vehicle not found with ID: " + idF));

    }

    public List<FastPost> getAllFastPosts() {
        List<FastPost> fastPosts = fastPostRepository.findAll();
        return fastPosts;
    }
    public FastPost getFastPost(String FPId) {
        return fastPostRepository.findById(FPId).get();
    }

    public void approveFastPost(String FPId){
        FastPost fastPost = fastPostRepository.findById(FPId).orElse(null); // Retrieve the vehicle by ID.findById(id).orElse(null);
        if (fastPost != null) {
            fastPost.setFastPostStatus(Status.APPROVED);
            fastPostRepository.save(fastPost);
        }
    }

    public void rejectFastPost(String FPId){
        FastPost fastPost = fastPostRepository.findById(FPId).orElse(null); // Retrieve the vehicle by ID.findById(id).orElse(null);
        if (fastPost != null) {
            fastPost.setFastPostStatus(Status.REJECTED);
            fastPostRepository.save(fastPost);
        }
    }

}
