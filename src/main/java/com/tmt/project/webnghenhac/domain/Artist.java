package com.tmt.project.webnghenhac.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "artist")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "full_name", nullable = false)
    private String name;

    @Column(name = "description",nullable = false)
    private String description;

    @Column(name = "date_of_birth", nullable = false, length = 20)
    private String dateOfBirth;

    @Column(name = "place_of_birth",nullable = false, length = 20)
    private String placeOfBirth;

    @Column(name = "is_valid")
    private Boolean isValid;

    @Column(name = "status")
    private Boolean status;

    @OneToOne
    @JoinColumn(name = "picture_id")
    private Picture picture;
    @ManyToMany
    @JoinTable(name = "artist_songs",
            joinColumns = @JoinColumn(name = "artist_id"),
            inverseJoinColumns = @JoinColumn(name ="song_id"))
    @JsonIgnoreProperties(value = {"status","isValid","artists","playlist"},allowSetters = true)
    private List<Song> songs = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "artist_albums",
            joinColumns = @JoinColumn(name = "artist_id"),
            inverseJoinColumns = @JoinColumn(name = "album_id")
    )
    @JsonIgnoreProperties(value = {"status","isValid","category","artists","songs"} ,allowSetters = true)
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private List<Album> albums = new ArrayList<>();
}
