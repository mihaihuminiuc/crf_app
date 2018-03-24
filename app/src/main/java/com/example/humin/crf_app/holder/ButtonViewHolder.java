package com.example.humin.crf_app.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.humin.crf_app.R;
import com.example.humin.crf_app.list_items.QuestionItem;
import com.example.humin.crf_app.listener.QuestionAnswersClickListener;
import com.example.humin.crf_app.model.QuestionModel;

/**
 * Created by humin on 3/23/2018.
 */

public class ButtonViewHolder extends RecyclerView.ViewHolder {

    private Button mButton;

    public ButtonViewHolder(View itemView) {
        super(itemView);
        mButton = itemView.findViewById(R.id.send_button);
    }

    public void bindData(final QuestionItem questionItem, QuestionAnswersClickListener mQuestionAnswersClickListener) {
        mButton.setOnClickListener(view -> mQuestionAnswersClickListener.onSendAnswers());
    }
}