package com.example.humin.crf_app.question.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.humin.crf_app.R;
import com.example.humin.crf_app.question.item.QuestionItem;
import com.example.humin.crf_app.listener.QuestionAnswersClickListener;

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