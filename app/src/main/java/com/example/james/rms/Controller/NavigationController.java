package com.example.james.rms.Controller;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.james.rms.CommonProfile.DialogBox.ClassicDialog;
import com.example.james.rms.CommonProfile.FabButton.FabCore;
import com.example.james.rms.CommonProfile.MyBaseFragment;
import com.example.james.rms.CommonProfile.SharePreferences.LoginPreferences;
import com.example.james.rms.CommonProfile.SharePreferences.NavPreferences;
import com.example.james.rms.CommonProfile.SharePreferences.PartyIdPreferences;
import com.example.james.rms.Controller.CommunicateService.NavToRL;
import com.example.james.rms.Core.Dao.InventoryDao;
import com.example.james.rms.Core.Dao.InventoryDaoImpl;
import com.example.james.rms.Core.Model.InventoryModel;
import com.example.james.rms.Core.Model.Status;
import com.example.james.rms.ITF.ViewPagerListener;
import com.example.james.rms.Inventory.InventoryCombine;
import com.example.james.rms.Inventory.Tab.InventoryContainer;
import com.example.james.rms.Login.LoginActivity;
import com.example.james.rms.Operation.OperationContainer;
import com.example.james.rms.ProductPool.Tab.ProductContainer;
import com.example.james.rms.R;
import com.example.james.rms.Receiving.Tab.ReceivingContainer;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NavigationController extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        android.support.v7.widget.SearchView.OnQueryTextListener,
        MenuItemCompat.OnActionExpandListener,ViewPagerListener,
        FloatingActionMenu.OnMenuToggleListener{

    @BindView(R.id.nav_toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.page)
    MyViewPager pager;
    @BindView(R.id.fab_menu)
    FloatingActionMenu fab_menu;

    //
    private NavPagerAdapter navPagerAdapter;
    private List<Fragment> fragments = new ArrayList<>();

    String partyId;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_controller);
        ButterKnife.bind(this);
        navigationView.setNavigationItemSelectedListener(this);
        setUpActionBar();
        setUpFragmentType();
        setUpViewPager();
        //Preferences
        NavPreferences navPreferences = new NavPreferences(this,"loginInformation",MODE_PRIVATE);
        String userName = navPreferences.getPreferences_NavPreferences().get("username");
        partyId =  navPreferences.getPreferences_NavPreferences().get("partyId");
        View hearder = navigationView.getHeaderView(0);
        TextView navUsername  = (TextView) hearder.findViewById(R.id.navUserName);
        navUsername.setText(userName);
        FabSetting();
    }

    public void onResume(){
        super.onResume();
        //Service
    }
