package com.mblonyox.iaktrivia.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mblonyox.iaktrivia.QuestionActivity;
import com.mblonyox.iaktrivia.R;

import org.apache.commons.lang3.StringEscapeUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by Sukirno on 18/02/2018.
 */

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {

    Context context;
    List<String> allAnswer;
    String correctAnswer;

    public QuestionAdapter(Context context, List<String> incorrect, String correct) {
        this.context = context;
        correctAnswer = correct;
        allAnswer = new ArrayList<String>(incorrect);
        allAnswer.add(correctAnswer);
        Collections.shuffle(allAnswer);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_question, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.txtAnswer.setText( StringEscapeUtils.unescapeHtml4( allAnswer.get(position) ) );
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((QuestionActivity)context).onAnswer(allAnswer.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return allAnswer.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtAnswer;

        public ViewHolder(View v) {
            super(v);
            txtAnswer = v.findViewById(R.id.answer_text);
        }
    }
}
