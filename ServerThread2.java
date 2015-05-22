package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ServerThread2 implements Runnable {

     //定义当前线程所处理的socket
     static Socket s=null;
     //该线程所处理的socket所对应的输入流
     BufferedReader br=null;
    
     public ServerThread2(Socket s)
     {
          try
          {
               ServerThread2.s=s;
               //初始化socket对应的输入流
               br=new BufferedReader(new InputStreamReader(s.getInputStream()));
              
          }
          catch(IOException e)
          {
               e.printStackTrace();
          }
    
     }
    
     @Override
     public void run() {

         try
         {
              String content=null;
              //采用循环不断从Socket中读取客户端发送过来的数据
              //while((content=readFromClient ())!=null )
              if((content=readFromClient ())!=null )
              {
            	  //向界面输出信息
//            	  System.out.print(
//            			  Main_GUI.set_jTextArea1(content,s.getInetAddress().toString()));
            	  System.out.println(content);
            	  new HandleAndroidMessage().Analyze(content);
              }
              s.close();
         }
         catch(Exception e)
         {
              try
              {
                   s.close();
              }catch(IOException ex)
              {
                   ex.printStackTrace();
              }
         }

     }

	//服务器对请求进行答复
	static void Send_back(String string) throws IOException {
		// TODO Auto-generated method stub
//		System.out.print(Main_GUI.set_jTextArea1("返回："+string));
		System.out.println("返回："+string);
		PrintStream ps=new PrintStream(s.getOutputStream());
        ps.println(string);
	}

	//定义读取客户端数据的方法
     private String readFromClient()
     {
          try
          {
               return br.readLine();
          }
          //如果捕捉到异常，表明该socket对应的客户端已经关闭
          catch(IOException e)
          {
              e.printStackTrace();
          }
        return null;
     }     
}
