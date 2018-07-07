package com.garrettvernon.letstalkemotions;

import android.app.Application;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class AddChild extends AppCompatActivity {
    private static final String TAG = "AddChildActivity";
    private ChildViewModel mChildViewModel;
    private Child mChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child);
        mChildViewModel = ViewModelProviders.of(this).get(ChildViewModel.class);
        mChild = new Child();
        initOnClick();
    }

    private void initOnClick() {
        final View addChild = findViewById(R.id.add_child);
        addChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked");
                addChild();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Called when user taps on 'Add Child'
     */
    public void addChild(){
        EditText firstName = findViewById(R.id.firstName);
        String firstNameString = firstName.getText().toString();
        EditText lastName = findViewById(R.id.lastName);
        String lastNameString = lastName.getText().toString();
        mChild.setFirstName(firstNameString);
        mChild.setLastName(lastNameString);
        mChildViewModel.insert(mChild);
        /*//Return to MainActivity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);*/
    }
}
