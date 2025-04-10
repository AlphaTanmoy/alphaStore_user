package com.alphaStore.user.utils

object KeywordsAndConstants {

    const val NO_AUTH_APIS = "merchant/login/email"

    const val TOKEN_PREFIX = "Alpha "
    const val HEADER_AUTHORIZATION = "Alpha"
    const val HEADER_API_KEY = "apiKey"
    const val HEADER_TRACKING_ID = "trackingId"
    const val TOKEN_TIRE_ONE = "alpha-store-auth"
    const val HEADER_OTP = "otp"

    const val SERVICE_NOT_AVAILABLE = 503001
    const val SERVICE_UNDER_MAINTENANCE = 503002

    const val SERVICE_NOT_AVAILABLE_DESCRIPTION = "Service not available now. Please try again later."

    const val ENCRYPTION_PASSWORD_CHOICE_ONE = "/h9lW;L-~n>l8j\$!IQJHx1Yx{0Ot7:j%W;5b[^8]q}\"G]9jRJgc#P;pDakVkB}G62twX+M6CpscBD;a="
    const val ENCRYPTION_PASSWORD_CHOICE_TWO = "teA5o?MxI<Mcp+\"RZ|UYK{[>{geCYQdo)bUN57U2p|Ea,lwg4dTK+RBcvird5DdmfakeB[Rdn*wIIhmN"
    const val ENCRYPTION_PASSWORD_CHOICE_THREE = "/aSM6'A8J,zz9~0)sX*d3pXoo].u<!k+ZThF\\}cRvR7i-\"`X(&'F%4#4c{X8s^/'4Ivh/qKgq;0Q6lz8"
    const val ENCRYPTION_PASSWORD_SALT_CHOICE = "18237012"

    const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
    const val PASSWORD_CONFIG_BCRYPT_ROUND = 12
    const val ALLOW_REPEAT_OF_OLD_PASSWORD = false
    const val FRONT_END_URL = "http://localhost:4200/"

    const val SMTP_HOST = "smtppro.zoho.in"
    const val SMTP_PORT = 465
    const val SMTP_USERNAME_SUPPORT = "tanmoy.das@stableonesoftware.com"
    const val SMTP_PASSWORD_SUPPORT = "ddd"
    const val SMTP_USERNAME_SYSTEM = "tanmoy.das@stableonesoftware.com"
    const val SMTP_PASSWORD_SYSTEM = "ddd"
    const val SMTP_USERNAME_NOTIFICATIONS = "tanmoy.das@stableonesoftware.com"
    const val SMTP_PASSWORD_NOTIFICATIONS = "ddd"
    const val SMTP_FROM_EMAIL_SUPPORT = "tanmoy.das@stableonesoftware.com"
    const val SMTP_FROM_EMAIL_NOTIFICATIONS = "tanmoy.das@stableonesoftware.com"
    const val SMTP_FROM_EMAIL_SYSTEM = "tanmoy.das@stableonesoftware.com"

    const val NUMBER_OF_REPEATED_OTP_SEND_ATTEMPTS_BEFORE_HALTING = 3
    const val DELAY_IN_EACH_OTP_SENT_MINUTES = 1
    const val MINUTES_TO_HALT_ON_RELATED_SEND_OTP_THRESHOLD_CROSSED = 1
    const val OTP_DIGITS = 6
    const val CAN_SEND_OTP_THROUGH_EMAIL = true
    const val NUMBER_OF_TIMES_OTP_VERIFICATION_ALLOWED_PER_OTP = 2

    const val JWT_TIMEOUT_MINUTES_NORMAL = 50
    const val REFRESH_TIMEOUT_MINUTES_LONG = 300
    const val TOKEN_TIRE = "alphaStore"
    const val REFRESH_TOKEN_SUB = "alphaStore-refresh"

    const val GENERIC_JWT_CHOICE_ONE = "ZPXRzAVyv7TlNqcILxi6lclqRU70HdGvwEoKSoTEjK8nEBjdWSwlZO9kigODUcFF0X5R46EghR8eDs4E"
    const val GENERIC_JWT_CHOICE_TWO = "jjs82ECubIXT3mNZwsCV1Safh1OTUAsy395ksApTkGeOxwNiKhGbk0gBIxvl4rP6Mjo0ZDHqdq6kV0w2"
    const val GENERIC_JWT_CHOICE_THREE = "ALfbJ5066FtVE6vz0lUKqe49hx75Qur3z645L9tZpBeOxhq2u0IfiNaQjakRENeVyra69Fm4ftfba4HX"

    const val TOKEN_EXPIRED_DESCRIPTION = "token is expired"
    const val TOKEN_NOT_VALID_DESCRIPTION = "token is not valid"
    const val TOKEN_BLOCKED_DESCRIPTION = "token is blocked"
    const val TOKEN_REFRESHED_DESCRIPTION = "token is already refreshed"
    const val TOKEN_OR_API_KEY_REQUIRED_DESCRIPTION = "PLease provide either token or api key"

    const val TOKEN_EXPIRED = 401001
    const val TOKEN_NOT_VALID = 401002
    const val TOKEN_BLOCKED = 401003
    const val TOKEN_REFRESHED = 401004
    const val TOKEN_OR_API_KEY_REQUIRED = 401007

    const val PASSWORD_MIN_LENGTH = 6
    const val PASSWORD_MUST_HAVE_CAPITAL_LETTER = true
    const val PASSWORD_MUST_HAVE_SMALL_LETTER = true
    const val PASSWORD_MUST_HAVE_NUMBER = true
    const val PASSWORD_MUST_HAVE_SPECIAL_CHAR = true

