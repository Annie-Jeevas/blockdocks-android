package android.anna.blockdocs_v2;

import android.anna.blockdocs_v2.model.Doc;
import android.app.Activity;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class MainActivity extends AppCompatActivity {

    ConstraintLayout docBlock;
    Logger log = Logger.getLogger("Main");
    ArrayList<Doc> docs = new ArrayList<>();
    private Context context;


    class DocArrayAdapter extends ArrayAdapter<Doc> {
        private Context context;
        private List<Doc> docList;

        //constructor, call on creation
        public DocArrayAdapter(Context context, int resource, ArrayList<Doc> objects) {
            super(context, resource, objects);
            this.context = context;
            this.docList = objects;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            //get the property we are displaying
            log.info("In getView() with position " + position);
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

//            //display trimmed excerpt for description
//            int descriptionLength = property.getDescription().length();
//            if(descriptionLength >= 100){
//                String descriptionTrim = property.getDescription().substring(0, 100) + "...";
//                description.setText(descriptionTrim);
//            }else{
//                description.setText(property.getDescription());
//            }

//            //get the image associated with this property
//            int imageID = context.getResources().getIdentifier(property.getImage(), "drawable", context.getPackageName());
//            image.setImageResource(imageID);

            return view;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        log.info("In onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        docs.add(new Doc(1, 1716480277, "2016-12-12", "Bachelor", "Information systems and technologies"));
        docs.add(new Doc(2, 1720456993, "2020-12-14", "Master", "Information systems and technologies"));
        ArrayAdapter arrayAdapter = new DocArrayAdapter(this, 0, docs);
        ListView listView = (ListView) findViewById(R.id.docsListView);
        listView.setAdapter(arrayAdapter);
        log.info(String.valueOf(docs.size()));
    }

    public void onBlockClick(View view) {
        log.info("In onClick for ConstraintLayout");
    }
}
