package com.ttknpdev.mariadb.springbootcrudmariadb.log;

import com.ttknpdev.mariadb.springbootcrudmariadb.dao.DaoClient;
import org.apache.log4j.Logger;

public class Logging {
    public static final Logger daoClient = Logger.getLogger(DaoClient.class);
}
