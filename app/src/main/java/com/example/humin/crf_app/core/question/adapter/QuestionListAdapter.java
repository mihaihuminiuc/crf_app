package com.example.humin.crf_app.core.question.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.humin.crf_app.R;
import com.example.humin.crf_app.core.question.holder.ButtonViewHolder;
import com.example.humin.crf_app.core.question.holder.QuestionFreeTextViewHolder;
import com.example.humin.crf_app.core.question.holder.QuestionRatingViewHolder;
import com.example.humin.crf_app.core.question.holder.QuestionTextViewHolder;
import com.example.humin.crf_app.core.question.item.QuestionItem;
import com.example.humin.crf_app.listener.QuestionAnswersClickListener;
import com.example.humin.crf_app.model.QuestionModel;

import java.util.List;

import io.reactivex.annotations.NonNull;

/**
 * Created by humin on 3/23/2018.
 */

public class QuestionListAdapter extends RecyclerView.Adapter{

    private Context mContext;
    private List<QuestionItem> mItems;
    private QuestionAnswersClickListener mQuestionAnswersClickListener;

    public QuestionListAdapter(final List<QuestionItem> mItems, Context context, QuestionAnswersClickListener questionAnswersClickListener) {
        if (mItems != null) {
            this.mItems = mItems;
        }
        this.mQuestionAnswersClickListener=questionAnswersClickListener;
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        View view;

        switch (viewType){
            case QuestionModel.ANSWER_TYPE_FREE_TEXT:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_question_text, parent, false);
                viewHolder = new QuestionFreeTextViewHolder(view);
                break;
            case QuestionModel.ANSWER_TYPE_RATING:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_question_raiting, parent, false);
                viewHolder = new QuestionRatingViewHolder(view);
                break;
            case QuestionModel.ANSWER_TYPE_TEXT:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_question_with_answers, parent, false);
                viewHolder = new QuestionTextViewHolder(view);
                break;

            case QuestionModel.ANSWER_BUTTON_SEND:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_question_button, parent, false);
                viewHolder = new ButtonViewHolder(view);
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof QuestionFreeTextViewHolder){
            ((QuestionFreeTextViewHolder) holder).bindData(mContext,position,mItems.get(position));
        }else if(holder instanceof QuestionRatingViewHolder){
            ((QuestionRatingViewHolder) holder).bindData(mContext,position,mItems.get(position));
        }else if(holder instanceof QuestionTextViewHolder){
            ((QuestionTextViewHolder) holder).bindData(mContext,position,mItems.get(position));
        } else if(holder instanceof ButtonViewHolder){
            ((ButtonViewHolder) holder).bindData(mItems.get(position), mQuestionAnswersClickListener);
        }
    }

    @Override
    public int getItemCount() {
        return mItems == null ? 0 : mItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).type;
    }

    public void updateList(List<QuestionItem> mItems){
        if (mItems != null) {
            this.mItems = mItems;
        }
        notifyDataSetChanged();
    }

    public List<QuestionItem> questionItems(){
        return this.mItems;
    }
}
