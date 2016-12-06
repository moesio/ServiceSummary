package com.anowit.servicesummary.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;

import com.anowit.servicesummary.actions.ClearListAction;
import com.anowit.servicesummary.actions.SaveReportAction;
import com.anowit.servicesummary.actions.SumAction;
import com.anowit.servicesummary.actions.UploadAction;
import com.anowit.servicesummary.helpers.ActionMap;
import com.anowit.servicesummary.helpers.Sections;
import com.seimos.android.dbhelper.database.DatabaseHelper;
import com.seimos.android.dbhelper.database.DatabaseHelper.Patch;
import com.seimos.android.dbhelper.database.DatabaseUtil;
import com.seimos.android.dbhelper.util.Application;

public class MainActivity extends Activity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

	/**
	 * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in {@link #restoreActionBar()}.
	 */
	private static Sections sections;
	private ActionMap actionMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// TODO Veriify global expetion handler
		//		UncaughtExceptionHandler uncaughtExceptionHandler = new DefaultExceptionHandler();
		//		Thread.setDefaultUncaughtExceptionHandler(uncaughtExceptionHandler);

		initializeDatabase();
		initializeSessions();
		initializeMenuActions();

		setContentView(R.layout.activity_main);
		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager().findFragmentById(R.id.navigation_drawer);
		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));
	}

	private void initializeMenuActions() {
		actionMap = new ActionMap(this);
		actionMap.append(R.id.menuItemClear, new ClearListAction(this));
		actionMap.append(R.id.menuItemSave, new SaveReportAction(this));
		actionMap.append(R.id.menuUpload, new UploadAction(this));
		actionMap.append(R.id.menuSum, new SumAction(this));
	}

	private void initializeDatabase() {
		Patch patches[] = new Patch[Application.getVersion(this)];
		String[] forward = getResources().getStringArray(R.array.patches);
		String[] rewind = getResources().getStringArray(R.array.unpatches);
		for (int i = 0; i < patches.length; i++) {
			patches[i] = new Patch(forward[i].split(";"), rewind[i].split(";"));
		}
		DatabaseUtil.instantiateDb(new DatabaseHelper(this, "service_summary", patches));
	}

	private void initializeSessions() {
		FormFragment formFragment = new FormFragment();
		ListFragment listFragment = new ListFragment();
		sections = new Sections(//
				getResources().getStringArray(R.array.sections), // 
				new Fragment[] { listFragment, formFragment }, //
				new int[] { R.menu.list, R.menu.form });
	}

	public ActionMap getActionMap() {
		return actionMap;
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		sections.setCurrentSession(position);
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction().replace(R.id.container, sections.getCurrentFragment()).commit();
	}

	public void restoreActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(sections.getCurrentTitle());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(sections.getCurrentMenuOption(), menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
