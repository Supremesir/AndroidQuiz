package bjfu.it.haofanfang.androidquiz;

import androidx.annotation.NonNull;

/**
 * @author HaoFan Fang
 * @date 2020-01-01 21:34
 */

public class Question {
    private String type;    //问题类型
    private String question;    //问题
    private String answer;  //回答
    private String tanswer; //正确答案


    //有参构造
    public Question(String type, String question, String tanswer) {
        this.type = type;
        this.question = question;
        this.tanswer = tanswer;
    }


    public static final Question[] questions = {
            new Question("天气", "北京下雪了？", "yes"),
            new Question("考试", "移动开发都会了？", "no"),
            new Question("心情", "今天你开心吗？", "no")
    };

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getTanswer() {
        return tanswer;
    }

    public void setTanswer(String tanswer) {
        this.tanswer = tanswer;
    }

    //重要！！！
    @NonNull
    @Override
    public String toString() {
        return this.type;
    }
}
