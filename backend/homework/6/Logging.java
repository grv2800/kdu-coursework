package org.example.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
enum loglevel{
    INFO,
    DEBUG,
    WARN,
    ERROR
}
public class Logging {
    Logging(){}
    public static void Mylogger(String message,String level){
        Logger logger=LoggerFactory.getLogger(Logging.class);
        switch (loglevel.valueOf(String.valueOf(level).toUpperCase())){
            case INFO -> logger.info(message);
            case DEBUG -> logger.debug(message);
            case ERROR -> logger.error(message);
            case WARN -> logger.warn(message);
        }
    }
}
