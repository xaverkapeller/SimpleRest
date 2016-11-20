package com.github.wrdlbrnft.simplerest.util;

/**
 * Created by kapeller on 19/11/15.
 */
public class StatusCodes {

    public static final int INVALID = -1;

    /**
     * <h1>Codes 100 - 199</h1>
     * <b>Request received, continuing process.</b>
     * <p>
     * This class of status code indicates a provisional response, consisting only of the Status-Line and optional headers,
     * and is terminated by an empty line. Since HTTP/1.0 did not define any 1xx status codes, servers must not send a
     * 1xx response to an HTTP/1.0 client except under experimental conditions.
     * </p>
     */
    public static class Informational {

        /**
         * <h1>100 Continue</h1>
         * <p>
         * This means that the server has received the request headers, and that the client
         * should proceed to send the request body (in the case of a request for which a body
         * needs to be sent; for example, a POST request). If the request body is large,
         * sending it to a server when a request has already been rejected based upon
         * inappropriate headers is inefficient. To have a server check if the request could
         * be accepted based on the requests headers alone, a client must send
         * <code>Expect: 100-continue</code> as a header in its initial request and check if a
         * <code>100 Continue</code> status code is received in response before continuing
         * = or receive <code>417 Expectation Failed</code> and not continue)
         * </p>
         */
        public static final int CONTINUE = 100;

        /**
         * <h1>101 Switching Protocols</h1>
         * <p>
         * This means the requester has asked the server to switch protocols and
         * the server is acknowledging that it will do so.
         * </p>
         */
        public static final int SWITCHING_PROTOCOLS = 101;

        /**
         * <h1>102 Processing (WebDAV; RFC 2518)</h1>
         * <p>
         * As a WebDAV request may contain many sub-requests involving file operations,
         * it may take a long time to complete the request. This code indicates that
         * the server has received and is processing the request, but no response is
         * available yet. This prevents the client from timing out and assuming
         * the request was lost.
         * </p>
         */
        public static final int PROCESSING = 102;
    }

    /**
     * <h1>Codes 200 - 299</h1>
     * <p>
     * This class of status codes indicates the action requested by the client was
     * received, understood, accepted and processed successfully.
     * </p>
     */
    public static class Success {

        /**
         * <h1>200 OK</h1>
         * <p>
         * Standard response for successful HTTP requests. The actual response will depend
         * on the request method used. In a GET request, the response will contain an
         * entity corresponding to the requested resource. In a POST request, the response
         * will contain an entity describing or containing the result of the action.
         * </p>
         */
        public static final int OK = 200;

        /**
         * <h1>201 Created</h1>
         * <p>
         * The request has been fulfilled and resulted in a new resource being created.
         * </p>
         */
        public static final int CREATED = 201;

        /**
         * <h1>202 Accepted</h1>
         * <p>
         * The request has been accepted for processing, but the processing has not been
         * completed. The request might or might not eventually be acted upon, as it might
         * be disallowed when processing actually takes place.
         * </p>
         */
        public static final int ACCEPTED = 202;

        /**
         * <h1>203 Non-Authoritative Information (since HTTP/1.1)</h1>
         * <p>
         * The server successfully processed the request, but is returning
         * information that may be from another source.
         * </p>
         */
        public static final int NON_AUTHORITATIVE_INFORMATION = 203;

        /**
         * <h1>204 No Content</h1>
         * <p>
         * The server successfully processed the request, but is not returning any content.
         * </p>
         */
        public static final int NO_CONTENT = 204;

        /**
         * <h1>205 Reset Content</h1>
         * <p>
         * The server successfully processed the request, but is not returning any content.
         * Unlike a 204 response, this response requires that the requester reset
         * the document view.
         * </p>
         */
        public static final int RESET_CONTENT = 205;

