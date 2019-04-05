package www.sumanmyon.com.sqliteexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    DataBaseHelper myDB;

    EditText editTextName, editTextSurName, editTextMarks;
    Button buttonInsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = new DataBaseHelper(this);

        castingViews();
        addData();
    }

    private void castingViews() {
        editTextName = (EditText)findViewById(R.id.edit_text_name);
        editTextSurName = (EditText)findViewById(R.id.edit_text_surname);
        editTextMarks = (EditText)findViewById(R.id.edit_text_marks);
        buttonInsert = (Button) findViewById(R.id.button_insert);
    }

    private void addData() {
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isTrue = checkingEditTextFieldsAreNotNull();
            }
        });
    }

    private boolean checkingEditTextFieldsAreNotNull() {
        if(TextUtils.isEmpty(editTextName.getText())){
            editTextName.setError("Please enter name");
            return false;
        }else if(TextUtils.isEmpty(editTextSurName.getText())) {
            editTextSurName.setError("Please enter surname");
            return false;
        }else if(TextUtils.isEmpty(editTextMarks.getText())) {
            editTextMarks.setError("Please enter marks");
            return false;
        }else {
            return true;
        }
    }

}
