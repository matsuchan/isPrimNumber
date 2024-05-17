import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.BindException;
import java.net.Socket; //ネットワーク関連のパッケージを利用する
import java.util.Scanner;

public class isPrimeNumberClient {

    public static void main(String arg[]) {
        try {

            Scanner scanner = new Scanner(System.in);
            System.out.print("ポートを入力してください(5000など) → ");
            int port = scanner.nextInt();

            while(true){

            System.out.println("localhostの" + port + "番ポートに接続を要求します");

            Socket socket = new Socket("localhost", port);
            System.out.println("接続されました");
            System.out.println("素数かどうかを判別します");

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

            System.out.println("判別したい値を入力してください ↓");

            String content = scanner.next();

            if(content.equals("exit") || content.equals("quit")){
                System.out.println("中断コマンドが確認されました。プログラムを終了します");
                isPrimeNumberCalculate stop = new isPrimeNumberCalculate();
                stop.setContent(content);
                oos.writeObject(stop);
                oos.flush();
                scanner.close();
            }

            int number = Integer.parseInt(content);

            isPrimeNumberCalculate Pnumber = new isPrimeNumberCalculate();
            Pnumber.setContent(content);
            Pnumber.setisPrimeNumber(number);

            oos.writeObject(Pnumber);
            oos.flush();

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            isPrimeNumberCalculate okaeshiPresent = (isPrimeNumberCalculate) ois.readObject();

            String replayMsg = okaeshiPresent.getMessage();
            System.out.println(replayMsg);

            ois.close();
            oos.close();
            socket.close();
        }

        } // エラーが発生したらエラーメッセージを表示してプログラムを終了する
        catch (BindException be) {
            be.printStackTrace();
            System.err.println("ポート番号が不正か、サーバが起動していません");
            System.err.println("サーバが起動しているか確認してください");
            System.err.println("別のポート番号を指定してください(6000など)");
        } catch (Exception e) {
            System.err.println("エラーが発生したのでプログラムを終了します");
            throw new RuntimeException(e);
        }
    }
}
