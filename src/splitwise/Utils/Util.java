package splitwise.Utils;

import java.util.UUID;

public class Util {

    public static String uuidGenerator() {
        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();
        return uuidAsString;
    }

}
