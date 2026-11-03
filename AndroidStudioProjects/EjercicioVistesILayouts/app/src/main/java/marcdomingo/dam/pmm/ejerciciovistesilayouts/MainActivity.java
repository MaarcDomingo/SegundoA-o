package marcdomingo.dam.pmm.ejerciciovistesilayouts;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private CheckBox enableViews;
    private TextView eventsTextView;
    private LinearLayout myViewsLayout;
    private RadioButton myRadioButton;
    private int clickTimes = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initConfig();
    }

    private void initConfig() {
        setContentView(R.layout.activity_main);

        // inicialización de variables sin la necesidad de casting explicito
        enableViews = findViewById(R.id.enableViews);
        eventsTextView = findViewById(R.id.eventsTextView);
        myViewsLayout = findViewById(R.id.myViewsLayout);
        myRadioButton = findViewById(R.id.myRadioButton);

        // Listener para hacer visible el layout si se marca el checkbox
        enableViews.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    myViewsLayout.setVisibility(View.VISIBLE);
                } else {
                    myViewsLayout.setVisibility(View.GONE);
                }
            }
        });

        // Listener del radio Button
        myRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                clickTimes++;
                eventsTextView.setText("clica " + clickTimes + " Veces.");
            }
        });

        ToggleButton toggleVisibility = findViewById(R.id.ToggleVisibility);
        toggleVisibility.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    myViewsLayout.setVisibility(View.INVISIBLE);
                } else {
                    myViewsLayout.setVisibility(View.GONE);
                }
            }
        });
    }
}