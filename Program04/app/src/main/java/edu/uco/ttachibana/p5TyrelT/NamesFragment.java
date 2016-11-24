package edu.uco.ttachibana.p5TyrelT;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class NamesFragment extends ListFragment
{
    private ListSelectionListener classListener = null;

    public interface ListSelectionListener
    {
        void onListSelection(int index);
    }

    @Override
    public void onListItemClick(ListView l, View v, int pos, long id)
    {
        getListView().setItemChecked(pos, true);
        classListener.onListSelection(pos);
    }

    @Override
    public void onActivityCreated(Bundle savedState)
    {
        super.onActivityCreated(savedState);
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        setListAdapter(new ArrayAdapter<>(getActivity(), R.layout.list_item, ContactActivity.contactsList));

        try { classListener = (ListSelectionListener) getActivity(); }
        catch (ClassCastException e) { throw new ClassCastException(getActivity().toString() + " must implement ListSelectionListener"); }
    }
}
