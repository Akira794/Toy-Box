/**
 * Chat�T�[�o
 * chatServer.java
 * 
 * �p�@��
 * >java chatServer
 */
import java.io.*;
import java.net.*;
import java.util.*;

public class chatServer{
    static final int CHAT_PORT = 10000;//Chat�T�[�o�ڑ��p�|�[�g�ԍ�
    static ServerSocket servsock;      //�T�[�o�\�P�b�g�錾
    static ArrayList al;               //ArrayList�錾

    //���b�Z�[�W���M
    public static void sendMess(String mess){
        Socket sockdt;//�\�P�b�g�錾
        BufferedWriter wr; //�o�̓X�g���[���p
        int total;//�\�P�b�g����
        int num = 0;//�\�P�b�g����
        num = al.size();
        total = num;
        //System.out.println(num);
        //�\�P�b�g�m�F
        if(num != 0){
            for(int i=0;i<total;i++){
                //�\�P�b�g�擾
                sockdt = (Socket)al.get(i);
                try{
                    OutputStream out = sockdt.getOutputStream();
                    wr = new BufferedWriter(new OutputStreamWriter(out));
                    //�Y���\�P�b�g�փ��b�Z�[�W�o��
                    wr.write(mess,0,mess.length());
                    wr.flush();
                }catch (IOException ex){}
            }
        }
        System.out.println(mess);
    }
    //�\�P�b�g���ǉ�
    public static void addEntry(Socket s){
        if (al == null){ //�\�P�b�g���Ȃ�?
            al = new ArrayList(); //���X�g�쐬
        }
        al.add(s); //�N���C�A���g�\�P�b�g�ǉ�
        //System.out.println(al);
    }
    //�\�P�b�g���폜
    public static void deleteEntry(Socket s){
        if(al.isEmpty() != true){ //�\�P�b�g��񂠂�?
            al.remove(s); //�N���C�A���g�\�P�b�g�폜
        }
        //System.out.println(al);
    }
    //���C������
    public static void main(String[] args){
        try{
            //�T�[�o�\�P�b�g����
            servsock = new ServerSocket(CHAT_PORT);
        }catch (IOException e){
            System.err.println("creation error");
            System.exit(1);
        }
        System.out.println("Connections on port " 
                     + servsock.getLocalPort());
        //�������[�v
        while(true){
            try{
                //�ڑ��v���ҋ@
                Socket sock = servsock.accept();
                addEntry(sock);
                clientProc cp = new clientProc(sock);
                //Thread�N���X�̃C���X�^���X����
                Thread th = new Thread(cp);
                //�X���b�h�N��
                th.start();
            }catch (IOException e){
                System.err.println("accept error.");
                System.exit(1);
            }
        }
    }
}

//�N���C�A���g����
class clientProc implements Runnable{
    Socket s;           //�ڑ��p�\�P�b�g
    BufferedReader rd;  //���̓X�g���[���p
    BufferedWriter wr;  //�o�̓X�g���[���p
    String name = null; //���O�C����
    boolean bflg =true; //quit���o�t���O
    //�R���X�g���N�^
    public clientProc(Socket s) throws IOException{
        this.s = s;
        //���o�̓X�g���[���錾
        InputStream in = s.getInputStream();
        rd = new BufferedReader(new InputStreamReader(in));
        OutputStream out = s.getOutputStream();
        wr = new BufferedWriter(new OutputStreamWriter(out));
    }
    //�X���b�h�{��
    public void run(){
        try{
            String sendstr = "�n���h���l�[��:";
            wr.write(sendstr,0,sendstr.length());
            wr.flush();
            while(name  == null){
                name = rd.readLine();//���O�C����
            }
            //���O�C�����b�Z�[�W
            chatServer.sendMess("#"+name+"���񂪓������܂���\r\n");
            String mess = rd.readLine(); //���b�Z�[�W���[�h
            if("quit".equalsIgnoreCase(mess)){//quit?
                bflg = false;
            }
            while(bflg){
                //���b�Z�[�W��S�N���C�A���g�ɑ��M
                chatServer.sendMess(name + ">" +mess + "\r\n");
                mess = rd.readLine(); //���b�Z�[�W���[�h
                if("quit".equalsIgnoreCase(mess)){//quit?
                    bflg = false;
                }
            }
            //���O�A�E�g���b�Z�[�W
            chatServer.sendMess("#"+name+"���񂪑ގ����܂���\r\n");
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
