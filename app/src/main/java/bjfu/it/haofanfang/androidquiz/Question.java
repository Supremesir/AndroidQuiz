package bjfu.it.haofanfang.androidquiz;

import androidx.annotation.NonNull;

/**
 * @author HaoFan Fang
 * @date 2020-01-01 21:34
 */

public class Question {
    private String type;
    private String question;
    private String answer;


    public Question(String type, String question) {
        this.type = type;
        this.question = question;
    }

    public static final Question[] questions={
            new Question("架构","自顶向下依次列举Android系统架构各层"),
            new Question("编程","Android App开发支持编程语言包括？"),
            new Question("SDK","Android开发者官网是？")
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

    @NonNull
    @Override
    public String toString() {
        return this.type;
    }
}
