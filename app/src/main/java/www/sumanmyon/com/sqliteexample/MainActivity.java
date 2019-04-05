package www.sumanmyon.com.sqliteexample;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DataBaseHelper myDB;

    EditText editTextName, editTextSurName, editTextMarks, editTextID;
    Button buttonInsert, buttonShow, buttonUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = new DataBaseHelper(this);

        castingViews();
        addData();
        viewData();
        updateData();
    }

    private void castingViews() {
        editTextName = (EditText)findViewById(R.id.edit_text_name);
        editTextSurName = (EditText)findViewById(R.id.edit_text_surname);
        editTextMarks = (EditText)findViewById(R.id.edit_text_marks);
        editTextID = (EditText)findViewById(R.id.edit_text_id);
        buttonInsert = (Button) findViewById(R.id.button_insert);
        buttonShow = (Button) findViewById(R.id.button_show);
        buttonUpdate = (Button)findViewById(R.id.button_update);
    }

    private void addData() {
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isTrue = checkingEditTextFieldsAreNotNull("insert");
                if(isTrue == true){
                    boolean isInserted = myDB.insert(editTextName.getText().toString(),editTextSurName.getText().toString(),editTextMarks.getText().toString());
                    if(isInserted == true){
                        showMessage("Data Inserted Successfully");
                    }else {
                        showMessage("Data Inserted failed");
                    }
                }
            }
        });
    }

    private void viewData(){
        buttonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = myDB.getAllData();
                if(cursor.getCount() == 0){  //TODO there is no data in database
                    //showMessage("There is no data in database");
                    showAllData("Error", "There is no data in database");
                    return;
                }

                //TODO if cursor is at first of row or not
                if(!cursor.isFirst()){
                    cursor.moveToFirst();
                }

                //TODO store data in StringBuffer
                StringBuffer buffer = new StringBuffer();
                while (cursor.moveToNext()){
                    buffer.append("ID : "+cursor.getString(0)+"\n");
                    buffer.append("Name : "+cursor.getString(1)+"\n");
                    buffer.append("SurName : "+cursor.getString(2)+"\n");
                    buffer.append("Marks : "+cursor.getString(3)+"\n\n");
                }

                //TODO Show all data
                showAllData("Data",buffer.toString());
            }
        });
    }

    private void updateData(){
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isTrue = checkingEditTextFieldsAreNotNull("update");
                if(isTrue == true){
                    boolean isUpdated = myDB.update(editTextID.getText().toString(), editTextName.getText().toString(),
                            editTextSurName.getText().toString(), editTextMarks.getText().toString());
                    if(isUpdated == true){
                        showMessage("Data Update Successfully");
                    }else {
                        showMessage("Data Update failed");
                    }
                }
            }
        });
    }

    public void showAllData(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    private boolean checkingEditTextFieldsAreNotNull(String type) {
        if(type.equals("update")){
            if(TextUtils.isEmpty(editTextID.getText())){
                editTextID.setError("Please enter ID");
                return false;
            }
        }else {
            if (TextUtils.isEmpty(editTextName.getText())) {
                editTextName.setError("Please enter name");
                return false;
            } else if (TextUtils.isEmpty(editTextSurName.getText())) {
                editTextSurName.setError("Please enter surname");
                return false;
            } else if (TextUtils.isEmpty(editTextMarks.getText())) {
                editTextMarks.setError("Please enter marks");
                return false;
            } else {
                return true;
            }
        }
        return true;
    }

    private void showMessage(String message) {
        Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();
    }

}
