package com.example.shubham.odk_mockup;


import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class AllFragment extends Fragment {
    ArrayList<Form> data;
    ArrayList<Form> formsToRemove;
    ArrayList<Form> multiselect_list = new ArrayList<>();
    RCVAdapter adapter;
    boolean isMultiSelect = false;
    RecyclerView formRecycler;
    private ActionMode mActionMode;
    private Paint p = new Paint();
    Menu context_menu;


    public AllFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_all, container, false);
        formRecycler=(RecyclerView)v.findViewById(R.id.formRecycler);
        data=new ArrayList<>();
        String type=getArguments().getString("Type","All");
        if(type.equals("Finalized")){
        data.add(new Form("Birds","Finalized","6 hours ago"));
        data.add(new Form("Cascading Select Form","Finalized","Nov 5, 2016"));
        } else {
            data.add(new Form("Birds","","7 hours ago"));
            data.add(new Form("Cascading Select Form","Finalized","Nov 5, 2016"));
            data.add(new Form("Birds","Finalized","6 hours ago"));
            data.add(new Form("Biggest of N set","Sent","5 hours ago"));
        }
        adapter=new RCVAdapter(getContext(),data,multiselect_list);
        formRecycler.setAdapter(adapter);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        formRecycler.setLayoutManager(layoutManager);
        formRecycler.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), formRecycler, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (isMultiSelect)
                    multi_select(position);
                else
                    Toast.makeText(getContext(),"xyz",Toast.LENGTH_LONG).show();

            }

            @Override
            public void onItemLongClick(View view, int position) {
                if (!isMultiSelect) {
                    multiselect_list = new ArrayList<>();
                    isMultiSelect = true;

                    if (mActionMode == null) {
                        mActionMode = ((AppCompatActivity)getActivity()).startSupportActionMode(mActionModeCallback);
                        getActivity().findViewById(R.id.tabs).setBackgroundColor(ContextCompat.getColor(getContext(),R.color.cabColor));
                    }
                }

                multi_select(position);

            }
        }));
        initSwipe();
//        implementRecyclerViewClickListeners();
        return v;


    }

    public void multi_select(int position) {
        if (mActionMode != null) {
            if (multiselect_list.contains(data.get(position)))
                multiselect_list.remove(data.get(position));
            else
                multiselect_list.add(data.get(position));

            if (multiselect_list.size() > 0)
                mActionMode.setTitle("" + multiselect_list.size());
            else
                mActionMode.finish();

            refreshAdapter();

        }
    }

//    private void implementRecyclerViewClickListeners() {
//        formRecycler.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), formRecycler, new RecyclerClick_listener() {
//            @Override
//            public void onClick(View view, int position) {
//                //If ActionMode not null select item
//                if (mActionMode != null)
//                    onListItemSelect(position);
//            }
//
//            @Override
//            public void onLongClick(View view, int position) {
//                //Select item on long click
//                onListItemSelect(position);
//            }
//        }));
//    }


//    private void onListItemSelect(int position) {
//        adapter.toggleSelection(position);//Toggle the selection
//
//        boolean hasCheckedItems = adapter.getSelectedCount() > 0;//Check if any items are already selected or not
//
//
//        if (hasCheckedItems && mActionMode == null)
//            // there are some selected items, start the actionMode
//            mActionMode = ((AppCompatActivity) getActivity()).startActionMode( new Toolbar_ActionMode_Callback(getActivity(),adapter, data));
//        else if (!hasCheckedItems && mActionMode != null)
//            // there no selected items, finish the actionMode
//            mActionMode.finish();
//
//        if (mActionMode != null)
//            //set action mode title on item selection
//            mActionMode.setTitle(String.valueOf(adapter
//                    .getSelectedCount()) + " selected");
//
//
//    }
//    //Set action mode null after use
//    public void setNullToActionMode() {
//        if (mActionMode != null)
//            mActionMode = null;
//    }
//
//    //Delete selected rows
//    public void deleteRows() {
//        SparseBooleanArray selected = adapter
//                .getSelectedIds();//Get selected ids
//
//        //Loop all selected ids
//        for (int i = (selected.size() - 1); i >= 0; i--) {
//            if (selected.valueAt(i)) {
//                data.remove(selected.keyAt(i));
//                adapter.notifyDataSetChanged();//notify adapter
//
//            }
//        }
//        Toast.makeText(getActivity(), selected.size() + " item deleted.", Toast.LENGTH_SHORT).show();//Show Toast
//        mActionMode.finish();//Finish action mode after use

