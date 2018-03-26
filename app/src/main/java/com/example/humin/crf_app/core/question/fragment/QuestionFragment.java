package com.example.humin.crf_app.core.question.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.humin.crf_app.R;
import com.example.humin.crf_app.core.question.adapter.QuestionListAdapter;
import com.example.humin.crf_app.core.question.item.QuestionItem;
import com.example.humin.crf_app.listener.QuestionAnswersClickListener;
import com.example.humin.crf_app.model.QuestionModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by humin on 3/23/2018.
 */

public class QuestionFragment extends Fragment implements QuestionAnswersClickListener{


    @BindView(R.id.simple_recyclerview) RecyclerView recyclerView;

    private QuestionListAdapter adapter;
    private List<QuestionModel> mQuestionList;

    private Context mContext;

    public static QuestionFragment newInstance(List<QuestionModel> mList){
        QuestionFragment fragment = new QuestionFragment();
        fragment.mQuestionList=mList;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.question_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mContext = getActivity();

        ButterKnife.bind(this, view);

        setupList();
    }

    private void setupList(){

        ArrayList<QuestionItem>  questionModelList = new ArrayList<>(mQuestionList.size());

        for(int i=0;i<mQuestionList.size();i++){
            switch (mQuestionList.get(i).getAnswerType()){
                case QuestionModel.ANSWER_TYPE_FREE_TEXT:
                    questionModelList.add(new QuestionItem(QuestionModel.ANSWER_TYPE_FREE_TEXT,mQuestionList.get(i)));
                    break;
                case QuestionModel.ANSWER_TYPE_RATING:
                    questionModelList.add(new QuestionItem(QuestionModel.ANSWER_TYPE_RATING,mQuestionList.get(i)));
                    break;
                case QuestionModel.ANSWER_TYPE_TEXT:
                    questionModelList.add(new QuestionItem(QuestionModel.ANSWER_TYPE_TEXT,mQuestionList.get(i)));
                    break;
            }

        }
        questionModelList.add(new QuestionItem(QuestionModel.ANSWER_BUTTON_SEND,mQuestionList.size()));

        adapter = new QuestionListAdapter(questionModelList,mContext,this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(mContext,1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onSendAnswers() {
        for (QuestionModel q:mQuestionList) {
            if(q.getUserResponses()==null){
                Toast.makeText(mContext,getString(R.string.answer_error),Toast.LENGTH_SHORT).show();
                return;
            }
            for(String answer:q.getUserResponses().getAnswer()){
                Toast.makeText(mContext,getString(R.string.answer_text, answer),Toast.LENGTH_SHORT).show();
            }
        }
    }
}