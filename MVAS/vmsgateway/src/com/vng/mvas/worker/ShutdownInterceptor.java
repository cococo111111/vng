package com.vng.mvas.worker;

public class ShutdownInterceptor extends Thread
{

 public ShutdownInterceptor(SendMessageWorker app)
 {
     this.app = app;
 }

 public void run()
 {
     System.out.println("Call the shutdown routine");
     app.windowClosing();
 }
 private SendMessageWorker app;
}