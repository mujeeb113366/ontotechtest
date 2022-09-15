package com.mujeeb.techtestapp.utils

import com.mujeeb.techtestapp.domain.model.Response


object TestUtils {
    fun generateResponse(): Response {
        return Response(
            "test-user-name",
            "test@gmail.com",
            null,
            arrayListOf(),
            arrayListOf()
        )
    }

}

