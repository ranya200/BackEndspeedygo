package tn.esprit.examen.SpeedyGo.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "ad") // MongoDB annotation
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ad {
    @Id
    private String id;
    private String title;
    private String description;
    private String image;
    private String video;
    private String category;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String Status;
}