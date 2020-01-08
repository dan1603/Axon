package com.example.axon.usecases.repository.data_source.database.dao

import androidx.paging.DataSource
import androidx.room.*
import com.example.axon.usecases.repository.data_source.database.entity.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM users WHERE uid = :id")
    fun queryById(id: Int): UserEntity

    @Insert(onConflict=OnConflictStrategy.REPLACE)
    fun insert(listEntities: List<UserEntity>)

    @Query("DELETE FROM users WHERE cached = 1")
    fun deleteCachedItems()

    @Query("SELECT * FROM users")
    fun queryItems(): DataSource.Factory<Int, UserEntity>

    @Query("DELETE FROM users")
    fun deleteAllItems()

    @Transaction
    fun insertAndClearCache(
        listEntities: List<UserEntity>
    ) {
        listEntities.forEach { it.let { deleteAllItems() } }
        insert(listEntities)
    }
}