package cat.alorma.capsules.ui.fragment;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import cat.alorma.capsulecorp.library.DispenserView;
import cat.alorma.capsulecorp.library.capsule.abs.Capsule;
import cat.alorma.capsulecorp.library.capsule.impl.TextCapsule;
import cat.alorma.capsulecorp.library.type.Type;
import cat.alorma.capsules.R;

/**
 * Created by Bernat on 25/11/13.
 */
public abstract class BaseFragment extends Fragment implements SeekBar.OnSeekBarChangeListener, View.OnClickListener {

    protected DispenserView dispenserView;

    private SeekBar seekBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        return inflater.inflate(R.layout.fragment_main, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dispenserView = (DispenserView) view.findViewById(R.id.dispenserView);
        dispenserView.setMaskResource(R.drawable.mask);
        dispenserView.setConcretType(getType());

        view.findViewById(R.id.padding0).setOnClickListener(this);
        view.findViewById(R.id.padding10).setOnClickListener(this);
        view.findViewById(R.id.padding50).setOnClickListener(this);
        view.findViewById(R.id.padding100).setOnClickListener(this);

        seekBar = (SeekBar) view.findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(this);
        seekBar.setMax(4);

        seekBar.setProgress(2);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        changeView(progress);
    }

    private void changeView(int progress) {
        progress++;
        dispenserView.clear();
        switch (progress) {
            case 5:
                dispenserView.addCapsule(getCapsule1());
                dispenserView.addCapsule(getCapsule2());
                dispenserView.addCapsule(getCapsule3());
                dispenserView.addCapsule(getCapsule4());
                dispenserView.addCapsule(new TextCapsule("5", Color.parseColor("#000000"), Color.parseColor("#bdc3c7")));
                break;
            case 4:
                dispenserView.addCapsule(getCapsule1());
                dispenserView.addCapsule(getCapsule2());
                dispenserView.addCapsule(getCapsule3());
                dispenserView.addCapsule(getCapsule4());
                break;
            case 3:
                dispenserView.addCapsule(getCapsule1());
                dispenserView.addCapsule(getCapsule2());
                dispenserView.addCapsule(getCapsule3());
                break;
            case 2:
                dispenserView.addCapsule(getCapsule1());
                dispenserView.addCapsule(getCapsule2());
                break;
            case 1:
                dispenserView.addCapsule(getCapsule1());
                break;
        }
    }

    protected abstract Capsule getCapsule1();

    protected abstract Capsule getCapsule2();

    protected abstract Capsule getCapsule3();

    protected abstract Capsule getCapsule4();

    protected abstract Type getType();

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        changeView(seekBar.getProgress());
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onClick(View v) {
        int padding = 0;
        switch (v.getId()) {
            case R.id.padding0:
                padding = 0;
                break;
            case R.id.padding10:
                padding = 10;
                break;
            case R.id.padding50:
                padding = 50;
                break;
            case R.id.padding100:
                padding = 100;
                break;
        }
        dispenserView.setPadding(padding);
    }

    public void setMaskEnabled() {
        dispenserView.setMaskEnabled(!dispenserView.isMaskEnabled());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.action_mask) {
            setMaskEnabled();
        }

        return true;
    }
}
