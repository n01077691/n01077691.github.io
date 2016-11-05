/*
name: Denis Stepanov
student ID: n01077691
assignment #: 2
*/

package denis.stepanov;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[] {"My Patients", "All Patients"};
    private Context context;

    public TabsPagerAdapter(FragmentManager fm, Context context)
    {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount()
    {
        return PAGE_COUNT;
    }

    public Fragment getItem(int position)
    {
        return PageFragment.newInstance(position +1);
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        //generate title based on tab position
        return tabTitles[position];
    }
}
