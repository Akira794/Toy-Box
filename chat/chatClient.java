/*
 * チャットクライアント
 * chatClient.java
 *
 * 用法例
 * >java chatClient 192.168.1.1
 */
import java.io.*;
import java.net.*;

public class chatClient{
    static final int CHAT_PORT = 10000;//Chatポート番号
    Socket sock;             //接続用ソケット
    BufferedInputStream in;  //入力用ストリーム
    BufferedOutputStream out;//出力用ストリーム
    String host;             //サーバアドレス
    int port;                //サーバポート番号

    //コンストラクタ2
    public chatClient(String host, int port)
      throws IOException,UnknownHostException
    {
        this.host = host;
        this.port = port;
        sock = new Socket(host, port);
        out = new BufferedOutputStream(sock.getOutputStream());
        in = new BufferedInputStream(sock.getInputStream());

    }
    //コンストラクタ1
    public chatClient(String host) throws IOException
    {
        this(host, CHAT_PORT);
    }
    //チャット処理
    public void chatProc() throws IOException
    {
        try{
            //SendInHandler、GetOutHandlerオブジェクト生成
            SendInHandler insto = new SendInHandler(System.in,out);
            GetOutHandler outst = new GetOutHandler(in,System.out);
            //スレッド生成
            Thread th1 = new Thread(insto);
            Thread th2 = new Thread(outst);
            //スレッド起動
            th1.start();
            th2.start();
        }
        catch(Exception e){
            System.err.println(e);
            System.exit(1);
        }
    }
    //メイン処理
    public static void main(String[] args){
        try{
            if(args.length != 1){
               throw new IllegalArgumentException("usage:>java chatClient <host_address>");
            }
            chatClient chat = new chatClient(args[0]);
            chat.chatProc();
        }catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }
    }
}
//入力送信ハンドラ処理
class SendInHandler implements Runnable{
    static final int LEN = 1024;    //keyinbufサイズ
    byte[] keyinbuf = new byte[LEN];//送信バッファ
    int k;                  //バイト数
    InputStream in = null;  //入力ストリーム宣言
    OutputStream out = null;//出力ストリーム宣言
    //コンストラクタ
    public SendInHandler(InputStream in, OutputStream out)
      throws IOException
    {
        this.in = in;
        this.out = out;
    }
    //quitチェック
    private void quitCheck(byte buf[], int count) throws IOException{
        String keyinstr = new String(buf,0,count);
        if(keyinstr.equals("quit\r\n") || keyinstr.equals("QUIT\r\n")) {
            System.out.println("Please to" + "([Ctrl]+[C])");
        }
    }
    //ストリームデータ送信
    public void run(){
        while(true){
            try{
                //キーインデータリード
                k = in.read(keyinbuf);
                if(k == -1){
                    break;
                }
                //ストリームデータ送信ライト
                out.write(keyinbuf, 0, k);
                //ストリームデータ掃き出し
                out.flush();
                quitCheck(keyinbuf, k);
            }catch(Exception e){
                e.printStackTrace();
                System.exit(1);
            }
        }
    }
}
//受信出力ハンドラ処理
class GetOutHandler implements Runnable{
    static final int LEN = 1024;//getbufサイズ
    byte[] getbuf = new byte[LEN];//受信バッファ
    int k;                  //バイト数
    InputStream in = null;  //入力ストリーム宣言
    OutputStream out = null;//出力ストリーム宣言
    // コンストラクタ
    public GetOutHandler(InputStream in, OutputStream out)
      throws IOException
    {
        this.in = in;
        this.out = out;
    }
    //ストリームデータ受信
    public void run(){
        while(true){
            try{
                //受信データリード
                k = in.read(getbuf);
                if(k == -1){
                    break;
                }
                //ストリームデータ出力ライト
                out.write(getbuf, 0, k);
                //ストリームデータ掃き出し
                out.flush();
            }catch(Exception e){
                e.printStackTrace();
                System.exit(1);
            }
        }
    }
}
