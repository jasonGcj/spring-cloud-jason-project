package com.jason.user;

/**
 * @ClassName UserContext
 * @Description TODO
 * @Author GCJ
 * @Date 2020/9/17 14:56
 */
public final class UserContext {

    private static final ThreadLocal<IUser> USER_STORE = new ThreadLocal();

    private UserContext() {
    }

    public static IUser getCurrentUser() {
        return (IUser)USER_STORE.get();
    }

    public static void setCurrentUser(IUser user) {
        USER_STORE.set(user);
    }

    public static void remove() {
        //USER_STORE.set((Object)null);
        USER_STORE.set((IUser) null);
    }

}
