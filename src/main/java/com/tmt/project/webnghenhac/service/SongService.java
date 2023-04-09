package com.tmt.project.webnghenhac.service;

import com.tmt.project.webnghenhac.domain.Song;
import com.tmt.project.webnghenhac.service.request.CreateSongRequest;

import java.util.List;

public interface SongService {
    public List<Song> getAllActivatedSong();

    List<Song> getAllInProcessSong();
    List<Song> getAllDoneSong();

    List<Song>getAllSongs();
    public Song getSongById(Integer id);

    public Song createNewSong(CreateSongRequest song);

    public Song updateSongById(Integer id, CreateSongRequest song);

    Song activateSongById(Integer id);

    Song updateSongArtistById(Integer id,Song song);

}
