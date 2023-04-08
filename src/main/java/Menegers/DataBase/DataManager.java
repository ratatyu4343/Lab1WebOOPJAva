package Menegers.DataBase;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class DataManager {

    public static final String DBPath = "jdbc:postgresql://localhost:5432/TestsLab1";
    public static final String user = "postgres";
    public static final String password = "1234";
    public static final Logger logger = LogManager.getLogger(DataManager.class.getName());
    private static final String SECRET = "mysecret";
    private static final String ISSUER = "myapp";
    public static final String SECRET_KEY = "mySecretKey";
    public static final long EXPIRATION_TIME = 3600000; //1 hour

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(DBPath, user, password);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return connection;
    }

    public static String createJwt(String subject, String role, long expiresInMs) {
        java.util.Date now = new java.util.Date();
        java.util.Date expiresAt = new java.util.Date(now.getTime() + expiresInMs);
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        String jwt = JWT.create()
                .withSubject(subject)
                .withIssuer(ISSUER)
                .withClaim("role", role)
                .withIssuedAt(now)
                .withExpiresAt(expiresAt)
                .sign(algorithm);
        return jwt;
    }
    public static DecodedJWT verifyJwt(String jwt) {
        String jwtCopy = jwt.startsWith("Bearer ") ? jwt.substring(7) : jwt;
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).build();
            return verifier.verify(jwtCopy);
        } catch (Exception e){
            return null;
        }
    }
}
