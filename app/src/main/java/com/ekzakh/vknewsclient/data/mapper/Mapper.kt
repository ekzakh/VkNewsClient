package com.ekzakh.vknewsclient.data.mapper

interface Mapper<S : Any, R : Any> {
    fun map(source: S): R
}
