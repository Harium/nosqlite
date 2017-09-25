package com.harium.database.nosqlite;

import com.harium.database.sqlite.module.SQLiteDatabaseModule;
import com.j256.ormlite.support.ConnectionSource;

public class Database extends BaseDatabase<ConnectionSource> {

    public final static String DATABASE = "database.sqlite";

    public static boolean clearOnInit = false;

    public static void init() {
        init(DATABASE);
    }

    public static void init(String databaseName) {
        databaseModule = new SQLiteDatabaseModule(databaseName);
        databaseModule.register(baseDAO);
        databaseModule.init(clearOnInit);
    }

    @Override
    public void init(ConnectionSource connection) {
        baseDAO.init(connection);
    }
}