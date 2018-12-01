package janella.calderon.com.calderon_exam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {

    FirebaseDatabase db;
    DatabaseReference root;
    EditText eFname, eLname, eGrade, eGrade2;
    TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = FirebaseDatabase.getInstance();
        root = db.getReference("grade");
        eFname = findViewById(R.id.eFN);
        eLname = findViewById(R.id.eLN);
        eGrade = findViewById(R.id.eGrade);
        eGrade2 = findViewById(R.id.eGrade2);
        display = findViewById(R.id.display);
    }

    public void addRecord(View v) {
        String fname = eFname.getText().toString().trim();
        String lname = eLname.getText().toString().trim();
        Long grade = Long.parseLong(eGrade.getText().toString().trim());
        Long grade2 = Long.parseLong(eGrade2.getText().toString().trim());

        Long average = (grade + grade2) / 2;
        Student sgrade = new Student(fname,lname,average);
        String key = root.push().getKey();
        root.child(key).setValue(sgrade);
        Toast.makeText(this,"record added to db",Toast.LENGTH_LONG).show();
        display.setText("Your Average is:" + average);


    }
}

