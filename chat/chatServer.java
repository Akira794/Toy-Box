/**
 * Chatサーバ
 * chatServer.java
 * 
 * 用法例
 * >java chatServer
 */
import java.io.*;
import java.net.*;
import java.util.*;

public class chatServer{
    static final int CHAT_PORT = 10000;//Chatサーバ接続用ポート番号
    static ServerSocket servsock;      //サーバソケット宣言
    static ArrayList al;               //ArrayList宣言

    //メッセージ送信
    public static void sendMess(String mess){
        Socket sockdt;//ソケット宣言
        BufferedWriter wr; //出力ストリーム用
        int total;//ソケット総数
        int num = 0;//ソケット総数
        num = al.size();
        total = num;
        //System.out.println(num);
        //ソケット確認
        if(num != 0){
            for(int i=0;i<total;i++){
                //ソケット取得
                sockdt = (Socket)al.get(i);
                try{
                    OutputStream out = sockdt.getOutputStream();
                    wr = new BufferedWriter(new OutputStreamWriter(out));
                    //該当ソケットへメッセージ出力
                    wr.write(mess,0,mess.length());
                    wr.flush();
                }catch (IOException ex){}
            }
        }
        System.out.println(mess);
    }
    //ソケット情報追加
    public static void addEntry(Socket s){
        if (al == null){ //ソケット情報なし?
            al = new ArrayList(); //リスト作成
        }
        al.add(s); //クライアントソケット追加
        //System.out.println(al);
    }
    //ソケット情報削除
    public static void deleteEntry(Socket s){
        if(al.isEmpty() != true){ //ソケット情報あり?
            al.remove(s); //クライアントソケット削除
        }
        //System.out.println(al);
    }
    //メイン処理
    public static void main(String[] args){
        try{
            //サーバソケット生成
            servsock = new ServerSocket(CHAT_PORT);
        }catch (IOException e){
            System.err.println("creation error");
            System.exit(1);
        }
        System.out.println("Connections on port " 
                     + servsock.getLocalPort());
        //無限ループ
        while(true){
            try{
                //接続要求待機
                Socket sock = servsock.accept();
                addEntry(sock);
                clientProc cp = new clientProc(sock);
                //Threadクラスのインスタンス生成
                Thread th = new Thread(cp);
                //スレッド起動
                th.start();
            }catch (IOException e){
                System.err.println("accept error.");
                System.exit(1);
            }
        }
    }
}

//クライアント処理
class clientProc implements Runnable{
    Socket s;           //接続用ソケット
    BufferedReader rd;  //入力ストリーム用
    BufferedWriter wr;  //出力ストリーム用
    String name = null; //ログイン名
    boolean bflg =true; //quit検出フラグ
    //コンストラクタ
    public clientProc(Socket s) throws IOException{
        this.s = s;
        //入出力ストリーム宣言
        InputStream in = s.getInputStream();
        rd = new BufferedReader(new InputStreamReader(in));
        OutputStream out = s.getOutputStream();
        wr = new BufferedWriter(new OutputStreamWriter(out));
    }
    //スレッド本体
    public void run(){
        try{
            String sendstr = "ハンドルネーム:";
            wr.write(sendstr,0,sendstr.length());
            wr.flush();
            while(name  == null){
                name = rd.readLine();//ログイン名
            }
            //ログインメッセージ
            chatServer.sendMess("#"+name+"さんが入室しました\r\n");
            String mess = rd.readLine(); //メッセージリード
            if("quit".equalsIgnoreCase(mess)){//quit?
                bflg = false;
            }
            while(bflg){
                //メッセージを全クライアントに送信
                chatServer.sendMess(name + ">" +mess + "\r\n");
                mess = rd.readLine(); //メッセージリード
                if("quit".equalsIgnoreCase(mess)){//quit?
                    bflg = false;
                }
            }
            //ログアウトメッセージ
            chatServer.sendMess("#"+name+"さんが退室しました\r\n");
            chatServer.deleteEntry(s);
            s.close();
        }catch (IOException e){
            try{
                s.close();
                System.err.println("running error");
                System.exit(1);
            }catch (IOException e1){
            }
        }
    }
}