    const val MICRO_SERVICE_USER_NAME = "alphaUsername"
    const val MICRO_SERVICE_PASSWORD = "alphaPassword"

    const val APIS_ALLOWED_WITH_BOTH_KEY_AND_TOKEN = ""
    const val TWO_FACTOR_AUTH_REQUIRED_APIS = ""

    const val REDIS_PASSWORD = "password"
    const val REDIS_HOST = "127.0.0.1"
    const val REDIS_PORT = 6379

    const val REDIS_KEY_BLACKLISTED_IPS = "ALPHA_STORE_REDIS_KEY_BLACKLISTED_IPS"
    const val REDIS_KEY_WHITELISTED_IPS = "ALPHA_STORE_REDIS_KEY_WHITELISTED_IPS"
    const val REDIS_KEY_JWT_BLACK_LIST = "ALPHA_STORE_REDIS_KEY_JWT_BLACK_LIST"
    const val REDIS_KEY_ACCESS_ROLE = "ALPHA_STORE_REDIS_KEY_ACCESS_ROLE"
    const val REDIS_KEY_USER_SERIAL = "ALPHA_STORE_REDIS_KEY_USER_SERIAL"
    const val REDIS_KEY_ACCESS_CONTROL = "ALPHA_STORE_REDIS_KEY_ACCESS_CONTROL"
    const val REDIS_KEY_DATABASE_RESULT = "ALPHA_STORE_REDIS_KEY_DATABASE_RESULT"
    const val REDIS_KEY_RESULT = "ALPHA_STORE_REDIS_KEY_RESULT"
    const val REDIS_KEY_DATABASE_TABLE_LOCK = "ALPHA_STORE_REDIS_KEY_DATABASE_TABLE_LOCK"

    //for request sanitation
    const val RABBIT_MQ_QUEUE_FOR_REQUEST_SANITATION = "alphaStoreRabbitMqRequestSanitationQueue"
    const val RABBIT_MQ_ROUTE_KEY_FOR_REQUEST_SANITATION = "alphaStoreRabbitMqRequestSanitationQueueKey"

    //user logged in from a new country
    const val RABBIT_MQ_QUEUE_FOR_NEW_DEVICE_LOGIN = "LivenessRabbitNewDeviceLoginQueue"
    const val RABBIT_MQ_ROUTE_KEY_FOR_NEW_DEVICE_LOGIN =
        "alphaStoreRabbitMqNewDeviceLoginQueueKey"

    //for sending emails to system admin
    const val RABBIT_MQ_QUEUE_FOR_SENDING_EMAIL_TO_SYSTEM_ADMIN =
        "alphaStoreRabbitMqSendingEmailsToSuperAdmin"
    const val RABBIT_MQ_ROUTE_KEY_FOR_SENDING_EMAIL_TO_SYSTEM_ADMIN =
        "alphaStoreRabbitMqSendingEmailsToSuperAdminKey"


    //send otp for verification
    const val RABBIT_MQ_QUEUE_FOR_SEND_OTP_TO_EMAIL_FOR_VERIFICATION =
        "alphaStoreRabbitMqSendOtpToEmailForVerification"
    const val RABBIT_MQ_ROUTE_KEY_FOR_SEND_OTP_TO_EMAIL_FOR_VERIFICATION =
        "alphaStoreRabbitMqSendOtpToEmailForVerificationKey"

    //user password reset due to multiple login fails
    const val RABBIT_MQ_QUEUE_FOR_PASSWORD_RESET_DUE_TO_MULTIPLE_LOGIN_FAILS =
        "alphaStoreRabbitMqPasswordResetDueToMultipleLoginFails"
    const val RABBIT_MQ_ROUTE_KEY_FOR_PASSWORD_RESET_DUE_TO_MULTIPLE_LOGIN_FAILS =
        "alphaStoreRabbitMqPasswordResetDueToMultipleLoginFailsKey"

    //for change password
    const val RABBIT_MQ_QUEUE_FOR_CHANGE_PASSWORD_EVENT = "alphaStoreRabbitMqChangePasswordEvent"
    const val RABBIT_MQ_ROUTE_KEY_FOR_CHANGE_PASSWORD_EVENT = "alphaStoreRabbitMqChangePasswordEventKey"

    //for forgot password
    const val RABBIT_MQ_QUEUE_FOR_FORGOT_PASSWORD_EVENT = "alphaStoreRabbitMqForgotPasswordEvent"
    const val RABBIT_MQ_ROUTE_KEY_FOR_FORGOT_PASSWORD_EVENT = "alphaStoreRabbitMqForgotPasswordEventKey"

    //New sub user created
    const val RABBIT_MQ_QUEUE_FOR_NEW_SUB_USER_CREATED =
        "alphaStoreRabbitMqNewSubUserCreated"
    const val RABBIT_MQ_ROUTE_KEY_FOR_NEW_SUB_USER_CREATED =
        "alphaStoreRabbitMqNewSubUserCreatedKey"

    //New user created
    const val RABBIT_MQ_QUEUE_FOR_NEW_USER_CREATED =
        "alphaStoreRabbitMqNewUserCreated"
    const val RABBIT_MQ_ROUTE_KEY_FOR_NEW_USER_CREATED =
        "alphaStoreRabbitMqNewUserCreatedKey"

    // For profile status updated
    const val RABBIT_MQ_QUEUE_FOR_USER_PROFILE_STATUS_UPDATED = "alphaStoreRabbitMqUserProfileStatusUpdatedQueue"
    const val RABBIT_MQ_ROUTE_KEY_FOR_USER_PROFILE_STATUS_UPDATED = "alphaStoreRabbitMqUserProfileStatusUpdatedKey"
}