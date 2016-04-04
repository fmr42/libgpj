/**
 * Copyright (C) 2016  Federico M. Rossi <federico.rossi@gmx.com>
 */
package libgpj;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

/**
 * @author Federico M. Rossi <federico.rossi@gmx.com>
 * 
 */
public class Timer {

  /**
   * 
   */
  
  DateTime dt_start;
  long timeoutMillis;
  
  public Timer() {
    dt_start = DateTime.now(DateTimeZone.UTC);
    timeoutMillis = 0;
  }
  
  public void start (long millis){
    this.timeoutMillis = millis;
    dt_start = DateTime.now(DateTimeZone.UTC);
  }
  
  public boolean isElapsed () {
    DateTime now = DateTime.now(DateTimeZone.UTC);
    long elapsedMillis = now.getMillis() - dt_start.getMillis() ; 
    if ( elapsedMillis < timeoutMillis ) {
      return false;
    } else {
      return true;
    }
  }
  
  public void waitForTimeout() throws InterruptedException {
    DateTime now = DateTime.now(DateTimeZone.UTC);
    long remainingMillis = dt_start.getMillis() + timeoutMillis - now.getMillis();
    Thread.sleep(remainingMillis);
    return;
  }
  
  /**
   * Start the timer and wait for timeout.
   * Timeout should be specified in milliseconds
   * 
   * @param millis the millis
   * @throws InterruptedException the interrupted exception
   */
  public void startAndWaitForTimeout ( long millis ) throws InterruptedException{
    this.start(millis);
    this.waitForTimeout();
  }
  
}
