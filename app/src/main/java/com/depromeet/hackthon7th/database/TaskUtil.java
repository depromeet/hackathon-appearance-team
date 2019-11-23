package com.depromeet.hackthon7th.database;

import io.realm.Realm;
import io.realm.RealmResults;
import java.util.List;

public class TaskUtil {
    // 추가
    public static void addTask(Task task, RealmCallback callback) {
        Realm.getDefaultInstance().executeTransactionAsync(realm -> {
           Number currentId = realm.where(Task.class).max("id");
           int maxId;
           if (currentId != null) {
               maxId = currentId.intValue() + 1;
           } else {
               maxId = 1;
           }
           task.setId(maxId);
           realm.copyToRealm(task);
        }, callback::onSuccess, callback::onError);
    }

    // 삭제
    public static void deleteTask(int id, RealmCallback callback) {
        Realm.getDefaultInstance().executeTransactionAsync(realm -> {
          Task realmTask = realm.where(Task.class).equalTo("id", id).findFirst();

            if (realmTask != null) {
                realmTask.deleteFromRealm();
                callback.onSuccess();
            } else {
                callback.onError(new Throwable("Task 조회 결과 없음"));
            }
        }, callback::onSuccess, callback::onError);
    }

    // 리스트 조회
    public static List<Task> getTasks() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Task> realmResults = realm.where(Task.class).findAll();

        if (realmResults != null) {
            return realm.copyFromRealm(realmResults);
        } else {
            return null;
        }
    }

    // id 조회
    public static Task getTaskById(int id) {
        Realm realm = Realm.getDefaultInstance();
        Task realmTask = realm.where(Task.class).equalTo("id", id).findFirst();

        if (realmTask != null) {
            return realm.copyFromRealm(realmTask);
        } else {
            return null;
        }
    }

    // 업데이트
    public static void updateTask(Task task, RealmCallback callback) {
        Realm.getDefaultInstance().executeTransactionAsync(realm -> {
          realm.copyToRealmOrUpdate(task);
        }, callback::onSuccess, callback::onError);
    }

    // 완료처리
    public static void setDone(Task task, RealmCallback callback) {
        Realm.getDefaultInstance().executeTransactionAsync(realm -> {
            Task realmTask = realm.where(Task.class).equalTo("id", task.getId()).findFirst();

            if (realmTask != null) {
                realmTask.setDone(true);
            } else {
                callback.onError(new Throwable("Task 조회 결과 실패"));
            }
        }, callback::onSuccess, callback::onError);
    }
}
