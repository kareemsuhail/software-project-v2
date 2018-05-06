package com.example.maysara_.myapplication.Helpers;

import android.content.Context;
import android.content.Intent;

import com.example.maysara_.myapplication.Activities.GraphActivity;

public class GraphHelper {


    public static void showGraphForBudget(Context context,int budgetId){
        Intent intent = new Intent(context, GraphActivity.class);
        intent .putExtra("budget", budgetId);
        context.startActivity(intent);

    }
}