//    }



    private void initSwipe(){
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();



                    final int adapterPosition = viewHolder.getAdapterPosition();
                    final Form formItem = data.get(adapterPosition);
                    Snackbar snackbar = Snackbar
                            .make(formRecycler, "Item Deleted", Snackbar.LENGTH_LONG)
                            .setAction("Undo", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    data.add(adapterPosition, formItem);
                                    adapter.notifyItemInserted(adapterPosition);
                                    formRecycler.scrollToPosition(adapterPosition);
                                }
                            });

                    snackbar.show();
                    data.remove(adapterPosition);
                    adapter.notifyItemRemoved(adapterPosition);
                }




            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

                Bitmap icon;
                if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE){

                    View itemView = viewHolder.itemView;
                    float height = (float) itemView.getBottom() - (float) itemView.getTop();
                    float width = height / 3;

                    if(dX > 0){
                        p.setColor(Color.parseColor("#D32F2F"));
                        RectF background = new RectF((float) itemView.getLeft(), (float) itemView.getTop(), dX,(float) itemView.getBottom());
                        c.drawRect(background,p);
                        icon = BitmapFactory.decodeResource(getResources(), R.drawable.dustbin);
                        RectF icon_dest = new RectF((float) itemView.getLeft() + width ,(float) itemView.getTop() + width,(float) itemView.getLeft()+ 2*width,(float)itemView.getBottom() - width);
                        c.drawBitmap(icon,null,icon_dest,p);
                    } else {
                        p.setColor(Color.parseColor("#D32F2F"));
                        RectF background = new RectF((float) itemView.getRight() + dX, (float) itemView.getTop(),(float) itemView.getRight(), (float) itemView.getBottom());
                        c.drawRect(background,p);
                        icon = BitmapFactory.decodeResource(getResources(), R.drawable.dustbin);
                        RectF icon_dest = new RectF((float) itemView.getRight() - 2*width ,(float) itemView.getTop() + width,(float) itemView.getRight() - width,(float)itemView.getBottom() - width);
                        c.drawBitmap(icon,null,icon_dest,p);
                    }
                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(formRecycler);

    }

    public void refreshAdapter()
    {
        adapter.selectedList=multiselect_list;
        adapter.mData=data;
        adapter.notifyDataSetChanged();
    }

    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            // Inflate a menu resource providing context menu items
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.main_menu, menu);
            context_menu = menu;
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false; // Return false if nothing is done
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_delete:
                    if(multiselect_list.size()>0)
                    {
                        for(int i=0;i<multiselect_list.size();i++)
                            data.remove(multiselect_list.get(i));

                        adapter.notifyDataSetChanged();

                        if (mActionMode != null) {
                            mActionMode.finish();
                        }
                        Toast.makeText(getActivity().getApplicationContext(), "Delete Click", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                case R.id.action_forward:
                    ProgressDialog progressDialog=new ProgressDialog(getActivity());
                    progressDialog.setIndeterminate(true);
                    progressDialog.setMessage("Sending forms 1 of 1");
                    progressDialog.show();
                    return true;
            }

            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mActionMode = null;
            isMultiSelect = false;
            multiselect_list = new ArrayList<Form>();
            getActivity().findViewById(R.id.tabs).setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));

            refreshAdapter();
        }
    };

    // AlertDialog Callback Functions


}


