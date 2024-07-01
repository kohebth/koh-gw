package koh.api.core.base;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

@Slf4j
public abstract class Configuration implements Configurable<Configuration>, Lazy<Configuration> {
    protected Logger getLog() {
        return log;
    }
}
