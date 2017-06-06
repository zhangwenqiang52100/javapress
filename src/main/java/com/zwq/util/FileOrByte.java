package com.zwq.util;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

/**
 * Created by Lancer on 2017/5/9.
 */
public class FileOrByte {

  public static byte[] File2byte(String filename) throws IOException {
    FileChannel fc = null;
    try {
      fc = new RandomAccessFile(filename, "r").getChannel();
      MappedByteBuffer byteBuffer = fc.map(MapMode.READ_ONLY, 0,
        fc.size()).load();
      System.out.println(byteBuffer.isLoaded());
      byte[] result = new byte[(int) fc.size()];
      if (byteBuffer.remaining() > 0) {
        // System.out.println("remain");
        byteBuffer.get(result, 0, byteBuffer.remaining());
      }
      return result;
    } catch (IOException e) {
      e.printStackTrace();
      throw e;
    } finally {
      try {
        fc.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public static void byte2File(byte[] buf, String filePath, String fileName) {
    BufferedOutputStream bos = null;
    FileOutputStream fos = null;
    File file = null;
    try {
      File dir = new File(filePath);
      if (!dir.exists() && dir.isDirectory()) {
        dir.mkdirs();
      }
      file = new File(filePath + File.separator + fileName);
      fos = new FileOutputStream(file);
      bos = new BufferedOutputStream(fos);
      bos.write(buf);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (bos != null) {
        try {
          bos.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      if (fos != null) {
        try {
          fos.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
