package com.olq.multimedias.http.okgo;

/**
 * Created by Administrator on 2016/10/20 0020.
 */

public class OkUrlConfig {

    /**
     * 基础	www.tngou.net/tnfs/api/classify	取得图片分类，可以通过分类id取得热词列表
     重要	www.tngou.net/tnfs/api/list	取得图片列表，也可以用分类id作为参数
     一般	www.tngou.net/tnfs/api/news	取得最新的图片，通过id取得大于该id的图片
     重要	www.tngou.net/tnfs/api/show	取得热点图片详情，通过热点id取得该对应详细内容信息
     重要	http://www.tngou.net/api/favorite/add	收藏接口，需要用户认证
     重要	http://www.tngou.net/api/memo/add	评论接口，需要用户认证
     重要	http://www.tngou.net/api/memo/comment	评论列表
     */
    static String IMG_HTTP="http://tnfs.tngou.net/image";
    static String BASE_HTTP="http://www.tngou.net/";
    static String TNFS=BASE_HTTP+"tnfs/api/";
    static String API=BASE_HTTP+"api/";
    /**
     * 分类
     */
    static String CLASSIFY=TNFS+"classify";
    /**
     * 列表
     */
    static String LIST=TNFS+"list";
    /**
     * 最新
     */
    static String NEWS=TNFS+"news";
    /**
     * 热点详情
     */
    static String SHOW=TNFS+"show";
    /**
     * 收藏
     */
    static String FAVORITE=API+"favorite/add";
    /**
     * 评论
     */
    static String COMMENT=API+"memo/add";
    /**
     * 评论列表
     */
    static String COMMENT_LIST=API+"memo/comment";

    public static String getNEWS() {
        return NEWS;
    }

    public static String getNEWS(int id) {
        return NEWS+"?id="+id;
    }
}