        /**
         * <h1>206 Partial Content (RFC 7233)</h1>
         * <p>
         * The server is delivering only part of the resource (byte serving) due to
         * a range header sent by the client. The range header is used by HTTP clients
         * to enable resuming of interrupted downloads, or split a download into
         * multiple simultaneous streams.
         * </p>
         */
        public static final int PARTIAL_CONTENT = 206;

        /**
         * <h1>207 Multi-Status (WebDAV; RFC 4918)</h1>
         * <p>
         * The message body that follows is an XML message and can contain a number of separate
         * response codes, depending on how many sub-requests were made.
         * </p>
         */
        public static final int MULTI_STATUS = 207;

        /**
         * <h1>208 Already Reported (WebDAV; RFC 5842)</h1>
         * <p>
         * The members of a DAV binding have already been enumerated in a previous reply
         * to this request, and are not being included again.
         * </p>
         */
        public static final int ALREADY_REPORTED = 208;

        /**
         * <h1>226 IM Used (RFC 3229)</h1>
         * <p>
         * The server has fulfilled a request for the resource, and the response is a
         * representation of the result of one or more instance-manipulations applied
         * to the current instance.
         * </p>
         */
        public static final int IM_USED = 226;
    }

    /**
     * <h1>Codes 300 - 399</h1>
     * <p>
     * This class of status code indicates the client must take additional action to complete the request.
     * Many of these status codes are used in URL redirection.
     * </p>
     * <p>
     * A user agent may carry out the additional action with no user interaction only if
     * the method used in the second request is GET or HEAD. A user agent should not
     * automatically redirect a request more than five times, since such redirections usually
     * indicate an infinite loop.
     * </p>
     */
    public static class Redirection {

        /**
         * <h1>300 Multiple Choices</h1>
         * <p>
         * Indicates multiple options for the resource that the client may follow.
         * It, for instance, could be used to present different format options for video,
         * list files with different extensions, or word sense disambiguation.
         * </p>
         */
        public static final int MULTIPLE_CHOICES = 300;

        /**
         * <h1>301 Moved Permanently</h1>
         * <p>
         * This and all future requests should be directed to the given URI.
         * </p>
         */
        public static final int MOVED_PERMANENTLY = 301;

        /**
         * <h1>302 Found</h1>
         * <p>
         * This is an example of industry practice contradicting the standard.
         * The HTTP/1.0 specification (RFC 1945) required the client to perform a
         * temporary redirect (the original describing phrase was "Moved Temporarily";
         * but popular browsers implemented 302 with the functionality of a 303 See Other.
         * Therefore, HTTP/1.1 added status codes 303 and 307 to distinguish between
         * the two behaviours. However, some Web applications and frameworks use the
         * 302 status code as if it were the 303.
         * </p>
         */
        public static final int FOUND = 302;

        /**
         * <h1>303 See Other (since HTTP/1.1)</h1>
         * <p>
         * The response to the request can be found under another URI using a
         * GET method. When received in response to a POST (or PUT/DELETE;
         * it should be assumed that the server has received the data and the
         * redirect should be issued with a separate GET message.
         * </p>
         */
        public static final int SEE_OTHER = 303;

        /**
         * <h1>304 Not Modified (RFC 7232)</h1>
         * <p>
         * Indicates that the resource has not been modified since the
         * version specified by the request headers If-Modified-Since or
         * If-None-Match. This means that there is no need to retransmit
         * the resource, since the client still has a previously-downloaded copy.
         * </p>
         */
        public static final int NOT_MODIFIED = 304;

        /**
         * <h1>305 Use Proxy (since HTTP/1.1)</h1>
         * <p>
         * The requested resource is only available through a proxy, whose
         * address is provided in the response. Many HTTP clients (such as Mozilla
         * and Internet Explorer) do not correctly handle responses with this status
         * code, primarily for security reasons.
         * </p>
         */
        public static final int USE_PROXY = 305;

        /**
         * <h1>306 Switch Proxy</h1>
         * <p>
         * No longer used. Originally meant "Subsequent requests should use the specified proxy."
         * </p>
         */
        public static final int SWITCH_PROXY = 306;

