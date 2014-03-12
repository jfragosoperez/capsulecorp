package cat.alorma.capsules.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cat.alorma.capsulecorp.library.DispenserView;
import cat.alorma.capsulecorp.library.capsule.abs.Capsule;
import cat.alorma.capsulecorp.library.distributor.CapsulesAdapter;
import cat.alorma.capsulecorp.library.distributor.Distributor;
import cat.alorma.capsulecorp.library.type.Type;
import cat.alorma.capsulecorp.library.type.TypeFour;
import cat.alorma.capsules.R;

/**
 * Created by Bernat on 25/11/13.
 */
public abstract class BaseFragment extends Fragment implements SeekBar.OnSeekBarChangeListener, View.OnClickListener, TitleStrip, AdapterView.OnItemClickListener {

    protected DispenserView dispenserView;

    protected SeekBar seekBar;
    private CapsulesAdapter adapter;

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

        adapter = new CapsulesAdapter(getActivity());

        dispenserView.setAdapter(adapter);
        adapter.add(getCapsule1());
        adapter.add(getCapsule2());
        adapter.add(getCapsule3());
        //adapter.add(getCapsule4());

        dispenserView.setOnItemClickListener(this);

        view.findViewById(R.id.padding0).setOnClickListener(this);
        view.findViewById(R.id.padding10).setOnClickListener(this);
        view.findViewById(R.id.padding50).setOnClickListener(this);
        view.findViewById(R.id.padding100).setOnClickListener(this);

        seekBar = (SeekBar) view.findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(this);
        seekBar.setMax(3);

        seekBar.setProgress(2);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        changeView(progress);
    }

    protected void changeView(int progress) {
        /*progress++;
        dispenserView.clear();
        switch (progress) {
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
            case 0:
                dispenserView.clear();
                break;
        }*/
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
        // dispenserView.setPadding(padding);
    }

    public void setMaskEnabled() {
        /*dispenserView.setMaskEnabled(!dispenserView.isMaskEnabled());*/
    }
    public void setMaskBackgroundEnabled(){
        /*dispenserView.setBackgroundMaskEnabled(!dispenserView.isBackgroundMaskEnabled());*/
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.action_mask) {
            setMaskEnabled();
        }else if(item.getItemId() == R.id.action_background_mask){
            setMaskBackgroundEnabled();
        }

        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), "Capsule [" + position + "] clicked", Toast.LENGTH_SHORT).show();
    }
}
