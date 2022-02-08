package com.spanner.springBoot.dao;

import model.spanner.Singers;

import java.util.List;

public interface SingerReadDao {

    public List<Singers> findSingersByFirstChar(Character c);
}