        /**
         * <h1>307 Temporary Redirect (since HTTP/1.1)</h1>
         * <p>
         * In this case, the request should be repeated with another URI; however,
         * future requests should still use the original URI. In contrast to how 302 was
         * historically implemented, the request method is not allowed to be changed when
         * reissuing the original request. For instance, a POST request should be repeated
         * using another POST request.
         * </p>
         */
        public static final int TEMPORARY_REDIRECT = 307;

        /**
         * <h1>308 Permanent Redirect (RFC 7538)</h1>
         * <p>
         * The request, and all future requests should be repeated using another URI.
         * 307 and 308 (as proposed) parallel the behaviours of 302 and 301, but do
         * not allow the HTTP method to change. So, for example, submitting a form to
         * a permanently redirected resource may continue smoothly.
         * <br><br>
         * <b>Shares its status code with RESUME_INCOMPLETE</b>
         * </p>
         */
        public static final int PERMANENT_REDIRECT = 308;
    }

    /**
     * <h1>Codes 400 - 499</h1>
     * <p>
     * The 4xx class of status code is intended for cases in which the client seems to have erred.
     * Except when responding to a HEAD request, the server should include an entity containing
     * an explanation of the error situation, and whether it is a temporary or permanent condition.
     * These status codes are applicable to any request method.
     * User agents should display any included entity to the user.
     * </p>
     */
    public static class ClientError {

        /**
         * <h1>400 Bad Request</h1>
         * <p>
         * The server cannot or will not process the request due to something that is
         * perceived to be a client error (e.g., malformed request syntax,
         * invalid request message framing, or deceptive request routing)
         * </p>
         */
        public static final int BAD_REQUEST = 400;

        /**
         * <h1>401 Unauthorized (RFC 7235)</h1>
         * <p>
         * Similar to 403 Forbidden, but specifically for use when authentication is
         * required and has failed or has not yet been provided. The response must
         * include a WWW-Authenticate header field containing a challenge applicable
         * to the requested resource. See Basic access authentication and
         * Digest access authentication.
         * </p>
         */
        public static final int UNAUTHORIZED = 401;

        /**
         * <h1>402 Payment Required</h1>
         * <p>
         * Reserved for future use. The original intention was that this code might be used
         * as part of some form of digital cash or micropayment scheme, but that has not
         * happened, and this code is not usually used. Google Developers API uses this status
         * if a particular developer has exceeded the daily limit on requests.
         * </p>
         */
        public static final int PAYMENT_REQUIRED = 402;

        /**
         * <h1>403 Forbidden</h1>
         * <p>
         * The request was a valid request, but the server is refusing to respond to it.
         * Unlike a 401 Unauthorized response, authenticating will make no difference.
         * </p>
         */
        public static final int FORBIDDEN = 403;

        /**
         * <h1>404 Not Found</h1>
         * <p>
         * The requested resource could not be found but may be available again in the future.
         * Subsequent requests by the client are permissible.
         * </p>
         */
        public static final int NOT_FOUND = 404;

        /**
         * <h1>405 Method Not Allowed</h1>
         * <p>
         * A request was made of a resource using a request method not supported by that
         * resource; for example, using GET on a form which requires data to be presented
         * via POST, or using PUT on a read-only resource.
         * </p>
         */
        public static final int METHOD_NOT_ALLOWED = 405;

        /**
         * <h1>406 Not Acceptable</h1>
         * <p>
         * The requested resource is only capable of generating content not acceptable
         * according to the Accept headers sent in the request.
         * </p>
         */
        public static final int NOT_ACCEPTABLE = 406;

        /**
         * <h1>407 Proxy Authentication Required (RFC 7235)</h1>
         * <p>
         * The client must first authenticate itself with the proxy.
         * </p>
         */
        public static final int PROXY_AUTHENTICATION_REQUIRED = 407;

