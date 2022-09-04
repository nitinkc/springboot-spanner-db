package com.spanner.springBoot.dao;

import com.spanner.springBoot.dto.SingersDto;
import com.spanner.springBoot.model.spanner.Singers;

import java.util.List;

public interface SingerReadDao {

    public List<SingersDto> findSingersByFirstChar(Character c);
}
