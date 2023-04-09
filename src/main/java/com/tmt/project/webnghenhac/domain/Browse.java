package com.tmt.project.webnghenhac.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "browse")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Browse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties(value = {"playlists","albums","singles"}, allowSetters = true)
    private Category category;

    @OneToOne
    @JoinColumn(name = "picture_id")
    private Picture picture;
}