        /**
         * <h1>408 Request Timeout</h1>
         * <p>
         * The server timed out waiting for the request. According to HTTP specifications:
         * "The client did not produce a request within the time that the server was prepared
         * to wait. The client MAY repeat the request without modifications at any later time."
         * </p>
         */
        public static final int REQUEST_TIMEOUT = 408;

        /**
         * <h1>409 Conflict</h1>
         * <p>
         * Indicates that the request could not be processed because of conflict in the
         * request, such as an edit conflict in the case of multiple updates.
         * </p>
         */
        public static final int CONFLICT = 409;

        /**
         * <h1>410 Gone</h1>
         * <p>
         * Indicates that the resource requested is no longer available and will not be
         * available again. This should be used when a resource has been intentionally
         * removed and the resource should be purged. Upon receiving a 410 status code,
         * the client should not request the resource again in the future. Clients such
         * as search engines should remove the resource from their indices. Most use cases
         * do not require clients and search engines to purge the resource,
         * and a "404 Not Found" may be used instead.
         * </p>
         */
        public static final int GONE = 410;

        /**
         * <h1>411 Length Required</h1>
         * <p>
         * The request did not specify the length of its content, which is required
         * by the requested resource.
         * </p>
         */
        public static final int LENGTH_REQUIRED = 411;

        /**
         * <h1>412 Precondition Failed (RFC 7232)</h1>
         * <p>
         * The server does not meet one of the preconditions that the requester put on the request.
         * </p>
         */
        public static final int PRECONDITION_FAILED = 412;

        /**
         * <h1>413 Payload Too Large (RFC 7231)</h1>
         * <p>
         * The request is larger than the server is willing or able to process.
         * Called "Request Entity Too Large" previously.
         * </p>
         */
        public static final int PAYLOAD_TOO_LARGE = 413;

        /**
         * <h1>414 URI Too Long (RFC 7231)</h1>
         * <p>
         * The URI provided was too long for the server to process. Often the result of too
         * much data being encoded as a query-string of a GET request, in which case it should
         * be converted to a POST request. Called "Request-URI Too Long" previously.
         * </p>
         */
        public static final int URI_TOO_LONG = 414;

        /**
         * <h1>415 Unsupported Media Type</h1>
         * <p>
         * The request entity has a media type which the server or resource does not support.
         * For example, the client uploads an image as image/svg+xml, but the server
         * requires that images use a different format.
         * </p>
         */
        public static final int UNSUPPORTED_MEDIA_TYPE = 415;

        /**
         * <h1>416 Range Not Satisfiable (RFC 7233)</h1>
         * <p>
         * The client has asked for a portion of the file (byte serving; but the server
         * cannot supply that portion. For example, if the client asked for a part of the
         * file that lies beyond the end of the file. Called "Requested Range Not Satisfiable"
         * previously.
         * </p>
         */
        public static final int RANGE_NOT_SATISFIABLE = 416;

        /**
         * <h1>417 Expectation Failed</h1>
         * <p>
         * The server cannot meet the requirements of the Expect request-header field.
         * </p>
         */
        public static final int EXPECTATION_FAILED = 417;

        /**
         * <h1>418 I'm a teapot (RFC 2324)</h1>
         * <p>
         * This code was defined in 1998 as one of the traditional IETF April Fools' jokes,
         * in RFC 2324, Hyper Text Coffee Pot Control Protocol, and is not expected to be
         * implemented by actual HTTP servers. The RFC specifies this code should be returned
         * by tea pots requested to brew coffee.
         * </p>
         */
        public static final int I_AM_A_TEAPOT = 418;

        /**
         * <h1>419 Authentication Timeout (not in RFC 2616)</h1>
         * <p>
         * Not a part of the HTTP standard, 419 Authentication Timeout denotes that previously
         * valid authentication has expired. It is used as an alternative to 401 Unauthorized
         * in order to differentiate from otherwise authenticated clients being denied access
         * to specific server resources.
         * </p>
         */
        public static final int AUTHENTICATION_TIMEOUT = 419;