//    private List<InventoryModel> InventoryConfig(String partyId) {
//        //partyId
//        String combine_partyId = InventoryCombine.combine_partyIdAndStatus(partyId, Status.PROGRESS);
//        //Service
//        InventoryDao inventoryDao = new InventoryDaoImpl();
//        List<InventoryModel> inventoryModels = inventoryDao.findByPartyIdAndStatus(combine_partyId);
//
//        ViewPagerListener viewPagerListener = (MyBaseFragment) fragments.get(2);
//        viewPagerListener.transferViewPager(R.id.inventory_item,inventoryModels);
//        return inventoryModels;
//    }

    private void setUpFragmentType(){
//        fragments.add(new ReceivingContainer());
        fragments.add(new ProductContainer());
        fragments.add(new ReceivingContainer());
        fragments.add(new InventoryContainer());
    }

    private void FabSetting() {
        FabCore fabCore = new FabCore(this);
        FloatingActionButton fabAdd = fabCore.fabAdd();
        FloatingActionButton fabReceiving = fabCore.fabReceiving();
        fab_menu.addMenuButton(fabAdd);
//        fab_menu.addMenuButton(fabReceiving);
        fab_menu.setOnMenuToggleListener(this);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent = intent.setClass(getApplication(), OperationContainer.class);
                startActivity(intent);
                Toast.makeText(getApplication(),"fabAdd",Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void setUpViewPager(){
        navPagerAdapter = new NavPagerAdapter(getSupportFragmentManager(), fragments);
        pager.setAdapter(navPagerAdapter);
        pager.setOffscreenPageLimit(fragments.size());
        //setTouchEvent
        pager.setPagingEnabled(false);
        pager.setCurrentItem(0,true);
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_all_product) {
            pager.setCurrentItem(0);
            toolbar.setTitle(R.string.allproduct);
        } else if (id == R.id.nav_receiving) {
            pager.setCurrentItem(1);
            toolbar.setTitle(R.string.receiving);
        } else if (id == R.id.nav_inventory) {
            pager.setCurrentItem(2);
            toolbar.setTitle(R.string.inventory);
        } else if (id == R.id.nav_stockOut) {
//            toolbar.setTitle(R.string.stockout);
        } else if (id == R.id.nav_record) {
//            toolbar.setTitle(R.string.record);
        } else if (id == R.id.nav_setting) {
//            toolbar.setTitle(R.string.setting);
        } else if (id == R.id.nav_logout) {
            logoutStatus();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logoutStatus(){
        LoginPreferences loginPreferences = new LoginPreferences(this,"loginInformation", MODE_PRIVATE);
        if( loginPreferences.getPreferences_loginInformation() != null){
            String username = loginPreferences.getPreferences_loginInformation().get("username");
            String password = loginPreferences.getPreferences_loginInformation().get("password");
            loginPreferences.setPreferences_loginInformation(username,password);
            Intent intent = new Intent();
            intent.setClass(this, LoginActivity.class);
            startActivity(intent);
        }
    }
    private void setUpActionBar() {
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setTitle(R.string.allproduct);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);


        MenuItem menuSearchItem = menu.findItem(R.id.my_search);
        // Get the SearchView and set the searchable configuration

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        android.support.v7.widget.SearchView searchView = (android.support.v7.widget.SearchView) menuSearchItem.getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        // 這邊讓icon可以還原到搜尋的icon
        searchView.setMaxWidth( Integer.MAX_VALUE );
        searchView.setIconifiedByDefault(true);
        searchView.setOnQueryTextListener(this);
        searchView.setQueryHint(getResources().getString(R.string.search_hint));
        MenuItemCompat.setOnActionExpandListener(menuSearchItem,this);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Log.v("asd","pager :" +pager.getCurrentItem());
            boolean isFirst = false;
            switch (pager.getCurrentItem()){
                case 1:
                    Fragment fragment = fragments.get(pager.getCurrentItem());
                    NavToRL navToRl = (ReceivingContainer)fragment;
                    isFirst = navToRl.changeCurrentPage();
                    break;
            }
            if(!isFirst){
                ClassicDialog classicDialog = new ClassicDialog(this);
                classicDialog.showLeave(getString(R.string.leave));
            }
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Fragment fragment = fragments.get(pager.getCurrentItem());
        try{
            MyBaseFragment myBaseFragment = (MyBaseFragment)fragment;
            return myBaseFragment.onQueryTextChange(newText);
        }catch (ClassCastException e){
            return false;
        }
    }
    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.GRAY));
        return true;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        getSupportActionBar().setBackgroundDrawable(ContextCompat.getDrawable(this,R.color.blue0895ef));
        return true;
    }
    @Override
    public void transferViewPager(int rid, List models) {
        Fragment fragment = fragments.get(pager.getCurrentItem());
        MyBaseFragment myBaseFragment = (MyBaseFragment)fragment;
        try{
            switch (rid){
                case R.id.product_action:
                    myBaseFragment.transferViewPager(R.id.product_action,models);
                    break;
                case R.id.receiving_item:
                    myBaseFragment.transferViewPager(R.id.receiving_item,models);
                    break;
            }
        }catch (ClassCastException e){
        }
    }

    @Override
    public void onMenuToggle(boolean opened) {
        if(opened){
//            drawer.openDrawer(GravityCompat.START);
        }
    }
}
