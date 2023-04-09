package com.tmt.project.webnghenhac.service.impl;

import com.tmt.project.webnghenhac.domain.*;
import com.tmt.project.webnghenhac.repository.ArtistRepository;
import com.tmt.project.webnghenhac.repository.MusicRepository;
import com.tmt.project.webnghenhac.repository.SongRepository;
import com.tmt.project.webnghenhac.service.AlbumService;
import com.tmt.project.webnghenhac.service.PlaylistService;
import com.tmt.project.webnghenhac.service.SingleService;
import com.tmt.project.webnghenhac.service.SongService;
import com.tmt.project.webnghenhac.service.request.CreateSongRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SongServiceImpl implements SongService {
    private final SongRepository songRepository;
    private final AlbumService albumService;
    private final SingleService singleService;

    private final PlaylistService playlistService;

    private final MusicRepository musicRepository;

    private final ArtistRepository artistRepository;


    public SongServiceImpl(SongRepository songRepository, AlbumService albumService, SingleService singleService, PlaylistService playlistService, MusicRepository musicRepository, ArtistRepository artistRepository) {
        this.songRepository = songRepository;
        this.albumService = albumService;
        this.singleService = singleService;
        this.playlistService = playlistService;
        this.musicRepository = musicRepository;
        this.artistRepository = artistRepository;
    }

    @Override
    public List<Song> getAllActivatedSong() {
        var songs = this.songRepository.getAllActivatedSong();
        return songs;
    }

    @Override
    public List<Song> getAllInProcessSong() {
        var songs = this.songRepository.getAllInProcessSong();
        return songs;
    }

    @Override
    public List<Song> getAllDoneSong() {
        var songs = this.songRepository.getAllDoneSong();
        return songs;
    }

    @Override
    public List<Song> getAllSongs() {
        var songs = this.songRepository.findAll();
        return songs;
    }

    @Override
    public Song getSongById(Integer id) {
        var checkSong = this.songRepository.findById(id);
        if (!checkSong.isPresent()) {
            throw new RuntimeException("Id Not Found !");
        }
        var realSong = checkSong.get();
        return realSong;
    }

    @Transactional
    @Override
    public Song createNewSong(CreateSongRequest song) {
        Song newSong = new Song();
        newSong.setTitle((song.getTitle()));
        newSong.setLength((song.getLength()));
        Playlist playlist = this.playlistService.getPlaylistById(song.getPlaylistId());
        Single single = this.singleService.getSingleById(song.getSingleId());
        Album album = this.albumService.getAlbumById(song.getAlbumId());
        Optional<Music> music = this.musicRepository.findById(song.getMusicId());
        newSong.setAlbum(album);
        newSong.setSingle(single);
        newSong.setPlaylist(playlist);
        newSong.setMusic(music.get());
        newSong.setIsValid(false);
        newSong.setStatus(false);
        this.songRepository.save(newSong);
        return newSong;
    }

    @Transactional
    public Song updateSongById(Integer id, CreateSongRequest song) {
        var checkedSong = this.songRepository.findById(id);
        if (!checkedSong.isPresent()) {
            throw new RuntimeException("Id not found");
        }
        var realSong = checkedSong.get();
        realSong.setTitle(song.getTitle());
        realSong.setLength(song.getLength());
        Playlist playlist = this.playlistService.getPlaylistById(song.getPlaylistId());
        Single single = this.singleService.getSingleById(song.getSingleId());
        Album album = this.albumService.getAlbumById(song.getAlbumId());
        Optional<Music> music = this.musicRepository.findById(song.getMusicId());
        realSong.setAlbum(album);
        realSong.setSingle(single);
        realSong.setPlaylist(playlist);
        realSong.setMusic(music.get());
        realSong.setIsValid(true);
        this.songRepository.save(realSong);
        return realSong;
    }

    @Override
    public Song activateSongById(Integer id) {
        var checkSong = this.songRepository.findById(id);
        if (!checkSong.isPresent()) {
            throw new RuntimeException("Id Not Found !");
        }
        var realSong = checkSong.get();
        realSong.setStatus(true);
        realSong.setIsValid(true);
        this.songRepository.save(realSong);
        return realSong;
    }

    @Override
    public Song updateSongArtistById(Integer id, Song song) {
        var checkSong = this.songRepository.findById(id);
        if (!checkSong.isPresent()) {
            throw new RuntimeException("Id Not Found !");
        }
        var realSong = checkSong.get();
        realSong.getArtists().addAll(song
                .getArtists()
                .stream()
                .map(v ->{
                    Artist a = this.artistRepository.findById(v.getId()).get();
                    a.getSongs().add(realSong);
                    return a;
                }).collect(Collectors.toList())
        );
        realSong.setStatus(true);
        this.songRepository.save(realSong);
        return realSong;
    }

}
