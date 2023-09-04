package com.ekzakh.vknewsclient.data

import android.content.Context
import com.vk.api.sdk.VKPreferencesKeyValueStorage
import com.vk.api.sdk.auth.VKAccessToken

class VkTokenStorage(private val context: Context) : TokenStorage.Read<VKAccessToken?> {
    override fun read(): VKAccessToken? {
        val vkStorage = VKPreferencesKeyValueStorage(context)
        return VKAccessToken.restore(vkStorage)
    }
}
