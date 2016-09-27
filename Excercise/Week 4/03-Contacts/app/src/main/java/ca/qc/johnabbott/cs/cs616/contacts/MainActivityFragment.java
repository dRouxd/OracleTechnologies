package ca.qc.johnabbott.cs.cs616.contacts;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.TextViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ca.qc.johnabbott.cs.cs616.contacts.model.Contact;
import ca.qc.johnabbott.cs.cs616.contacts.model.ContactData;
import ca.qc.johnabbott.cs.cs616.contacts.model.ContactDatabaseHandler;
import ca.qc.johnabbott.cs.cs616.contacts.model.ContactTable;
import ca.qc.johnabbott.cs.cs616.contacts.model.DatabaseException;

/**
 * ListView Demo
 */
public class MainActivityFragment extends Fragment {

    private ListView contacts;
    private ArrayAdapter<Contact> adapter;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        // 1. Retrieve the ListView
        contacts = (ListView) root.findViewById(R.id.contacts_ListView);

        // 2. Create and initialize adapter
        //adapter = new ArrayAdapter<>(this.getContext(), R.layout.list_item_contact, R.id.firstName_TextView);
        adapter = new ContactDataAdapter(this.getContext());

        // 3.  for today we have sample data
        final List<Contact> data = ContactData.getData();
        adapter.addAll(data);

        // 4.  Connect the adapter to the ListView
        contacts.setAdapter(adapter);

        // Toast the Contact on click
        contacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), ContactData.getContactById(id).toString(), Toast.LENGTH_SHORT).show();
            }
        });


        //*************************** TESTING *************************

        ContactDatabaseHandler dbh = new ContactDatabaseHandler(getContext());
        ContactTable contactTable = dbh.getContactTable();
        try {
            contactTable.create(ContactData.getContactById(0));
        } catch (DatabaseException e) {
            e.printStackTrace();
        }

        //*************************************************************


        return root;
    }

    /**
     * Data Adapter for Contacts
     */
    private class ContactDataAdapter extends ArrayAdapter<Contact> {

        public ContactDataAdapter(Context context) {
            super(context, -1);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            // inflate or reuse previously inflated UI
            View root;
            if(convertView != null)
                root = convertView;
            else {
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                root = inflater.inflate(R.layout.list_item_contact, parent, false);
            }

            // Update UI components
            Contact contact = getItem(position);

            TextView firstName = (TextView) root.findViewById(R.id.firstName_TextView);
            TextView lastName = (TextView) root.findViewById(R.id.lastName_TextView);

            firstName.setText(contact.getFirstName());
            lastName.setText(contact.getLastName());

            return root;
        }

        @Override
        public long getItemId(int position) {
            return getItem(position).getId(); // use Contact IDs
        }
    }
}
