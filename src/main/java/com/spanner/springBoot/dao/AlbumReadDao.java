package com.spanner.springBoot.dao;

import model.spanner.Albums;
import model.spanner.Singers;

import java.util.List;

public interface AlbumReadDao {

    public List<Albums> getAlbumsBySinger(List<Singers> singers);

}
