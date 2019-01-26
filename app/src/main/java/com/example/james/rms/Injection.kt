package com.example.james.rms

import com.example.james.rms.data.remote.LoginRemoteDataSource
import com.example.james.rms.data.remote.TestRemoteDataSource
import com.example.james.rms.data.repo.LoginRepository
import com.example.james.rms.data.repo.TestRepository

/**
 * Created by steve_000 on 22/1/2019.
 * for project RMS
 * package name com.example.james.rms
 */
object Injection {

    fun provideLoginRepository():LoginRepository{
        return LoginRepository(LoginRemoteDataSource())
    }
    @JvmStatic
    fun provideTestRepository():TestRepository{
        return TestRepository(TestRemoteDataSource())
    }
}