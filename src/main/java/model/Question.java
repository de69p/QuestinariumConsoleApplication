package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Question {
    public Question(String text, String topic){
        this.text = text;
        this.topic = topic;
    }
    private int id;
    private String text;
    private String topic;

}