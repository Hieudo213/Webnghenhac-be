package com.tmt.project.webnghenhac.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "category")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title", nullable = false,length = 20)
    private String name;

    @Column(name = "description", nullable = false, length = 1000)
    private String description;

    @OneToOne
    @JoinColumn(name = "picture_id")
    private Picture picture;

    @Column(name = "is_valid")
    private Boolean isValid;

    @OneToMany(mappedBy = "category")
    @JsonIgnoreProperties(value = {"albums", "category"}, allowSetters = true)
    private List<Album> albums = new ArrayList<>();

    @OneToMany(mappedBy = "category")
    @JsonIgnoreProperties(value = {"category","songs","album"},allowSetters = true)
    private List<Single> singles = new ArrayList<>();

    @OneToMany(mappedBy = "category")
    @JsonIgnoreProperties(value = {"category"},allowSetters = true)
    private List<Playlist> playlists = new ArrayList<>();

    @OneToMany(mappedBy = "category")
    @JsonIgnoreProperties(value = {"category","songs"},allowSetters = true)
    private List<Browse> browses = new ArrayList<>();
}
