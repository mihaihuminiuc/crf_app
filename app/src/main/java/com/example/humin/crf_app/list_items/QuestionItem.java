package com.example.humin.crf_app.list_items;


/**
 * Created by humin on 3/23/2018.
 */

public class QuestionItem {
    public int type;
    public Object element;

    public QuestionItem(){}

    public QuestionItem(int type, Object element) {
        this.type = type;
        this.element = element;
    }
}