        /**
         * <h1>420 Enhance Your Calm (Twitter)</h1>
         * <p>
         * Not part of the HTTP standard, but returned by version 1 of the Twitter Search and
         * Trends API when the client is being rate limited. Other services may wish to implement
         * the 429 Too Many Requests response code instead.
         * <br><br>
         * <b>Shares the same status code as METHOD_FAILURE</b>
         * </p>
         */
        public static final int ENHANCE_YOUR_CALM = 420;

        /**
         * <h1>421 Misdirected Request (RFC 7540)</h1>
         * <p>
         * The request was directed at a server that is not able to produce a response
         * = for example because a connection reuse).
         * </p>
         */
        public static final int MISDIRECTED_REQUEST = 421;

        /**
         * <h1>422 Unprocessable Entity (WebDAV; RFC 4918)</h1>
         * <p>
         * The request was well-formed but was unable to be followed due to semantic errors.
         * </p>
         */
        public static final int UNPROCESSABLE_ENTITY = 422;

        /**
         * <h1>423 Locked (WebDAV; RFC 4918)</h1>
         * <p>
         * The resource that is being accessed is locked.
         * </p>
         */
        public static final int LOCKED = 423;

        /**
         * <h1>424 Failed Dependency (WebDAV; RFC 4918)</h1>
         * <p>
         * The request failed due to failure of a previous request (e.g., a PROPPATCH).
         * </p>
         */
        public static final int FAILED_DEPENDENCY = 424;

        /**
         * <h1>426 Upgrade Required</h1>
         * <p>
         * The client should switch to a different protocol such as
         * TLS/1.0, given in the Upgrade header field.
         * </p>
         */
        public static final int UPGRADE_REQUIRED = 426;

        /**
         * <h1>428 Precondition Required (RFC 6585)</h1>
         * <p>
         * The origin server requires the request to be conditional. Intended to prevent
         * "the 'lost update' problem, where a client GETs a resource's state, modifies
         * it, and PUTs it back to the server, when meanwhile a third party has modified
         * the state on the server, leading to a conflict."
         * </p>
         */
        public static final int PRECONDITION_REQUIRED = 428;

        /**
         * <h1>429 Too Many Requests (RFC 6585)</h1>
         * <p>
         * The user has sent too many requests in a given amount of time.
         * Intended for use with rate limiting schemes.
         * </p>
         */
        public static final int TOO_MANY_REQUESTS = 429;

        /**
         * <h1>431 Request Header Fields Too Large (RFC 6585)</h1>
         * <p>
         * The server is unwilling to process the request because either an individual
         * header field, or all the header fields collectively, are too large.
         * </p>
         */
        public static final int REQUEST_HEADER_FIELDS_TOO_LARGE = 431;

        /**
         * <h1>440 Login Timeout (Microsoft)</h1>
         * <p>
         * A Microsoft extension. Indicates that your session has expired.
         * </p>
         */
        public static final int LOGIN_TIMEOUT = 440;

        /**
         * <h1>444 No Response (Nginx)</h1>
         * <p>
         * Used in Nginx logs to indicate that the server has returned no information to
         * the client and closed the connection (useful as a deterrent for malware).
         * </p>
         */
        public static final int NO_RESPONSE = 444;

        /**
         * <h1>449 Retry With (Microsoft)</h1>
         * <p>
         * A Microsoft extension. The request should be retried after performing
         * the appropriate action.
         * </p>
         */
        public static final int RETRY_WITH = 449;

        /**
         * <h1>450 Blocked by Windows Parental Controls (Microsoft)</h1>
         * <p>
         * A Microsoft extension. This error is given when Windows Parental Controls
         * are turned on and are blocking access to the given webpage.
         * </p>
         */
        public static final int BLOCKED_BY_WINDOWS_PARENTAL_CONTROLS = 450;

