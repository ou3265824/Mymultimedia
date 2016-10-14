package com.olq.multimedias.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.olq.multimedias.bean.DaoMaster;
import com.olq.multimedias.bean.DaoSession;
import com.olq.multimedias.bean.User;
import com.olq.multimedias.bean.UserDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * 数据库操作
 */
public class DaoManager {

    private String DB_NAME="test_db";
    private static DaoManager mDaoManager;
    private Context mContext;
    private DaoMaster.OpenHelper openHelper;

    private DaoManager(Context context) {
        mContext=context;
        openHelper = new DaoMaster.DevOpenHelper(context,DB_NAME,null);
    }

    public static DaoManager getInstance(Context context){
        if(mDaoManager==null){
            synchronized (DaoManager.class){
                if (mDaoManager==null){
                    mDaoManager=new DaoManager(context);
                }
            }
        }
        return mDaoManager;
    }

    /**
     * 可读数据库
     * @return
     */
    private SQLiteDatabase getReadableDatabase(){
        if(openHelper==null){
            openHelper=new DaoMaster.DevOpenHelper(mContext,DB_NAME,null);
        }
        SQLiteDatabase db=openHelper.getReadableDatabase();
        return db;
    }

    /**
     * 可写数据库
     * @return
     */
    private SQLiteDatabase getWritableDatabase(){
        if(openHelper==null){
            openHelper=new DaoMaster.DevOpenHelper(mContext,DB_NAME,null);
        }
        SQLiteDatabase db=openHelper.getWritableDatabase();
        return db;
    }

    /**
     *添加
     * @param user
     */
    public void insertUser(User user){
        DaoMaster daoMaster=new DaoMaster(getWritableDatabase());
        DaoSession daoSession=daoMaster.newSession();
        UserDao userDao=daoSession.getUserDao();
        userDao.insert(user);
    }

    /**
     *添加集合
     * @param users
     */
    public void insertUserList(List<User> users){
        if (users == null || users.isEmpty()) {
            return;
        }
        DaoMaster daoMaster=new DaoMaster(getWritableDatabase());
        DaoSession daoSession=daoMaster.newSession();
        UserDao userDao=daoSession.getUserDao();
        userDao.insertInTx(users);
    }

    /**
     * 删除
     */
    public void deleteUser(User user){
        DaoMaster daoMaster=new DaoMaster(getWritableDatabase());
        DaoSession daoSession=daoMaster.newSession();
        UserDao userDao=daoSession.getUserDao();
        userDao.delete(user);
    }

    /**
     * 修改
     */
    public void updateUser(User user){
        DaoMaster daoMaster=new DaoMaster(getWritableDatabase());
        DaoSession daoSession=daoMaster.newSession();
        UserDao userDao=daoSession.getUserDao();
        userDao.update(user);
    }

    /**
     * 查询全部数据
     */
    public List<User> selectUser(){
        DaoMaster daoMaster=new DaoMaster(getWritableDatabase());
        DaoSession daoSession=daoMaster.newSession();
        UserDao userDao=daoSession.getUserDao();
        QueryBuilder<User> queryBuilder=userDao.queryBuilder();
        List<User> list=queryBuilder.list();
        return list;
    }

    /**
     * 查询用户列表
     */
    public List<User> queryUserList(int age) {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        QueryBuilder<User> qb = userDao.queryBuilder();
//        qb.where(UserDao.Properties.ObjectId.gt(age)).orderAsc(UserDao.Properties.ObjectId);
        List<User> list = qb.list();
        return list;
    }

}
