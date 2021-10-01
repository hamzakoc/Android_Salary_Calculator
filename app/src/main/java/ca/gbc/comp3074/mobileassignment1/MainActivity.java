package ca.gbc.comp3074.mobileassignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {


    EditText noOfHours,hourlyPay;
    TextView payment, overtime, taxPaym, totalPaym;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inputs
        noOfHours = findViewById(R.id.num_hours_input);
        hourlyPay = findViewById(R.id.hourly_rate_input);

        // Outputs
        payment = findViewById(R.id.payment_out);
        overtime = findViewById(R.id.overtime_out);
        taxPaym = findViewById(R.id.tax_out);
        totalPaym = findViewById(R.id.total_out);
    }

    public void calculation(View view) {
        try {
            double workedHours= Double.parseDouble(noOfHours.getText().toString());
            double hourlyRate = Double.parseDouble(hourlyPay.getText().toString());

            double paymentP = 0;
            double overtimeP = 0;
            double totalP = 0;
            double tax = 0;

            if (workedHours <= 40) {
                paymentP = workedHours * hourlyRate;
                overtimeP = 0;
                totalP = paymentP;
            } else {
                paymentP = 40 * hourlyRate;
                overtimeP = (workedHours - 40) * hourlyRate;
                totalP = paymentP + overtimeP;
            }

            tax = totalP * 0.18;

            payment.setText(String.format("%.2f", paymentP));
            overtime.setText(String.format("%.2f", overtimeP));
            totalPaym.setText(String.format("%.2f", totalP));
            taxPaym.setText(String.format("%.2f", tax));

        } catch (Exception e) {

            TextView err =  findViewById(R.id.error);
            err.setText("Opps! Enter numbers to calculate!");
            payment.setText("");
            overtime.setText("");
            totalPaym.setText("");
            taxPaym.setText("");

        }finally {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    TextView err =  findViewById(R.id.error);
                    err.setText("");
                                 }
            }, 2000);
        }




    }



    // Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
           switch (item.getItemId()) {
            case R.id.about_me:
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}