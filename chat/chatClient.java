/*
 * �`���b�g�N���C�A���g
 * chatClient.java
 *
 * �p�@��
 * >java chatClient 192.168.1.1
 */
import java.io.*;
import java.net.*;

public class chatClient{
    static final int CHAT_PORT = 10000;//Chat�|�[�g�ԍ�
    Socket sock;             //�ڑ��p�\�P�b�g
    BufferedInputStream in;  //���͗p�X�g���[��
    BufferedOutputStream out;//�o�͗p�X�g���[��
    String host;             //�T�[�o�A�h���X
    int port;                //�T�[�o�|�[�g�ԍ�

    //�R���X�g���N�^2
    public chatClient(String host, int port)
      throws IOException,UnknownHostException
    {
        this.host = host;
        this.port = port;
        sock = new Socket(host, port);
        out = new BufferedOutputStream(sock.getOutputStream());
        in = new BufferedInputStream(sock.getInputStream());

    }
    //�R���X�g���N�^1
    public chatClient(String host) throws IOException
    {
        this(host, CHAT_PORT);
    }
    //�`���b�g����
    public void chatProc() throws IOException
    {
        try{
            //SendInHandler�AGetOutHandler�I�u�W�F�N�g����
            SendInHandler insto = new SendInHandler(System.in,out);
            GetOutHandler outst = new GetOutHandler(in,System.out);
            //�X���b�h����
            Thread th1 = new Thread(insto);
            Thread th2 = new Thread(outst);
            //�X���b�h�N��
            th1.start();
            th2.start();
        }
        catch(Exception e){
            System.err.println(e);
            System.exit(1);
        }
    }
    //���C������
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
//���͑��M�n���h������
class SendInHandler implements Runnable{
    static final int LEN = 1024;    //keyinbuf�T�C�Y
    byte[] keyinbuf = new byte[LEN];//���M�o�b�t�@
    int k;                  //�o�C�g��
    InputStream in = null;  //���̓X�g���[���錾
    OutputStream out = null;//�o�̓X�g���[���錾
    //�R���X�g���N�^
    public SendInHandler(InputStream in, OutputStream out)
      throws IOException
    {
        this.in = in;
        this.out = out;
    }
    //quit�`�F�b�N
    private void quitCheck(byte buf[], int count) throws IOException{
        String keyinstr = new String(buf,0,count);
        if(keyinstr.equals("quit\r\n") || keyinstr.equals("QUIT\r\n")) {
            System.out.println("Please to" + "([Ctrl]+[C])");
        }
    }
    //�X�g���[���f�[�^���M
    public void run(){
        while(true){
            try{
                //�L�[�C���f�[�^���[�h
                k = in.read(keyinbuf);
                if(k == -1){
                    break;
                }
                //�X�g���[���f�[�^���M���C�g
                out.write(keyinbuf, 0, k);
                //�X�g���[���f�[�^�|���o��
                out.flush();
                quitCheck(keyinbuf, k);
            }catch(Exception e){
                e.printStackTrace();
                System.exit(1);
            }
        }
    }
}
//��M�o�̓n���h������
class GetOutHandler implements Runnable{
    static final int LEN = 1024;//getbuf�T�C�Y
    byte[] getbuf = new byte[LEN];//��M�o�b�t�@
    int k;                  //�o�C�g��
    InputStream in = null;  //���̓X�g���[���錾
    OutputStream out = null;//�o�̓X�g���[���錾
    // �R���X�g���N�^
    public GetOutHandler(InputStream in, OutputStream out)
      throws IOException
    {
        this.in = in;
        this.out = out;
    }
    //�X�g���[���f�[�^��M
    public void run(){
        while(true){
            try{
                //��M�f�[�^���[�h
                k = in.read(getbuf);
                if(k == -1){
                    break;
                }
                //�X�g���[���f�[�^�o�̓��C�g
                out.write(getbuf, 0, k);
                //�X�g���[���f�[�^�|���o��
                out.flush();
            }catch(Exception e){
                e.printStackTrace();
                System.exit(1);
            }
        }
    }
}
