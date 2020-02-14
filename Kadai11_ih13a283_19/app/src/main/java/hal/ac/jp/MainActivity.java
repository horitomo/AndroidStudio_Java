package hal.ac.jp;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    List<Company> companyList;
    int position2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.timetable);
        companyList = new ArrayList<Company>();

        Button register_btn = findViewById(R.id.register_btn);
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                position2 = position;
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("登録内容の削除")
                        .setMessage("本当に削除しますか？")
                        .setPositiveButton("削除", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                delete_list(position2);
                            }
                        })
                        .setNegativeButton("キャンセル", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();
                return true;
            }
        });
    }

    public void register(){
        EditText etCompanyName = findViewById(R.id.company_name);
        String company_name = etCompanyName.getText().toString();
        EditText etvisitDate = findViewById(R.id.visit_date);
        String visit_date = etvisitDate.getText().toString();
        EditText etvisitTime = findViewById(R.id.visit_time);
        String visit_time = etvisitTime.getText().toString();
        EditText etvisitPurpose = findViewById(R.id.visit_purpose);
        String visit_purpose = etvisitPurpose.getText().toString();
        EditText etvisitPlace = findViewById(R.id.visit_place);
        String visit_place = etvisitPlace.getText().toString();

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1);
        companyList.add(new Company(company_name,visit_date,visit_time,visit_purpose,visit_place));
        for(Company c : companyList){
            String str1 = c.companyStr();
            String str = c.getVisitDate()+c.getVisitTime()+c.getCompanyName()+c.getVisitPurpose()+c.getVisitPlace();
            adapter2.add(str1);
        }
        lv.setAdapter(adapter2);
    }

    public  void delete_list(int position){
        companyList.remove(position);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1);
        for(Company c : companyList){
            String str2 = c.companyStr();
            String str = c.getVisitDate()+c.getVisitTime()+c.getCompanyName()+c.getVisitPurpose()+c.getVisitPlace();
            adapter2.add(str2);
        }
        lv.setAdapter(adapter2);
    }
}
