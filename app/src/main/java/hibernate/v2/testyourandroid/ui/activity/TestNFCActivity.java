package hibernate.v2.testyourandroid.ui.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdView;

import butterknife.BindView;
import butterknife.ButterKnife;
import hibernate.v2.testyourandroid.C;
import hibernate.v2.testyourandroid.R;
import hibernate.v2.testyourandroid.ui.fragment.TestNFCFragment;

public class TestNFCActivity extends BaseActivity {

	@BindView(R.id.toolbar)
	Toolbar toolbar;

	private AdView adView;

	@BindView(R.id.adLayout)
	RelativeLayout adLayout;

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		C.detectLanguage(mContext);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_container_adview);
		ButterKnife.bind(this);
		setSupportActionBar(toolbar);

		initActionBar(getSupportActionBar(), R.string.title_activity_test_nfc);

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				adView = C.initAdView(mContext, adLayout);
			}
		}, DELAY_AD_LAYOUT);

		Fragment fragment = new TestNFCFragment();
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.container, fragment)
				.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.test_nfc, menu);
		return true;
	}

	@Override
	public void onDestroy() {
		if (adView != null) {
			adView.removeAllViews();
			adView.destroy();
		}
		super.onDestroy();
	}

}
