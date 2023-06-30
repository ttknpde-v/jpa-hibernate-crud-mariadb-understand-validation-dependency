package com.ttknpdev.mariadb.springbootcrudmariadb.service;

import java.util.List;
import java.util.Map;

public interface ClientService <T> {
    T create(T obj);
    T read(String phone);
    List<T> reads();
    List<T> readsByStatus(Integer status);
    T update(String phone , T obj);
    Map<?,?> delete(String phone);
    Map<?,?> deleteAllState(int state);


}
