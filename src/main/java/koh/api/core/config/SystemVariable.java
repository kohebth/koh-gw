package koh.api.core.config;

final class SystemVariable extends GlobalConfig {
    SystemVariable() {
        System.getenv("SERVICE_PORT");
    }

//    static synchronized SystemVariable getInstance() {
//
//    }
}
