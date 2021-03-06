package com.artolanggeng.purnamakertasindo.warehouse;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.SupportMenuInflater;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.artolanggeng.purnamakertasindo.R;
import com.artolanggeng.purnamakertasindo.common.Timbangan;
import com.artolanggeng.purnamakertasindo.popup.GantiPassword;
import com.artolanggeng.purnamakertasindo.service.FragMainLife;
import com.artolanggeng.purnamakertasindo.utils.Preference;

public class MainProses extends AppCompatActivity
{
	@BindView(R.id.ivBackIcon)
	ImageView ivBackIcon;
	@BindView(R.id.vpProses)
	ViewPager vpMainProses;
	@BindView(R.id.ivMenu)
	ImageView ivMenu;
	@BindView(R.id.tvMenuProses)
	TextView tvMenuProses;
	@BindView(R.id.llMenuProses)
	LinearLayout llMenuProses;
	@BindView(R.id.btnbawah1)
	Button btnbawah1;
	@BindView(R.id.btnbawah2)
	Button btnbawah2;
	@BindView(R.id.btnbawah3)
	Button btnbawah3;

	private SectionsWarehouse swMainProses;
	private Context context = this;
	static String TAG = "[Main Proses]";
	private MenuBuilder menuBuilder;
	private MenuPopupHelper menuHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lay_mainproses);
		ButterKnife.bind(this);

		BindingView();
	}

	public void BindingView()
	{
		swMainProses = new SectionsWarehouse(getSupportFragmentManager());
		vpMainProses.setAdapter(swMainProses);
		vpMainProses.setCurrentItem(0);

		vpMainProses.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
		{
			@Override
			public void onPageScrollStateChanged(int state)
			{
			}

			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
			{
			}

			@Override
			public void onPageSelected(int position)
			{
				if(position == 0)
				{
					btnbawah1.setBackgroundResource(R.color.appleGreen);
					btnbawah2.setBackgroundResource(R.color.whiteThree);
					btnbawah3.setBackgroundResource(R.color.whiteThree);
				}
				else
				if(position == 1)
				{
					btnbawah1.setBackgroundResource(R.color.whiteThree);
					btnbawah2.setBackgroundResource(R.color.appleGreen);
					btnbawah3.setBackgroundResource(R.color.whiteThree);
				}
				else
				if(position == 2)
				{
					btnbawah1.setBackgroundResource(R.color.whiteThree);
					btnbawah2.setBackgroundResource(R.color.whiteThree);
					btnbawah3.setBackgroundResource(R.color.appleGreen);
				}

				Log.d(TAG, "onPageSelected: " + position);
				FragMainLife fragmentToShow = (FragMainLife) swMainProses.instantiateItem(vpMainProses, position);
				fragmentToShow.onResumeFragMainLife();
			}
		});
	}

	@Override
	public void onBackPressed()
	{
		BackActivity();
	}

	@OnClick({R.id.ivBackIcon, R.id.ivMenu, R.id.ivPelanggan, R.id.tvPelanggan, R.id.llPelanggan, R.id.ivProses,
		R.id.llProses, R.id.ivRiwayat, R.id.tvRiwayat, R.id.llRiwayat, R.id.tvMenuProses, R.id.llMenuProses,
		R.id.ivMenuProses, R.id.rlMenuheader})
	public void onViewClicked(View view)
	{
		switch(view.getId())
		{
			case R.id.ivPelanggan:
			case R.id.tvPelanggan:
			case R.id.llPelanggan:
				vpMainProses.setCurrentItem(0);
			break;
			case R.id.ivProses:
			case R.id.llProses:
				vpMainProses.setCurrentItem(1);
			break;
			case R.id.ivRiwayat:
			case R.id.tvRiwayat:
			case R.id.llRiwayat:
				vpMainProses.setCurrentItem(2);
			break;
			case R.id.ivBackIcon:
				BackActivity();
			break;
			case R.id.llMenuProses:
			case R.id.ivMenuProses:
			case R.id.tvMenuProses:
				menuBuilder = new MenuBuilder(context);
				new SupportMenuInflater(context).inflate(R.menu.menu_proses, menuBuilder);
				menuHelper = new MenuPopupHelper(context, menuBuilder, llMenuProses);
				menuHelper.setForceShowIcon(true);
				menuHelper.show();

				menuBuilder.setCallback(new MenuBuilder.Callback()
				{
					@Override
					public boolean onMenuItemSelected(MenuBuilder menu, MenuItem menuItem)
					{
						switch(menuItem.getItemId())
						{
							case R.id.mnuSemua:
								menuHelper.dismiss();
								tvMenuProses.setText(getResources().getString(R.string.titleSemua));
								swMainProses = new SectionsWarehouse(getSupportFragmentManager());
								vpMainProses.setAdapter(swMainProses);
								vpMainProses.setCurrentItem(1);
							return true;
							case R.id.mnuTimbang:
								menuHelper.dismiss();
								tvMenuProses.setText(getResources().getString(R.string.titleTimbang));
								swMainProses = new SectionsWarehouse(getSupportFragmentManager());
								vpMainProses.setAdapter(swMainProses);
								vpMainProses.setCurrentItem(1);
							return true;
							case R.id.mnuQuality:
								menuHelper.dismiss();
								tvMenuProses.setText(getResources().getString(R.string.titleQuality));
								swMainProses = new SectionsWarehouse(getSupportFragmentManager());
								vpMainProses.setAdapter(swMainProses);
								vpMainProses.setCurrentItem(1);
							return true;
						}

						return false;
					}

					@Override
					public void onMenuModeChange(MenuBuilder menu)
					{
					}
				});
			break;
			case R.id.rlMenuheader:
			case R.id.ivMenu:
				menuBuilder = new MenuBuilder(context);
				new SupportMenuInflater(context).inflate(R.menu.menu_other, menuBuilder);
				menuHelper = new MenuPopupHelper(context, menuBuilder, ivMenu);
				menuHelper.setForceShowIcon(true);
				menuHelper.show();

				menuBuilder.setCallback(new MenuBuilder.Callback()
				{
					@Override
					public boolean onMenuItemSelected(MenuBuilder menu, MenuItem menuItem)
					{
						switch(menuItem.getItemId())
						{
							case R.id.mnuProfile:
								menuHelper.dismiss();
							return true;
							case R.id.mnuRubahPass:
								menuHelper.dismiss();
								GantiPassword cdMenuHome = new GantiPassword(MainProses.this);
								cdMenuHome.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
								cdMenuHome.show();
							return true;
						}

						return false;
					}

					@Override
					public void onMenuModeChange(MenuBuilder menu)
					{
					}
				});
			break;
		}
	}

	public class SectionsWarehouse extends FragmentPagerAdapter
	{
		public SectionsWarehouse(FragmentManager fm)
		{
			super(fm);
		}

		// BEGIN_INCLUDE (fragment_pager_adapter_getitem)

		/**
		 * Get fragment corresponding to a specific position. This will be used to populate the
		 * contents of the {@link ViewPager}.
		 *
		 * @param position Position to fetch fragment for.
		 * @return Fragment for specified position.
		 */
		@Override
		public Fragment getItem(int position)
		{
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			Fragment fragment = new Fragment();
			FragmentManager fragmentManager = getSupportFragmentManager();

			switch(position)
			{
				case 0:
					fragment = isFragmentAdded(fragmentManager, Preference.PrefArrived);
					if(fragment == null) fragment = new fragArrived();
				break;
				case 1:
					fragment = isFragmentAdded(fragmentManager, Preference.PrefProgress);
					if(fragment == null) fragment = new fragProses();
				break;
				case 2:
					fragment = isFragmentAdded(fragmentManager, Preference.PrefHistory);
					if(fragment == null) fragment = new fragHistory();
				break;
			}

			return fragment;
		}
		// END_INCLUDE (fragment_pager_adapter_getitem)

		// BEGIN_INCLUDE (fragment_pager_adapter_getcount)

		/**
		 * Get number of pages the {@link ViewPager} should render.
		 *
		 * @return Number of fragments to be rendered as pages.
		 */
		@Override
		public int getCount()
		{
			return 3;
		}
		// END_INCLUDE (fragment_pager_adapter_getcount)

		// BEGIN_INCLUDE (fragment_pager_adapter_getpagetitle)

		/**
		 * Get title for each of the pages. This will be displayed on each of the tabs.
		 *
		 * @param position Page to fetch title for.
		 * @return Title for specified page.
		 */
		@Override
		public CharSequence getPageTitle(int position)
		{
			return null;
		}

		// END_INCLUDE (fragment_pager_adapter_getpagetitle)
	}

	private Fragment isFragmentAdded(FragmentManager fragmentManager, String tag)
	{
		Fragment f = fragmentManager.findFragmentByTag(tag);

		if(f == null)
			return null;
		else
			return f;
	}

	private void BackActivity()
	{
		Intent TimbanganIntent = new Intent(MainProses.this, Timbangan.class);
		startActivity(TimbanganIntent);
		finish();
	}
}
