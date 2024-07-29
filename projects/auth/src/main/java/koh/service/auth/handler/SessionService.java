package koh.service.auth.handler;

import koh.db.hub.DSLContextFactory;
import koh.db.hub.vps_management.tables.daos.SessionDao;
import koh.db.hub.vps_management.tables.pojos.Session;
import koh.db.hub.vps_management.tables.pojos.User;
import koh.db.hub.vps_management.tables.records.SessionRecord;
import koh.service.auth.tools.Tools;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.time.LocalDateTime;

import static koh.db.hub.vps_management.tables.Session.SESSION;

@Slf4j
public class SessionService {
    final KeyGenerator keyGenerator;
    final SessionDao sessionDao;

    public SessionService() {
        this.keyGenerator = Tools.AES128KeyGenerator();
        this.sessionDao = DSLContextFactory.useDao(SessionDao.class);
    }

    public Object openSession(String deviceId, User user) {
        keyGenerator.init(128);
        SecretKey sessionKey = keyGenerator.generateKey();
        String sessionStringKey = Tools.base64Encoder().encodeToString(sessionKey.getEncoded());

        SessionRecord session = sessionDao
                .getTable()
                .newRecord()
                .setUserId(user.getId())
                .setDeviceId(deviceId)
                .setSessionId(sessionStringKey)
                .setExpireTime(LocalDateTime.now().plusDays(7));
        session.insert();
        return session;
    }

    public Object validateSession(String deviceId, User user) {
        try {
            return DSLContextFactory
                    .useContext()
                    .select()
                    .from(SESSION)
                    .where(SESSION.DEVICE_ID.eq(deviceId).and(SESSION.USER_ID.eq(user.getId())))
                    .fetchOptional(0, Session.class)
                    .orElseThrow();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Object closeSession() {

        return null;
    }
}
