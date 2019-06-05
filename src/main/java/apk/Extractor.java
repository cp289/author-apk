/**
 * Extract.java : Extract/decrypt apk file using apktool
 */

package apk;

import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.ProcessBuilder;

import java.io.IOException;
import java.lang.InterruptedException;


public class Extractor
{
  private String filename;

  public Extractor(String filename)
  {
    this.filename = filename;
  }

  // Run apktool to extract apk file
  public void run()
  {
    try
    {
      System.out.println("Extracting " + filename + " ...");
      ProcessBuilder builder = new ProcessBuilder("apktool", "d", "-s", filename);
      builder.redirectErrorStream(true);
      Process p = builder.start();
      BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));

      String line;
      while ( (line = input.readLine()) != null) {
        System.out.println(line);
      }

      int exitStatus = p.waitFor();

      if (exitStatus == 0) {
        System.out.println("Done extracting.");
      } else {
        System.out.println("Error occurred while extracting.");
        System.exit(1);
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }
  }

  public static void main(String[] args)
  {
    String filename = "";
    if (args.length >= 1) {
      filename = args[0];
    }
    System.out.println("Test: " + filename);

    Extractor ext = new Extractor(filename);
    ext.run();
  }
}

