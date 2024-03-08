package com.example.cie1_c;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().
                add(R.id.fragment1,new ProductList()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.registration)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View view = getLayoutInflater().inflate(R.layout.registration,null);
            builder.setMessage("Customer Registration");
            builder.setCancelable(false);
            builder.setView(view);
            builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    EditText txtCustomerName = view.findViewById(R.id.txtCustomerName);
                    EditText txtContactNo = view.findViewById(R.id.txtContactNo);
                    RadioButton rbMale = view.findViewById(R.id.rbMale);
                    RadioButton rbFemale = view.findViewById(R.id.rbFemale);
                    Spinner spCity = view.findViewById(R.id.spCities);
                    CheckBox chkClothes = view.findViewById(R.id.chkClothes);
                    CheckBox chkAccessories = view.findViewById(R.id.chkAccessories);
                    CheckBox chkElectronics = view.findViewById(R.id.chkElectronics);

                    String Gender;
                    if(rbMale.isChecked())
                    {
                        Gender = "Male";
                    }
                    else
                    {
                        Gender = "Female";
                    }

                    String InterestedArea = null;
                    if(chkClothes.isChecked())
                    {
                        InterestedArea = chkClothes.getText().toString();
                    }
                    if(chkAccessories.isChecked())
                    {
                        InterestedArea += "\n" + chkAccessories.getText().toString();
                    }
                    if(chkElectronics.isChecked())
                    {
                        InterestedArea += "\n" + chkElectronics.getText().toString();
                    }

                    String Content = "Customer Name : " + txtCustomerName.getText().toString() +
                            "\n Gender : " + Gender +
                            "\n City : " + spCity.getSelectedItem().toString() +
                            "\n Interested Shopping Area : " + InterestedArea;

                    Registration mFragment = new Registration();
                    Bundle bundle = new Bundle();
                    bundle.putString("content",Content);
                    bundle.putString("contactno",txtContactNo.getText().toString());
                    mFragment.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment2,mFragment).commit();
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }
        return true;
    }


}