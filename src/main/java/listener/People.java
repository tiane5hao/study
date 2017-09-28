package listener;

/**
 * Created by 听风 on 2017/9/8.
 */
public class People {
     volatile Sign status;

    public enum Sign{
        START,END
    }

    public People(){
        this.status = Sign.START;
    }

    public void changeStatus(Sign status){
        System.out.println("当前状态为"+this.status + "修改后的状态为"+status);
        this.status = status;
    }

}
