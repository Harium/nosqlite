package com.harium.database.nosqlite;

import com.harium.database.dao.OrmLiteBaseDAOImpl;
import com.harium.database.model.BaseDAO;
import com.harium.database.module.DatabaseModule;
import com.harium.database.sqlite.module.SQLiteDatabaseModule;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class Database implements BaseDAO<Data, ConnectionSource> {

    public final static String DATABASE = "database.sqlite";

    public static boolean clearOnInit = false;
    private static DatabaseModule databaseModule;

    private static OrmLiteBaseDAOImpl<Data> baseDAO = new OrmLiteBaseDAOImpl<Data>(Data.class);

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

    @Override
    public List<Data> queryAll() {
        return baseDAO.queryAll();
    }

    @Override
    public int create(Data item) {
        item.setCreatedAt(new Date().getTime());
        return baseDAO.create(item);
    }

    @Override
    public int update(Data item) {
        item.setUpdatedAt(new Date().getTime());
        return baseDAO.update(item);
    }

    @Override
    public int delete(Data data) {
        return baseDAO.delete(data);
    }

    @Override
    public Class<Data> getKlass() {
        return baseDAO.getKlass();
    }

    public static long count() {
        return baseDAO.count();
    }

    public static Data queryData(String key) {
        try {
            List<Data> result = baseDAO.queryForEq(Data.COLUMN_KEY, key);
            if (!result.isEmpty()) {
                return result.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void add(String key, String value) {
        Data data = new Data(key, value);
        baseDAO.create(data);
    }

    public static String get(String key) {
        Data value = queryData(key);
        if (value != null) {
            return value.getValue();
        }

        return null;
    }

    public static void delete(String key) {
        baseDAO.delete(new Data(key, ""));
    }
}