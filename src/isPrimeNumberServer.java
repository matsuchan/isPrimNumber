import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class isPrimeNumberServer {

    private static final int times = 2;

    private static String serverProcess(String content) {
        StringBuilder sb = new StringBuilder();
        sb.append("🎁");
        for (int i = 0; i < times; i++) {
            sb.append(content);
        }
        sb.append("🎁");
        String result = sb.toString();
        return result;
    }

    public static void main(String arg[]) {
        try {
            /* 通信の準備をする */
            Scanner scanner = new Scanner(System.in);
            System.out.print("ポートを入力してください(5050など) → ");
            int port = scanner.nextInt();
            scanner.close();

            while(true){

            System.out.println("localhostの" + port + "番ポートで待機します");
            ServerSocket server = new ServerSocket(port); // ポート番号を指定し、クライアントとの接続の準備を行う

            Socket socket = server.accept(); // クライアントからの接続要求を待ち、
            // 要求があればソケットを取得し接続を行う

            System.out.println("接続しました。相手の入力を待っています......");

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            isPrimeNumberCalculate Pnumber = (isPrimeNumberCalculate) ois.readObject();// Integerクラスでキャスト。

            String presentFromClient = Pnumber.getContent();
            System.out.println("入力された値は" + presentFromClient);
            
            if(presentFromClient.equals("exit")||presentFromClient.equals("quit")){
                System.out.println("中断コマンドが確認されました。プログラムを終了します");
                break;
            }

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

            boolean isTrue = Pnumber.getisPrimeNumber();
            isPrimeNumberCalculate response = new isPrimeNumberCalculate();
            System.out.println("入力された値の判別結果は"+isTrue);
            response.setMessage("サーバーです。入力された値の判別結果は"+isTrue);
            response.setContent(serverProcess(presentFromClient));

            oos.writeObject(response);
            oos.flush();

            // close処理

            ois.close();
            oos.close();

            // socketの終了。
            socket.close();
            server.close();
            }
            
        } // エラーが発生したらエラーメッセージを表示してプログラムを終了する
        catch (BindException be) {
            be.printStackTrace();
            System.out.println("ポート番号が不正、ポートが使用中です");
            System.err.println("別のポート番号を指定してください(6000など)");
        } catch (Exception e) {
            System.err.println("エラーが発生したのでプログラムを終了します");
            throw new RuntimeException(e);
        }
    }
}
