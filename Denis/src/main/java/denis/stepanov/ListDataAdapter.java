/*
name: Denis Stepanov
student ID: n01077691
assignment #: 2
*/

package denis.stepanov;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class ListDataAdapter extends ArrayAdapter {

    List list = new ArrayList<>();

    public ListDataAdapter(Context context, int resource) {
        super(context, resource);

    }

    static class LayoutHandler
    {
        TextView FIRSTNAME, LASTNAME, GENDER, HEALTHID, DEPARTMENT;
    }

    @Override
    public void add(Object object)
    {
        super.add(object);

        list.add(object);
    }

    @Override
    public int getCount()
    {
        return list.size();
    }

    @Override
    public Object getItem(int position)
    {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View row = convertView;
        LayoutHandler layoutHandler;

        if(row == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.list_view_layout, parent, false);

            layoutHandler = new LayoutHandler();
            layoutHandler.FIRSTNAME = (TextView) row.findViewById(R.id.first_nameRetr);
            layoutHandler.LASTNAME = (TextView) row.findViewById(R.id.last_nameRetr);
            layoutHandler.GENDER = (TextView) row.findViewById(R.id.gender_retr);
            layoutHandler.HEALTHID = (TextView) row.findViewById(R.id.health_idRetr);
            layoutHandler.DEPARTMENT = (TextView) row.findViewById(R.id.departmentRetr);
            row.setTag(layoutHandler);
        }
        else
        {
            layoutHandler = (LayoutHandler) row.getTag();
        }
        DataProvider dataProvider = (DataProvider)this.getItem(position);
        layoutHandler.FIRSTNAME.setText(dataProvider.getFirstname());
        layoutHandler.LASTNAME.setText(dataProvider.getLastName());
        layoutHandler.GENDER.setText(dataProvider.getGender());
        layoutHandler.HEALTHID.setText(dataProvider.getHealthID());
        layoutHandler.DEPARTMENT.setText(dataProvider.getDepartment());

        return row;
    }
}
