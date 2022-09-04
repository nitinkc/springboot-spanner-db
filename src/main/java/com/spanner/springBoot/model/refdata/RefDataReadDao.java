package com.spanner.springBoot.model.refdata;

import java.util.Collection;
import java.util.List;


public interface RefDataReadDao {

    <K extends ReferenceDataBase> K findById(Class<K> theClass, String  id);

    <K extends ReferenceDataBase> List<K> findByIds(Class<K> theClass, Collection<String> ids);

    <K extends ReferenceDataBase> List<K> findAll(Class<K> theClass);

    //in future will think about this, as of now plans to develop this method
    <K extends ReferenceDataBase> List<K> findByCategoryRefData(Class<K> theClass, Collection<String> category);
}
