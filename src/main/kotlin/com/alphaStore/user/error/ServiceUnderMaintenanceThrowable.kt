package com.alphaStore.user.error

class ServiceUnderMaintenanceThrowable(
    var errorMessage: String = "",
) : RuntimeException()