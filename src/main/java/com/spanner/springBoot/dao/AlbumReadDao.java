package com.spanner.springBoot.dao;

import com.spanner.springBoot.dto.AlbumsDto;
import com.spanner.springBoot.model.spanner.Albums;
import com.spanner.springBoot.model.spanner.Singers;

import java.util.List;

public interface AlbumReadDao{

    public List<AlbumsDto> getAlbumsBySingerIds(List<String> singerIds);

}
