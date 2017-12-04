package android.anna.blockdocs_v2.helpers;

import android.anna.blockdocs_v2.R;
import android.anna.blockdocs_v2.model.Doc;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Анюта on 03.12.2017.
 */

public class DocArrayAdapter extends ArrayAdapter<Doc> {
    private Context context;
    private List<Doc> docList;

    String TAG = "DocArrayAdapter";

    public DocArrayAdapter(Context context, int resource, List<Doc> objects) {
        super(context, resource, objects);
        this.context = context;
        this.docList = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        //get the property we are displaying
        Log.d(TAG,"In getView() with position " + position);
        Doc doc = docList.get(position);

        //get the inflater and inflate the XML layout for each item
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.docblock_layout, null);

        TextView number = (TextView) view.findViewById(R.id.number);
        TextView specialization = (TextView) view.findViewById(R.id.specialization);
        TextView qualification = (TextView) view.findViewById(R.id.qualification);
        TextView graduationDate = (TextView) view.findViewById(R.id.graduation);

        number.setText(String.valueOf(doc.getNumber()));
        specialization.setText(doc.getSpecialization());
        qualification.setText(doc.getQualification());
        graduationDate.setText(doc.getGraduationDate());

        return view;
    }
}
