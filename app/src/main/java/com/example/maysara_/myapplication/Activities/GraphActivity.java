package com.example.maysara_.myapplication.Activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.maysara_.myapplication.Helpers.DB_Helper;
import com.example.maysara_.myapplication.Models.Category;
import com.example.maysara_.myapplication.Models.Expense;
import com.example.maysara_.myapplication.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.BubbleChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.security.KeyStore;
import java.util.ArrayList;

public class GraphActivity extends AppCompatActivity {

    private LineChart lineChart;
    private BarChart barChart;
    String xvalues [] ;
    int yvalues [] ;
    String xvalues1 [] ;
    int yvalues1 [] ;

    ArrayList<Expense> expenses;
    int budgetId ;
    Category[] categories;
    DB_Helper db_helper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph);
        budgetId = (getIntent()).getIntExtra("budget", -1);

        expenses = new ArrayList<>();
        db_helper = new DB_Helper(this);
        categories = db_helper.getCategoriesForBudget(budgetId);
        for(int i =0;i<categories.length;i++){
            Expense[] expenses1 = db_helper.getAllExpensesForCategory(categories[i].getId());
            for(int j=0;j<expenses1.length;j++){
                expenses.add(expenses1[j]);
            }
        }
        if(expenses.size()==0){
            Toast.makeText(this, "No data to display !", Toast.LENGTH_SHORT).show();
            return;
        }
        xvalues = new String[expenses.size()];
        yvalues = new int[expenses.size()];


        lineChart = findViewById(R.id.linechart);
        barChart = findViewById(R.id.barchart);
        drawLineChart();
        drawBarChart();
    }

    private void drawLineChart(){
        getValues(expenses);
        ArrayList<Entry> yData = new ArrayList<>();
        for(int i=0;i<yvalues.length;i++){
            yData.add(new Entry(i,yvalues[i]));
        }

        LineDataSet lineDataSet = new LineDataSet(yData,"Expenses Graph");
        lineDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        LineData lineData = new LineData(lineDataSet);
        lineData.setValueTextSize(13f);
        lineData.setValueTextColor(Color.BLACK);
        lineChart.setData(lineData);
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return xvalues[(int)value];
            }
        });
        lineChart.invalidate();
    }

    private void drawBarChart(){
        getValues2();
        ArrayList<BarEntry> yData = new ArrayList<>();
        for(int i=0;i<yvalues1.length;i++){
            yData.add(new BarEntry(i,yvalues1[i]));
        }
        BarDataSet barDataSet= new BarDataSet(yData,"Categories Graph");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        BarData barData= new BarData(barDataSet);
        barData.setValueTextSize(13f);
        barData.setValueTextColor(Color.BLACK);
        barChart.setData(barData);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return xvalues1[(int)value];
            }
        });
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setCenterAxisLabels(false);
        barChart.invalidate();
    }


    private void getValues(ArrayList<Expense> expenses){
        for(int i =0;i<expenses.size();i++){
            xvalues[i] =  expenses.get(i).getLabel();
            yvalues[i] = (int) expenses.get(i).getAmount();
        }
    }

    private void getValues2(){
        xvalues1 = new String[categories.length];
        yvalues1 = new int[categories.length];
        for(int i =0;i<categories.length;i++){
            xvalues1[i] =  categories[i].getName();
            Expense[] expenses1 = db_helper.getAllExpensesForCategory(categories[i].getId());
            int total = 0;
            for(int j=0;j<expenses1.length;j++){
                total+=expenses1[j].getAmount();
            }
            yvalues1[i] = total;
        }
    }

}
