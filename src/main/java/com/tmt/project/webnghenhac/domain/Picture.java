package com.tmt.project.webnghenhac.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "picture")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 40)
    @Column(name = "picture_name", length = 40)
    private String pictureName;

    @Size(max = 200)
    @Column(name = "picture_url", length = 200)
    private String pictureURL;

    @Size(max = 500)
    @Column(name = "picture_alt", length = 500)
    private String pictureAlt;

    @Size(max = 20)
    @Column(name = "upload_date", length = 20)
    private LocalDate uploadDate;


}
