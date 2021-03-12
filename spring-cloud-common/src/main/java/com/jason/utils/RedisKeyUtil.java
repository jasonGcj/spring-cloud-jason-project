package com.jason.utils;

/**
 * @ClassName RedisKeyUtil
 * @Description TODO
 * @Author GCJ
 * @Date 2020/10/14 10:51
 */
public class RedisKeyUtil {
    /**
     * 文章点赞
     */
    public static final String MAP_KEY_ART_LIKED="MAP_ART_LIKED:";
    /**
     * 文章点赞数
     */
    public static final String MAP_KEY_ART_LIKED_COUNT="MAP_ART_LIKED_COUNT:";

    /**
     * 文章收藏
     */
    public static final String MAP_KEY_ART_COLLECT="MAP_ART_COLLECT:";
    /**
     * 收藏的数量
     */
    public static final String MAP_KEY_ART_COLLECT_COUNT="MAP_ART_COLLECT_COUNT";

    /**
     * 文章访问量
     */
    public static final String ARTICLE_COUNT="ARTICLE_COUNT";

    /**
     * 红包标识
     */
    public static final String RED_PACKAGE="RED_P:";

    /**
     * 拼接点赞的key
     * @param articlId
     * @param userId
     * @return
     */
    public static String getLikedKey(String articlId,String userId){
        StringBuilder sb = new StringBuilder();
        sb.append(articlId);
        sb.append("-");
        sb.append(userId);
        return sb.toString();

    }
}
