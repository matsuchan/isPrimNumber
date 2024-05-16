import java.io.Serializable;

public class isPrimeNumberCalculate implements Serializable {

    int number;
    String content;
    String Message;

    
    public String getMessage() {
        return Message;
    }

    public void setMessage(String present) {
        this.Message = present;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String present) {
        this.content = present;
    }

    public boolean getisPrimeNumber() {
        for(int i=2;i<number;i++){
            if(number%i==0){
                return false;
            }
        }
        return true;
    }

    public void setisPrimeNumber(int number){
        this.number = number;
    }

}