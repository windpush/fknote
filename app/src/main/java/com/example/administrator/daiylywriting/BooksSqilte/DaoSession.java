package com.example.administrator.daiylywriting.BooksSqilte;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;


// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig nowHappentsDaoConfig;
    private final DaoConfig booksVaulesDaoConfig;
    private final DaoConfig charptersDaoConfig;
    private final DaoConfig draftBoxDaoConfig;
    private final DaoConfig userVauleDaoConfig;
    private final DaoConfig bigDataDaoConfig;
    private final DaoConfig webDataDaoConfig;
    private final DaoConfig settingDaoConfig;

    private final NowHappentsDao nowHappentsDao;
    private final BooksVaulesDao booksVaulesDao;
    private final CharptersDao charptersDao;
    private final DraftBoxDao draftBoxDao;
    private final UserVauleDao userVauleDao;
    private final BigDataDao bigDataDao;
    private final WebDataDao webDataDao;
    private final SettingDao settingDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        nowHappentsDaoConfig = daoConfigMap.get(NowHappentsDao.class).clone();
        nowHappentsDaoConfig.initIdentityScope(type);

        booksVaulesDaoConfig = daoConfigMap.get(BooksVaulesDao.class).clone();
        booksVaulesDaoConfig.initIdentityScope(type);

        charptersDaoConfig = daoConfigMap.get(CharptersDao.class).clone();
        charptersDaoConfig.initIdentityScope(type);

        draftBoxDaoConfig = daoConfigMap.get(DraftBoxDao.class).clone();
        draftBoxDaoConfig.initIdentityScope(type);

        userVauleDaoConfig = daoConfigMap.get(UserVauleDao.class).clone();
        userVauleDaoConfig.initIdentityScope(type);

        bigDataDaoConfig = daoConfigMap.get(BigDataDao.class).clone();
        bigDataDaoConfig.initIdentityScope(type);

        webDataDaoConfig = daoConfigMap.get(WebDataDao.class).clone();
        webDataDaoConfig.initIdentityScope(type);

        settingDaoConfig = daoConfigMap.get(SettingDao.class).clone();
        settingDaoConfig.initIdentityScope(type);

        nowHappentsDao = new NowHappentsDao(nowHappentsDaoConfig, this);
        booksVaulesDao = new BooksVaulesDao(booksVaulesDaoConfig, this);
        charptersDao = new CharptersDao(charptersDaoConfig, this);
        draftBoxDao = new DraftBoxDao(draftBoxDaoConfig, this);
        userVauleDao = new UserVauleDao(userVauleDaoConfig, this);
        bigDataDao = new BigDataDao(bigDataDaoConfig, this);
        webDataDao = new WebDataDao(webDataDaoConfig, this);
        settingDao = new SettingDao(settingDaoConfig, this);

        registerDao(NowHappents.class, nowHappentsDao);
        registerDao(BooksVaules.class, booksVaulesDao);
        registerDao(Charpters.class, charptersDao);
        registerDao(DraftBox.class, draftBoxDao);
        registerDao(UserVaule.class, userVauleDao);
        registerDao(BigData.class, bigDataDao);
        registerDao(WebData.class, webDataDao);
        registerDao(Setting.class, settingDao);
    }
    
    public void clear() {
        nowHappentsDaoConfig.getIdentityScope().clear();
        booksVaulesDaoConfig.getIdentityScope().clear();
        charptersDaoConfig.getIdentityScope().clear();
        draftBoxDaoConfig.getIdentityScope().clear();
        userVauleDaoConfig.getIdentityScope().clear();
        bigDataDaoConfig.getIdentityScope().clear();
        webDataDaoConfig.getIdentityScope().clear();
        settingDaoConfig.getIdentityScope().clear();
    }

    public NowHappentsDao getNowHappentsDao() {
        return nowHappentsDao;
    }

    public BooksVaulesDao getBooksVaulesDao() {
        return booksVaulesDao;
    }

    public CharptersDao getCharptersDao() {
        return charptersDao;
    }

    public DraftBoxDao getDraftBoxDao() {
        return draftBoxDao;
    }

    public UserVauleDao getUserVauleDao() {
        return userVauleDao;
    }

    public BigDataDao getBigDataDao() {
        return bigDataDao;
    }

    public WebDataDao getWebDataDao() {
        return webDataDao;
    }

    public SettingDao getSettingDao() {
        return settingDao;
    }

}