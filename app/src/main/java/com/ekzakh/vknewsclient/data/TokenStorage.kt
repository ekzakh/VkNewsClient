package com.ekzakh.vknewsclient.data

interface TokenStorage<T> {

    interface Save<T> : TokenStorage<T> {
        fun <T> save(token: T)
    }

    interface Read<T> : TokenStorage<T> {
        fun read(): T
    }

    interface Mutate<T> : Save<T>, Read<T>
}
