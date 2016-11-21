package com.github.wrdlbrnft.simplerest.util;

import android.support.annotation.IntDef;

/**
 * Created with Android Studio<br>
 * User: Xaver<br>
 * Date: 20/11/2016
 */
@IntDef({
        StatusCodes.ServerError.INTERNAL_SERVER_ERROR, StatusCodes.ServerError.NOT_IMPLEMENTED, StatusCodes.ServerError.BAD_GATEWAY,
        StatusCodes.ServerError.SERVICE_UNAVAILABLE, StatusCodes.ServerError.GATEWAY_TIMEOUT, StatusCodes.ServerError.HTTP_VERSION_NOT_SUPPORTED,
        StatusCodes.ServerError.VARIANT_ALSO_NEGOTIATES, StatusCodes.ServerError.INSUFFICIENT_STORAGE, StatusCodes.ServerError.LOOP_DETECTED,
        StatusCodes.ServerError.BANDWIDTH_LIMIT_EXCEEDED, StatusCodes.ServerError.NOT_EXTENDED, StatusCodes.ServerError.NETWORK_AUTHENTICATION_REQUIRED,
        StatusCodes.ServerError.UNKNOWN_ERROR, StatusCodes.ServerError.ORIGIN_CONNECTION_TIME_OUT, StatusCodes.ServerError.NETWORK_READ_TIMEOUT_ERROR,
        StatusCodes.ServerError.NETWORK_CONNECT_TIMEOUT_ERROR, StatusCodes.ClientError.BAD_REQUEST, StatusCodes.ClientError.UNAUTHORIZED,
        StatusCodes.ClientError.PAYMENT_REQUIRED, StatusCodes.ClientError.FORBIDDEN, StatusCodes.ClientError.NOT_FOUND, StatusCodes.ClientError.METHOD_NOT_ALLOWED,
        StatusCodes.ClientError.NOT_ACCEPTABLE, StatusCodes.ClientError.PROXY_AUTHENTICATION_REQUIRED, StatusCodes.ClientError.REQUEST_TIMEOUT,
        StatusCodes.ClientError.CONFLICT, StatusCodes.ClientError.GONE, StatusCodes.ClientError.LENGTH_REQUIRED, StatusCodes.ClientError.PRECONDITION_FAILED,
        StatusCodes.ClientError.PAYLOAD_TOO_LARGE, StatusCodes.ClientError.URI_TOO_LONG, StatusCodes.ClientError.UNSUPPORTED_MEDIA_TYPE,
        StatusCodes.ClientError.RANGE_NOT_SATISFIABLE, StatusCodes.ClientError.EXPECTATION_FAILED, StatusCodes.ClientError.I_AM_A_TEAPOT,
        StatusCodes.ClientError.AUTHENTICATION_TIMEOUT, StatusCodes.ClientError.ENHANCE_YOUR_CALM,
        StatusCodes.ClientError.MISDIRECTED_REQUEST, StatusCodes.ClientError.UNPROCESSABLE_ENTITY, StatusCodes.ClientError.LOCKED,
        StatusCodes.ClientError.FAILED_DEPENDENCY, StatusCodes.ClientError.UPGRADE_REQUIRED, StatusCodes.ClientError.PRECONDITION_REQUIRED,
        StatusCodes.ClientError.TOO_MANY_REQUESTS, StatusCodes.ClientError.REQUEST_HEADER_FIELDS_TOO_LARGE, StatusCodes.ClientError.LOGIN_TIMEOUT,
        StatusCodes.ClientError.NO_RESPONSE, StatusCodes.ClientError.RETRY_WITH, StatusCodes.ClientError.BLOCKED_BY_WINDOWS_PARENTAL_CONTROLS,
        StatusCodes.ClientError.UNAVAILABLE_FOR_LEGAL_REASONS, StatusCodes.ClientError.REQUEST_HEADER_TOO_LARGE,
        StatusCodes.ClientError.CERT_ERROR, StatusCodes.ClientError.NO_CERT, StatusCodes.ClientError.HTTP_TO_HTTPS, StatusCodes.ClientError.TOKEN_EXPIRED_OR_INVALID,
        StatusCodes.ClientError.CLIENT_CLOSED_REQUEST, StatusCodes.Redirection.MULTIPLE_CHOICES,
        StatusCodes.Redirection.MOVED_PERMANENTLY, StatusCodes.Redirection.FOUND, StatusCodes.Redirection.SEE_OTHER, StatusCodes.Redirection.NOT_MODIFIED,
        StatusCodes.Redirection.USE_PROXY, StatusCodes.Redirection.SWITCH_PROXY, StatusCodes.Redirection.TEMPORARY_REDIRECT,
        StatusCodes.Redirection.PERMANENT_REDIRECT, StatusCodes.Success.OK, StatusCodes.Success.CREATED, StatusCodes.Success.ACCEPTED,
        StatusCodes.Success.NON_AUTHORITATIVE_INFORMATION, StatusCodes.Success.NO_CONTENT, StatusCodes.Success.RESET_CONTENT, StatusCodes.Success.PARTIAL_CONTENT,
        StatusCodes.Success.MULTI_STATUS, StatusCodes.Success.ALREADY_REPORTED, StatusCodes.Success.IM_USED, StatusCodes.Informational.CONTINUE,
        StatusCodes.Informational.SWITCHING_PROTOCOLS, StatusCodes.Informational.PROCESSING, StatusCodes.INVALID
})
public @interface StatusCode {
}
