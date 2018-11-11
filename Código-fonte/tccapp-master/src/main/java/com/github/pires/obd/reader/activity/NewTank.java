package com.github.pires.obd.reader.activity;
import com.github.pires.obd.reader.Drive;
import com.github.pires.obd.reader.Tank;
import com.github.pires.obd.reader.GsonRequest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.pires.obd.reader.R;
import com.github.pires.obd.reader.Vehicle;
import com.github.pires.obd.reader.VehicleSpinnerAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.velocity.runtime.directive.Parse;
import org.json.JSONObject;

import java.text.ParsePosition;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

public class NewTank extends Activity {

    private Spinner spinner;
    private static final String PATH_TO_SERVER = "http://ec2-52-15-123-78.us-east-2.compute.amazonaws.com:8080/api/vehicle";
    /*Lista de veiculos armazenada para o spinner*/
    protected List<Vehicle> spinnerData;
    /*Fila de requests do volley*/
    private RequestQueue queue;
    private Spinner VehicleSpinner;
    public String driveid;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_new_tank);
        queue = Volley.newRequestQueue(this);
        VehicleSpinner = (Spinner) findViewById(R.id.vehicle);
        requestJsonObject();
        //final Intent passVehicle = new Intent(NewDrive.this, MainActivity.class);
        //Set button to disabled until user selects vehicle
        final Button button = (Button) findViewById(R.id.new_drive);
        button.setEnabled(false);
        final TextView txtvin = (TextView) findViewById(R.id.vin);
        // Class Spinner implementing onItemSelectedListener
        VehicleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                TextView txtvin = (TextView) findViewById(R.id.vin);
                TextView txtmodel = (TextView) findViewById(R.id.model);
                TextView txtbrand = (TextView) findViewById(R.id.brand);
                TextView txtyear = (TextView) findViewById(R.id.year);

                // Set the text followed by the position
                txtmodel.setText("Model : "
                        + spinnerData.get(position).getModel());
                txtbrand.setText("Brand : "
                        + spinnerData.get(position).getBrand());
                txtyear.setText("Year : "
                        + spinnerData.get(position).getYear());
                txtvin.setText(
                        spinnerData.get(position).getVin());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                // can leave this empty
            }
        });




        //Event listener for create drive button
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //When button gets pressed, a POST request is sent to the API creating a new drive with
                //the selected vehicle
                Tank tank = new Tank();
                final EditText txtstation = (EditText) findViewById(R.id.station);
                String station = txtstation.getText().toString();
                tank.setStation(station);

                final EditText txtfuel = (EditText) findViewById(R.id.fuel);
                String fuel = txtfuel.getText().toString();
                tank.setFuel(fuel);

                final EditText txtvolume = (EditText) findViewById(R.id.volume);
                Integer volume = Integer.parseInt(txtvolume.getText().toString());
                tank.setVolume(volume);

                final EditText txtodometer = (EditText) findViewById(R.id.odometer);
                Integer odometer = Integer.parseInt(txtodometer.getText().toString());
                tank.setOdometer(odometer);

                //Date c = Calendar.getInstance().getTime();
                //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                //String strDate = sdf.format(c);
                //tank.setDate(sdf.parse(strDate,new ParsePosition(0)));




                GsonRequest<Tank> new_tank = new GsonRequest<Tank>("http://ec2-52-15-123-78.us-east-2.compute.amazonaws.com:8080/api/vehicle/"+txtvin.getText().toString()+"/tank",
                        tank,
                        Tank.class,
                        new HashMap<String, String>(),
                        new Response.Listener<Tank>() {
                            @Override
                            public void onResponse(Tank response) {
                                Context context = getApplicationContext();
                                txtodometer.setText("");
                                txtvolume.setText("");
                                txtfuel.setText("");
                                txtstation.setText("");
                                CharSequence text = "Refuel registered!";
                                int duration = Toast.LENGTH_SHORT;

                                Toast toast = Toast.makeText(context, text, duration);
                                toast.show();

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.i("post error","post error",error);
                            }

                        });

                queue.add(new_tank);

            }
        });
    }


   /* @Override
    public void onBackPressed(){

        TextView txtmodel = (TextView) findViewById(R.id.model);
        TextView txtbrand = (TextView) findViewById(R.id.brand);
        TextView txtyear = (TextView) findViewById(R.id.year);
        TextView txtvin = (TextView) findViewById(R.id.vin);
        Intent i = new Intent();
        i.putExtra("model", txtmodel.getText().toString());
        i.putExtra("brand", txtbrand.getText().toString());
        i.putExtra("year", txtyear.getText().toString());
        i.putExtra("vin", txtvin.getText().toString());
        setResult(99, i);
        finish();
    }*/

    private void requestJsonObject(){
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, PATH_TO_SERVER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                GsonBuilder builder = new GsonBuilder();
                Gson mGson = builder.create();
                spinnerData = Arrays.asList(mGson.fromJson(response, Vehicle[].class));
                //display first question to the user
                if(null != spinnerData){
                    spinner = (Spinner) findViewById(R.id.vehicle);
                    assert spinner != null;
                    //Sets button enable to true
                    final Button button = (Button) findViewById(R.id.new_drive);
                    button.setEnabled(true);
                    spinner.setVisibility(View.VISIBLE);
                    VehicleSpinnerAdapter spinnerAdapter = new VehicleSpinnerAdapter(NewTank.this, spinnerData);
                    spinner.setAdapter(spinnerAdapter);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(stringRequest);
    }

}
