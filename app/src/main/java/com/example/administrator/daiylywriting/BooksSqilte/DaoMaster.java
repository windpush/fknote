package com.example.administrator.daiylywriting.BooksSqilte;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import de.greenrobot.dao.AbstractDaoMaster;
import de.greenrobot.dao.identityscope.IdentityScopeType;


// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * Master of DAO (schema version 1): knows all DAOs.
*/
public class DaoMaster extends AbstractDaoMaster {
    public static final int SCHEMA_VERSION = 1;

    /** Creates underlying database table using DAOs. */
    public static void createAllTables(SQLiteDatabase db, boolean ifNotExists) {
        NowHappentsDao.createTable(db, ifNotExists);
        BooksVaulesDao.createTable(db, ifNotExists);
        CharptersDao.createTable(db, ifNotExists);
        DraftBoxDao.createTable(db, ifNotExists);
        UserVauleDao.createTable(db, ifNotExists);
        BigDataDao.createTable(db, ifNotExists);
        WebDataDao.createTable(db, ifNotExists);
        SettingDao.createTable(db, ifNotExists);
    }
    
    /** Drops underlying database table using DAOs. */
    public static void dropAllTables(SQLiteDatabase db, boolean ifExists) {
        NowHappentsDao.dropTable(db, ifExists);
        BooksVaulesDao.dropTable(db, ifExists);
        CharptersDao.dropTable(db, ifExists);
        DraftBoxDao.dropTable(db, ifExists);
        UserVauleDao.dropTable(db, ifExists);
        BigDataDao.dropTable(db, ifExists);
        WebDataDao.dropTable(db, ifExists);
        SettingDao.dropTable(db, ifExists);
    }
    
    public static abstract class OpenHelper extends SQLiteOpenHelper {

        public OpenHelper(Context context, String name, CursorFactory factory) {
            super(context, name, factory, SCHEMA_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.i("greenDAO", "Creating tables for schema version " + SCHEMA_VERSION);
            createAllTables(db, false);
        }
    }
    
    /** WARNING: Drops all table on Upgrade! Use only during development. */
    public static class DevOpenHelper extends OpenHelper {
        public DevOpenHelper(Context context, String name, CursorFactory factory) {
            super(context, name, factory);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.i("greenDAO", "Upgrading schema from version " + oldVersion + " to " + newVersion + " by dropping all tables");
            dropAllTables(db, true);
            onCreate(db);
        }
    }

    public DaoMaster(SQLiteDatabase db) {
        super(db, SCHEMA_VERSION);
        registerDaoClass(NowHappentsDao.class);
        registerDaoClass(BooksVaulesDao.class);
        registerDaoClass(CharptersDao.class);
        registerDaoClass(DraftBoxDao.class);
        registerDaoClass(UserVauleDao.class);
        registerDaoClass(BigDataDao.class);
        registerDaoClass(WebDataDao.class);
        registerDaoClass(SettingDao.class);
    }
    
    public DaoSession newSession() {
        return new DaoSession(db, IdentityScopeType.Session, daoConfigMap);
    }
    
    public DaoSession newSession(IdentityScopeType type) {
        return new DaoSession(db, type, daoConfigMap);
    }
    
}