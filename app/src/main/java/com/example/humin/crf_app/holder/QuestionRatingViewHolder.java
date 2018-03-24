package com.example.humin.crf_app.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.humin.crf_app.R;
import com.example.humin.crf_app.list_items.QuestionItem;
import com.example.humin.crf_app.model.QuestionModel;
import com.example.humin.crf_app.model.UserResponseModel;
import com.example.humin.crf_app.util.CommonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by humin on 3/23/2018.
 */

public class QuestionRatingViewHolder extends RecyclerView.ViewHolder {

    private TextView questionText;
    private LinearLayout masterContainer;
    private List<FrameLayout> frameList;
    private List<TextView> textList;
    private List<ImageView> imageList;

    public QuestionRatingViewHolder(View itemView) {
        super(itemView);
        masterContainer = itemView.findViewById(R.id.rating_layout);
        questionText = itemView.findViewById(R.id.txt_question);
    }

    public void bindData(Context context, int position, final QuestionItem questionItem) {

        frameList = new ArrayList<>();
        textList = new ArrayList<>();
        imageList = new ArrayList<>();

        questionText.setText(context.getString(R.string.question_item, position, ((QuestionModel)questionItem.element).getTitle()));
        questionText.setTextColor(CommonUtils.getColor(context, R.color.gray_light));

        int padding = (int) CommonUtils.dp2px(context.getResources(), 10f);
        LinearLayout.LayoutParams chkLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        chkLp.setMargins(padding, padding, padding, padding);

        for(int i=0;i<((QuestionModel)questionItem.element).getAnswers().size();i++){

            FrameLayout frameLayout = new FrameLayout(context);
            ImageView imageView = new ImageView(context);
            TextView textView = new TextView(context);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(100, 100);
            layoutParams.setMargins(10,10,10,10);

            imageView.setBackgroundResource(R.drawable.whitecircle);
            textView.setText(String.valueOf(((QuestionModel)questionItem.element).getAnswers().get(i).getRating()));
            textView.setTextColor(CommonUtils.getColor(context,R.color.blue_dark));
            textView.setGravity(Gravity.CENTER);

            frameLayout.addView(imageView);
            frameLayout.addView(textView);
            frameLayout.setLayoutParams(layoutParams);
            masterContainer.addView(frameLayout);

            frameList.add(frameLayout);
            textList.add(textView);
            imageList.add(imageView);
        }

        for(int i=0;i<((QuestionModel)questionItem.element).getAnswers().size();i++){
            int finalI = i;
            frameList.get(i).setOnClickListener(view ->{
                setChecked(context,textList,imageList,finalI);
                UserResponseModel userResponseModel = new UserResponseModel();
                List<String> userAnswerList = new ArrayList<>();

                if(!((QuestionModel)questionItem.element).isMultipleChoice()){
                    userAnswerList.clear();
                }
                if(!userAnswerList.contains(textList.get(finalI).getText().toString())){
                    userAnswerList.add(textList.get(finalI).getText().toString());
                }

                userResponseModel.setQuestionGUID(((QuestionModel)questionItem.element).getQuestionGUID());
                userResponseModel.setAnswer(userAnswerList);

                ((QuestionModel)questionItem.element).setUserResponses(userResponseModel);
            });
        }
    }


    private void setChecked(Context context, List<TextView> textView, List<ImageView> imageView, int position){
        for(int i=0;i<textView.size();i++){
            if(i==position){
                textView.get(i).setTextColor(CommonUtils.getColor(context,R.color.white));
                imageView.get(i).setBackgroundResource(R.drawable.bluecircle);
            }else {
                textView.get(i).setTextColor(CommonUtils.getColor(context,R.color.blue_dark));
                imageView.get(i).setBackgroundResource(R.drawable.whitecircle);
            }
        }
    }
}
