package bytetech.movierecmommendations.server.infrastructure.constants.module;

public class MappingConstant {

    public static final String USER = "/user";
    public static final String ADMIN = "/admin";
    public static final String CLIENT = "/client";

    public static final String EMBED = "/embed";

    public static final String API_VERSION_PREFIX = "/api/v1";
    public static final String API_COMMON = API_VERSION_PREFIX + "/common";
    public static final String API_CONNECTION = API_VERSION_PREFIX + "/connection";

    public static final String API_GUEST_GUARD_ACCOUNT = "/api/guest-guard/account";

    public static final String API_USER_PREFIX = API_VERSION_PREFIX + USER;
    public static final String API_ADMIN_PREFIX = API_VERSION_PREFIX + ADMIN;
    public static final String API_CLIENT_PREFIX = API_VERSION_PREFIX + CLIENT;

    public static final String API_EMBED_PREFIX = API_VERSION_PREFIX + EMBED;

    // admin
    public static final String API_ADMIN_ANOTHER = API_ADMIN_PREFIX + "/another";
    public static final String API_ADMIN_USER = API_ADMIN_PREFIX + "/user";
    public static final String API_ADMIN_REVIEW = API_ADMIN_PREFIX + "/review";
    public static final String API_ADMIN_CATEGORY = API_ADMIN_PREFIX + "/category";
    public static final String API_ADMIN_MOVIE = API_ADMIN_PREFIX + "/movie";

    // user
    public static final String API_USER_ANOTHER = API_USER_PREFIX + "/another";
    public static final String API_USER_ACCOUNT = API_USER_PREFIX + "/user";
    public static final String API_USER_REVIEW = API_USER_PREFIX + "/review";
    public static final String API_USER_MOVIE = API_USER_PREFIX + "/movie";
    // employee
    public static final String API_CLIENT_ANOTHER = API_CLIENT_PREFIX + "/another";
    public static final String API_CLIENT_REVIEW = API_CLIENT_PREFIX + "/review";

    // connection
    public static final String API_CONNECTION_PYTHON = API_CONNECTION + "/python";
    public static final String API_CONNECTION_RECOMMENDATION = API_CONNECTION + "/recommendation";

    public static final String API_COMMON_ANOTHER = API_COMMON + "/another";

    /* AUTHENTICATION */
    public static final String API_AUTH_PREFIX = API_VERSION_PREFIX + "/auth";

    /* SWAGGER */
    public static final String API_SWAGGER = API_VERSION_PREFIX + "/swagger-ui";

    public static final String PATH_OAUTH2 = "/oauth2";

}
