//package com.example.shubham.odk_mockup;
//
//import android.content.Context;
//import android.os.Build;
//import android.support.v4.app.Fragment;
//import android.support.v4.view.MenuItemCompat;
//import android.util.Log;
//import android.util.SparseBooleanArray;
//import android.view.ActionMode;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.widget.Toast;
//
//import java.util.ArrayList;
//
///**
// * Created by shubham on 22/3/17.
// */
//
//public class Toolbar_ActionMode_Callback implements ActionMode.Callback {
//
//    private Context context;
//    private RCVAdapter recyclerView_adapter;
//    private ArrayList<Form> message_models;
//
//
//    public Toolbar_ActionMode_Callback(Context context, RCVAdapter recyclerView_adapter, ArrayList<Form> message_models) {
//        this.context = context;
//        this.recyclerView_adapter = recyclerView_adapter;
//        this.message_models = message_models;
//    }
//
//    @Override
//    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
//        mode.getMenuInflater().inflate(R.menu.main_menu, menu);//Inflate the menu over action mode
//        return true;
//    }
//
//    @Override
//    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
//
//        //Sometimes the menu will not be visible so for that we need to set their visibility manually in this method
//        //So here show action menu according to SDK Levels
////        if (Build.VERSION.SDK_INT < 11) {
////            MenuItemCompat.setShowAsAction(menu.findItem(R.id.action_delete), MenuItemCompat.SHOW_AS_ACTION_NEVER);
////            MenuItemCompat.setShowAsAction(menu.findItem(R.id.action_forward), MenuItemCompat.SHOW_AS_ACTION_NEVER);
////        } else {
////            menu.findItem(R.id.action_delete).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
////            menu.findItem(R.id.action_forward).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
////        }
//
//        menu.findItem(R.id.action_delete).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
//
//        return true;
//    }
//
//    @Override
//    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action_delete:
//
//
//                    Fragment recyclerFragment = new AllFragment();//Get recycler view fragment
//                    if (recyclerFragment != null)
//                        //If recycler fragment not null
//                        ((AllFragment)recyclerFragment).deleteRows();//delete selected rows
//
//                break;
////            case R.id.action_copy:
////
////                //Get selected ids on basis of current fragment action mode
////                SparseBooleanArray selected = recyclerView_adapter
////                            .getSelectedIds();
////
////                int selectedMessageSize = selected.size();
////
////                //Loop to all selected items
////                for (int i = (selectedMessageSize - 1); i >= 0; i--) {
////                    if (selected.valueAt(i)) {
////                        //get selected data in Model
////                        Form model = message_models.get(selected.keyAt(i));
////                        String title = model.getFormName();
////                        String subTitle = model.getFormStatus();
////                        //Print the data to show if its working properly or not
////                        Log.e("Selected Items", "Title - " + title + "n" + "Sub Title - " + subTitle);
////
////                    }
////                }
////                Toast.makeText(context, "You selected Copy menu.", Toast.LENGTH_SHORT).show();//Show toast
////                mode.finish();//Finish action mode
////                break;
//            case R.id.action_forward:
//                Toast.makeText(context, "You selected Forward menu.", Toast.LENGTH_SHORT).show();//Show toast
//                mode.finish();//Finish action mode
//                break;
//
//
//        }
//        return false;
//    }
//
//
//    @Override
//    public void onDestroyActionMode(ActionMode mode) {
//
//        //When action mode destroyed remove selected selections and set action mode to null
//        //First check current fragment action mode
//
//            recyclerView_adapter.removeSelection();  // remove selection
//            Fragment recyclerFragment = new AllFragment();//Get recycler fragment
//            if (recyclerFragment != null)
//                ((AllFragment) recyclerFragment).setNullToActionMode();//Set action mode null
//
//    }
//}
