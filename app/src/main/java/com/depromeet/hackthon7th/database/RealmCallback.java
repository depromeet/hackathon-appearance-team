package com.depromeet.hackthon7th.database;

public interface RealmCallback {
    void onSuccess();

    void onError(Throwable error);
}
