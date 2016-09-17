package elements.hyper.example_database;

import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

import database.MyDatabase;
import utils.Datas;

public class MainActivity extends AppCompatActivity {

    private Button btn_add_usr;
    private Button btn_retrieve_info;
    private Button btn_delete_id_1;
    private Button btn_limit3_offset_5;
    private Button btn_update_id_1;
    private TextView txt_result;
    private String name = "smit";
    private String surname = "satodia";
    private String mail = "satodiasmit@gmail.com";
    private String updatedname = "updatedsmit";
    private String updatedsurname = "updatedsatodia";
    private String updatedmail = "updatedsatodiasmit@gmail.com";
    private Cursor c;
    private Random random;
    private String filepath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        /*Datas.DB_name = filepath+"/"+Datas.DB_name;
        Log.e("Dbname",Datas.DB_name);*/

        btn_add_usr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int age = random.nextInt(100);
                int salary = random.nextInt(100000);
                long uid = MyDatabase.getInstance(getApplicationContext()).inserUser(name, surname, mail, age, salary);
                txt_result.setText("User id is " + uid);
            }
        });



        btn_retrieve_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c = MyDatabase.getInstance(getApplicationContext()).retrieveUser();
                String userinfo = "";
                if (c.moveToFirst())
                {
                    do
                    {
                        String id = c.getString(c.getColumnIndex(Datas.C_id));
                        String name = c.getString(c.getColumnIndex(Datas.C_name));
                        String surname = c.getString(c.getColumnIndex(Datas.C_surname));
                        String email = c.getString(c.getColumnIndex(Datas.C_email));
                        int age = c.getInt(c.getColumnIndex(Datas.C_age));
                        int salary = c.getInt(c.getColumnIndex(Datas.C_salary));

                        userinfo = userinfo+id+"\n"+name+"\n"+surname+"\n"+email+"\n"+age+"\n"+salary+"\n\n\n";
                    }
                    while (c.moveToNext());
                }
                txt_result.setText(userinfo);
                c.close();
            }
        });



        btn_delete_id_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    int userid = 1;
                    long i = MyDatabase.getInstance(getApplicationContext()).delete(userid);
                    txt_result.setText("result " + i);
             }
        });

        btn_limit3_offset_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int limit = 3;
                int offset = 5;
                c = MyDatabase.getInstance(getApplicationContext()).limitedData(limit, offset);
                String userinfo = "";
                if (c.moveToFirst())
                {
                    do
                    {
                        String id = c.getString(c.getColumnIndex(Datas.C_id));
                        String name = c.getString(c.getColumnIndex(Datas.C_name));
                        String surname = c.getString(c.getColumnIndex(Datas.C_surname));
                        String email = c.getString(c.getColumnIndex(Datas.C_email));
                        int age = c.getInt(c.getColumnIndex(Datas.C_age));
                        int salary = c.getInt(c.getColumnIndex(Datas.C_salary));

                        userinfo = userinfo+id+"\n"+name+"\n"+surname+"\n"+email+"\n"+age+"\n"+salary+"\n\n\n";
                    }
                    while (c.moveToNext());
                }
                txt_result.setText(userinfo);

            }
        });

        btn_update_id_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int userid = 1;
                int updatedage = random.nextInt(100);
                int updatedsalary = random.nextInt(100000);

                int i = MyDatabase.getInstance(getApplicationContext()).updateUser(userid,updatedname,updatedsurname,updatedmail,updatedage,updatedsalary);
                txt_result.setText("result " + i);

            }
        });
    }

    public void init(){
        btn_add_usr = (Button)findViewById(R.id.btn_add_usr);
        btn_retrieve_info = (Button)findViewById(R.id.btn_retrieve_info);
        btn_delete_id_1 = (Button)findViewById(R.id.btn_delete_id_1);
        btn_limit3_offset_5 = (Button)findViewById(R.id.btn_limit3_offset_5);
        btn_update_id_1 = (Button)findViewById(R.id.btn_update_id_1);
        txt_result = (TextView)findViewById(R.id.txt_result);
        random = new Random();
        filepath = Environment.getExternalStorageDirectory().getAbsolutePath().toString();


    }
}
