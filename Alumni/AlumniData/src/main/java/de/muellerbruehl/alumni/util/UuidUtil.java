/*
 * **************************************************************************
 * This software is created by Michael M??ller.
 * (c) all rights reserved.
 * 
 * This software is delivered as is, without any warranty.
 * It is free for personal, non-commercial usage.
 * For any other usage, please contact the author.
 * michael.mueller@mueller-bruehl.de
 * **************************************************************************
 */
package de.muellerbruehl.alumni.util;

import java.nio.ByteBuffer;
import java.util.UUID;

/**
 *
 * @author mmueller
 */
public class UuidUtil {

  public static byte[] toBytes(UUID uuid) {
    ByteBuffer buffer = ByteBuffer.wrap(new byte[16]);
    buffer.putLong(uuid.getMostSignificantBits());
    buffer.putLong(uuid.getLeastSignificantBits());
    return buffer.array();
  }

  public static UUID fromBytes(byte[] bytes) {
    ByteBuffer buffer = ByteBuffer.wrap(bytes);
    Long mostSignificantBits = buffer.getLong();
    Long leastSignificantBits = buffer.getLong();
    return new UUID(mostSignificantBits, leastSignificantBits);
  }

  public static byte[] makeUuidAsBytes(){
    return toBytes(UUID.randomUUID());
  }
  
  private static String getRandomAsString() {
    return UUID.randomUUID().toString().replace("-", "");
  }
    
}