        /**
         * <h1>451 Unavailable For Legal Reasons (Internet draft)</h1>
         * <p>
         * Defined in the internet draft "A New HTTP Status Code for
         * Legally-restricted Resources". Intended to be used when resource access is
         * denied for legal reasons, e.g. censorship or government-mandated blocked access.
         * A reference to the 1953 dystopian novel Fahrenheit 451, where books are outlawed.
         * <br><br>
         * <b>Shares its status code with REDIRECT</b>
         * </p>
         */
        public static final int UNAVAILABLE_FOR_LEGAL_REASONS = 451;

        /**
         * <h1>494 Request Header Too Large (Nginx)</h1>
         * <p>
         * Nginx internal code similar to 431 but it was introduced earlier
         * in version 0.9.4 (on January 21, 2011).
         * </p>
         */
        public static final int REQUEST_HEADER_TOO_LARGE = 494;

        /**
         * <h1>495 Cert Error (Nginx)</h1>
         * <p>
         * Nginx internal code used when SSL client certificate error occurred to distinguish it from 4XX in a log and an error page redirection.
         * </p>
         */
        public static final int CERT_ERROR = 495;

        /**
         * <h1>496 No Cert (Nginx)</h1>
         * <p>
         * Nginx internal code used when client didn't provide certificate to
         * distinguish it from 4XX in a log and an error page redirection.
         * </p>
         */
        public static final int NO_CERT = 496;

        /**
         * <h1>497 HTTP to HTTPS (Nginx)</h1>
         * <p>
         * Nginx internal code used for the plain HTTP requests that are sent to HTTPS port
         * to distinguish it from 4XX in a log and an error page redirection.
         * </p>
         */
        public static final int HTTP_TO_HTTPS = 497;

        /**
         * <h1>498 Token expired/invalid (Esri)</h1>
         * <p>
         * Returned by ArcGIS for Server. A code of 498 indicates an expired or
         * otherwise invalid token.
         * </p>
         */
        public static final int TOKEN_EXPIRED_OR_INVALID = 498;

        /**
         * <h1>499 Client Closed Request (Nginx)</h1>
         * <p>
         * Used in Nginx logs to indicate when the connection has been closed by client while
         * the server is still processing its request, making server unable to send a
         * status code back.
         * <br><br>
         * <b>Shares its status code with TOKEN_REQUIRED</b>
         * </p>
         */
        public static final int CLIENT_CLOSED_REQUEST = 499;
    }

    /**
     * <h1>Codes 500 - 599</h1>
     * <b>The server failed to fulfill an apparently valid request.</b>
     * <p>
     * Response status codes beginning with the digit "5" indicate cases in which the server is
     * aware that it has encountered an error or is otherwise incapable of performing the request.
     * Except when responding to a HEAD request, the server should include an entity containing an
     * explanation of the error situation, and indicate whether it is a temporary or permanent condition.
     * Likewise, user agents should display any included entity to the user.
     * These response codes are applicable to any request method.
     * </p>
     */
    public static class ServerError {

        /**
         * <h1>500 Internal Server Error</h1>
         * <p>
         * A generic error message, given when an unexpected condition was encountered
         * and no more specific message is suitable.
         * </p>
         */
        public static final int INTERNAL_SERVER_ERROR = 500;

        /**
         * <h1>501 Not Implemented</h1>
         * <p>
         * The server either does not recognize the request method, or it lacks the ability to
         * fulfill the request. Usually this implies future availability
         * = e.g., a new feature of a web-service API).
         * </p>
         */
        public static final int NOT_IMPLEMENTED = 501;

        /**
         * <h1>502 Bad Gateway</h1>
         * <p>
         * The server was acting as a gateway or proxy and received an invalid response
         * from the upstream server.
         * </p>
         */
        public static final int BAD_GATEWAY = 502;

