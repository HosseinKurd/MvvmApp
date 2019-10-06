package com.hosseinkurd.mvvmapp.database

import io.objectbox.BoxStore

/**
 * DbRepo = AppDataBase Repository
 */
class DbRepo(boxStore: BoxStore) : DbRepository {

    /*private val TAG = "BOX_TAG"
    private val userBox: Box<UserData>

    val isUserEmpty: Boolean?
        get() = userBox.isEmpty()

    val allUsers: List<UserData>
        get() = userBox.query().order(User_.cowId).build().find()*/

    init {
        // this.userBox = boxStore.boxFor(UserData::class.java!!)
    }

    /*fun insertUser(user: UserData): Long {
        return userBox.put(user)
    }

    fun insertUsers(users: List<UserData>) {
        userBox.put(users)
    }

    fun getUserByID(id: Long?): UserData {
        return userBox.get(id)
    }

    fun getUserByDeviceID(milkMeterId: Int): UserData {
        return userBox.query().equal(User_.milkMeterId, milkMeterId).build().findFirst()
    }

    fun getUserByCowID(cowID: Int): UserData {
        return userBox.query().equal(User_.cowId, cowID).build().findFirst()
    }

    fun getAllUserByCowID(cowID: Int): List<UserData> {
        return userBox.query().equal(User_.cowId, cowID).order(User_.finishedTimestamp).build().find()
    }

    fun getUsersInRange(limit: Int, offset: Int): List<UserData> {
        return userBox.query().build().find(offset, limit)
    }

    fun removeAllUser() {
        userBox.removeAll()
    }*/
}