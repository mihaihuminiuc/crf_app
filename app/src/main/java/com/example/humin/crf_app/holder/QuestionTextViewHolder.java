package com.example.humin.crf_app.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.humin.crf_app.R;
import com.example.humin.crf_app.listitem.QuestionItem;
import com.example.humin.crf_app.model.AnswersModel;
import com.example.humin.crf_app.model.QuestionModel;
import com.example.humin.crf_app.model.UserResponseModel;
import com.example.humin.crf_app.util.CommonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by humin on 3/23/2018.
 */

public class QuestionTextViewHolder  extends RecyclerView.ViewHolder {

    private LinearLayout masterContainer;
    private TextView questionText;

    public QuestionTextViewHolder(View itemView) {
        super(itemView);
    }

    public void bindData(Context context, int position, final QuestionItem questionItem) {

        List<TextView> textsList = new ArrayList<>();
        List<CheckBox> checkboxesList = new ArrayList<>();

        int textColor = CommonUtils.getColor(context, R.color.gray_light);
        int padding = (int) CommonUtils.dp2px(context.getResources(), 10f);
        int textSize = context.getResources().getDimensionPixelSize(R.dimen.text_size_dimen_medium_large);

        LinearLayout.LayoutParams chkLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        chkLp.setMargins(padding, padding, padding, padding);

        questionText = itemView.findViewById(R.id.txt_question);
        questionText.setTextColor(context.getResources().getColor(R.color.gray_light));
        masterContainer = itemView.findViewById(R.id.master_container_row_quiz_text);

        for (int i = 0; i < ((QuestionModel)questionItem.element).getAnswers().size(); i++) {
            LinearLayout ll = new LinearLayout(context);
            ll.setOrientation(LinearLayout.HORIZONTAL);
            ll.setGravity(Gravity.CENTER_VERTICAL);

            CheckBox checkbox = new CheckBox(context);
            checkbox.setButtonDrawable(R.drawable.checkbox_agree);
            checkbox.setLayoutParams(chkLp);

            TextView txt = new TextView(context);
            txt.setTextColor(textColor);
            txt.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);

            ll.addView(checkbox);
            ll.addView(txt);
            masterContainer.addView(ll);

            textsList.add(txt);
            checkboxesList.add(checkbox);
        }

        List<AnswersModel> answerList = ((QuestionModel)questionItem.element).getAnswers();
        questionText.setText(context.getString(R.string.question_item, position, ((QuestionModel)questionItem.element).getTitle()));

        for (int i = 0; i < textsList.size(); i++) {//reset all
            textsList.get(i).setTextColor(CommonUtils.getColor(context, R.color.gray_light));
            checkboxesList.get(i).setChecked(false);
        }

        for (int i = 0; i < textsList.size(); i++) {
            final int iLocal = i;

            if (answerList.size() > i) {
                textsList.get(i).setVisibility(View.VISIBLE);
                checkboxesList.get(i).setVisibility(View.VISIBLE);
                textsList.get(i).setText(answerList.get(i).getSingleAnswer());
            } else {
                textsList.get(i).setVisibility(View.GONE);
                checkboxesList.get(i).setVisibility(View.GONE);
            }

            View.OnClickListener onClickListener = v -> {
                if (!((QuestionModel)questionItem.element).isMultipleChoice()) {
                    for (int j = 0; j < checkboxesList.size(); j++) {
                        checkboxesList.get(j).setChecked(false);
                        textsList.get(j).setTextColor(CommonUtils.getColor(context, R.color.gray_light));
                    }
                }

                checkboxesList.get(iLocal).setChecked(true);
                textsList.get(iLocal).setTextColor(CommonUtils.getColor(context, R.color.blue_dark));

                UserResponseModel userResponseModel = new UserResponseModel();
                List<String> userAnswerList = new ArrayList<>();

                if(!((QuestionModel)questionItem.element).isMultipleChoice()){
                    userAnswerList.clear();
                }
                if(!userAnswerList.contains(textsList.get(iLocal).getText().toString())){
                    userAnswerList.add(textsList.get(iLocal).getText().toString());
                }

                userResponseModel.setQuestionGUID(((QuestionModel)questionItem.element).getQuestionGUID());
                userResponseModel.setAnswer(userAnswerList);

                ((QuestionModel)questionItem.element).setUserResponses(userResponseModel);
            };
            textsList.get(iLocal).setOnClickListener(onClickListener);
            checkboxesList.get(iLocal).setOnClickListener(onClickListener);
        }
    }
}