        /**
         * <h1>503 Service Unavailable</h1>
         * <p>
         * The server is currently unavailable (because it is overloaded or down for
         * maintenance). Generally, this is a temporary state.
         * </p>
         */
        public static final int SERVICE_UNAVAILABLE = 503;

        /**
         * <h1>504 Gateway Timeout</h1>
         * <p>
         * The server was acting as a gateway or proxy and did not receive a timely
         * response from the upstream server.
         * </p>
         */
        public static final int GATEWAY_TIMEOUT = 504;

        /**
         * <h1>505 HTTP Version Not Supported</h1>
         * <p>
         * The server does not support the HTTP protocol version used in the request.
         * </p>
         */
        public static final int HTTP_VERSION_NOT_SUPPORTED = 505;

        /**
         * <h1>506 Variant Also Negotiates (RFC 2295)</h1>
         * <p>
         * Transparent content negotiation for the request results in a circular reference.
         * </p>
         */
        public static final int VARIANT_ALSO_NEGOTIATES = 506;

        /**
         * <h1>507 Insufficient Storage (WebDAV; RFC 4918)</h1>
         * <p>
         * The server is unable to store the representation needed to complete the request.
         * </p>
         */
        public static final int INSUFFICIENT_STORAGE = 507;

        /**
         * <h1>508 Loop Detected (WebDAV; RFC 5842)</h1>
         * <p>
         * The server detected an infinite loop while processing the
         * request (sent in lieu of 208 Already Reported).
         * </p>
         */
        public static final int LOOP_DETECTED = 508;

        /**
         * <h1>509 Bandwidth Limit Exceeded (Apache bw/limited extension)</h1>
         * <p>
         * This status code is not specified in any RFCs. Its use is unknown.
         * </p>
         */
        public static final int BANDWIDTH_LIMIT_EXCEEDED = 509;

        /**
         * <h1>510 Not Extended (RFC 2774)</h1>
         * <p>
         * Further extensions to the request are required for the server to fulfil it.
         * </p>
         */
        public static final int NOT_EXTENDED = 510;

        /**
         * <h1>511 Network Authentication Required (RFC 6585)</h1>
         * <p>
         * The client needs to authenticate to gain network access. Intended for use by
         * intercepting proxies used to control access to the network (e.g., "captive portals"
         * used to require agreement to Terms of Service before granting full Internet access
         * via a Wi-Fi hotspot).
         * </p>
         */
        public static final int NETWORK_AUTHENTICATION_REQUIRED = 511;

        /**
         * <h1>520 Unknown Error</h1>
         * <p>
         * This status code is not specified in any RFC and is returned by certain services,
         * for instance Microsoft Azure and CloudFlare servers: "The 520 error is essentially
         * a “catch-all” response for when the origin server returns something unexpected or
         * something that is not tolerated/interpreted (protocol violation or empty response)."
         * </p>
         */
        public static final int UNKNOWN_ERROR = 520;

        /**
         * <h1>522 Origin Connection Time-out</h1>
         * <p>
         * This status code is not specified in any RFCs, but is used by CloudFlare's
         * reverse proxies to signal that a server connection timed out.
         * </p>
         */
        public static final int ORIGIN_CONNECTION_TIME_OUT = 522;

        /**
         * <h1>598 Network read timeout error (Unknown)</h1>
         * <p>
         * This status code is not specified in any RFCs, but is used by Microsoft
         * HTTP proxies to signal a network read timeout behind the proxy to a client
         * in front of the proxy.
         * </p>
         */
        public static final int NETWORK_READ_TIMEOUT_ERROR = 598;

        /**
         * <h1>599 Network connect timeout error (Unknown)</h1>
         * <p>
         * This status code is not specified in any RFCs, but is used by Microsoft
         * HTTP proxies to signal a network connect timeout behind the proxy to a client
         * in front of the proxy.
         * </p>
         */
        public static final int NETWORK_CONNECT_TIMEOUT_ERROR = 599;
    }
}
