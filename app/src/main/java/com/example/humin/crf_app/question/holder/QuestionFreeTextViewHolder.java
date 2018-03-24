package com.example.humin.crf_app.question.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.humin.crf_app.R;
import com.example.humin.crf_app.question.item.QuestionItem;
import com.example.humin.crf_app.model.QuestionModel;
import com.example.humin.crf_app.model.UserResponseModel;
import com.example.humin.crf_app.util.CommonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by humin on 3/23/2018.
 */

public class QuestionFreeTextViewHolder extends RecyclerView.ViewHolder {

    private TextView questionText;
    private EditText answerText;

    public QuestionFreeTextViewHolder(View itemView) {
        super(itemView);

        questionText = itemView.findViewById(R.id.txt_question);
        answerText =  itemView.findViewById(R.id.edt_answer);
    }

    public void bindData(Context context, int position, final QuestionItem questionItem) {
        questionText.setTextColor(CommonUtils.getColor(context,R.color.gray_light));
        answerText.setTextColor(CommonUtils.getColor(context,R.color.blue));
        questionText.setText(context.getString(R.string.question_item, position, ((QuestionModel)questionItem.element).getTitle()));

        answerText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                UserResponseModel userResponseModel = new UserResponseModel();
                List<String> userAnswerList = new ArrayList<>();

                if(!((QuestionModel)questionItem.element).isMultipleChoice()){
                    userAnswerList.clear();
                }
                if(!userAnswerList.contains(editable.toString())){
                    userAnswerList.add(editable.toString());
                }

                userResponseModel.setQuestionGUID(((QuestionModel)questionItem.element).getQuestionGUID());
                userResponseModel.setAnswer(userAnswerList);

                ((QuestionModel)questionItem.element).setUserResponses(userResponseModel);
            }
        });
    }
}
