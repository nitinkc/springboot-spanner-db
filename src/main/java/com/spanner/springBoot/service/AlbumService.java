package com.spanner.springBoot.service;

import com.spanner.springBoot.dao.AlbumReadDao;
import com.spanner.springBoot.dao.spanner.SingerRead;
import com.spanner.springBoot.dto.AlbumsDto;
import com.spanner.springBoot.dto.SingersDto;
import com.spanner.springBoot.model.spanner.Albums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {
    @Autowired
    private AlbumReadDao albumReadDao;

    public List<AlbumsDto> getAlbumsBySingerId(List<String> singerIds) {
        return albumReadDao.getAlbumsBySingerIds(singerIds);
    }
}
