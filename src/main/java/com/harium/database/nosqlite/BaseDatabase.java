package com.harium.database.nosqlite;

import com.harium.database.dao.OrmLiteBaseDAOImpl;
import com.harium.database.model.BaseDAO;
import com.harium.database.module.DatabaseModule;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public abstract class BaseDatabase<T> implements BaseDAO<Data, T> {

    protected static DatabaseModule databaseModule;

    protected static OrmLiteBaseDAOImpl<Data> baseDAO = new OrmLiteBaseDAOImpl<>(Data.class);

    @Override
    public List<Data> queryAll() {
        return baseDAO.queryAll();
    }

    @Override
    public int create(Data item) {
        item.setCreatedAt(new Date().getTime());
        item.setUpdatedAt(new Date().getTime());
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

    public static void put(String key, String value) {
        Data data = new Data(key, value);
        data.setUpdatedAt(new Date().getTime());
        baseDAO.createOrUpdate(data);
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

    public void dispose() {

    }
}
