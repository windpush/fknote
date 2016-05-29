package com.example.administrator.daiylywriting.BooksSqilte;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.example.administrator.daiylywriting.BooksSqilte.DaoSession;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;


// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table SETTING.
*/
public class SettingDao extends AbstractDao<Setting, Long> {

    public static final String TABLENAME = "SETTING";

    /**
     * Properties of entity Setting.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property TimeToSearch = new Property(1, Integer.class, "TimeToSearch", false, "TIME_TO_SEARCH");
        public final static Property IsWebSearch = new Property(2, Boolean.class, "isWebSearch", false, "IS_WEB_SEARCH");
        public final static Property WebAddress = new Property(3, String.class, "WebAddress", false, "WEB_ADDRESS");
        public final static Property IsItemAdd = new Property(4, Boolean.class, "isItemAdd", false, "IS_ITEM_ADD");
    };


    public SettingDao(DaoConfig config) {
        super(config);
    }
    
    public SettingDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'SETTING' (" + //
                "'_id' INTEGER PRIMARY KEY ," + // 0: id
                "'TIME_TO_SEARCH' INTEGER," + // 1: TimeToSearch
                "'IS_WEB_SEARCH' INTEGER," + // 2: isWebSearch
                "'WEB_ADDRESS' TEXT," + // 3: WebAddress
                "'IS_ITEM_ADD' INTEGER);"); // 4: isItemAdd
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'SETTING'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Setting entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Integer TimeToSearch = entity.getTimeToSearch();
        if (TimeToSearch != null) {
            stmt.bindLong(2, TimeToSearch);
        }
 
        Boolean isWebSearch = entity.getIsWebSearch();
        if (isWebSearch != null) {
            stmt.bindLong(3, isWebSearch ? 1l: 0l);
        }
 
        String WebAddress = entity.getWebAddress();
        if (WebAddress != null) {
            stmt.bindString(4, WebAddress);
        }
 
        Boolean isItemAdd = entity.getIsItemAdd();
        if (isItemAdd != null) {
            stmt.bindLong(5, isItemAdd ? 1l: 0l);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Setting readEntity(Cursor cursor, int offset) {
        Setting entity = new Setting( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1), // TimeToSearch
            cursor.isNull(offset + 2) ? null : cursor.getShort(offset + 2) != 0, // isWebSearch
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // WebAddress
            cursor.isNull(offset + 4) ? null : cursor.getShort(offset + 4) != 0 // isItemAdd
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Setting entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setTimeToSearch(cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1));
        entity.setIsWebSearch(cursor.isNull(offset + 2) ? null : cursor.getShort(offset + 2) != 0);
        entity.setWebAddress(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setIsItemAdd(cursor.isNull(offset + 4) ? null : cursor.getShort(offset + 4) != 0);
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Setting entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Setting entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}